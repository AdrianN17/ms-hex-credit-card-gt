package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.consumption.ApplicationConsumptionException;
import com.bank.credit_card.application.port.in.command.CardProcessConsumptionCommand;
import com.bank.credit_card.application.port.in.usecase.ProcessConsumptionUseCase;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBenefit;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceConsumption;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.domain.consumption.vo.ConsumptionId;

import static com.bank.credit_card.application.error.consumption.ConsumptionApplicationErrorMessage.CONSUMPTION_CURRENCY_NOT_FOUND;
import static com.bank.credit_card.domain.balance.factory.BalanceType.CONSUMPTION;

public class ConsumptionService implements ProcessConsumptionUseCase {

    private final BusinessServiceCard businessServiceCard;
    private final BusinessServiceBalance businessServiceBalance;
    private final BusinessServiceBenefit businessServiceBenefit;
    private final BusinessServiceConsumption businessServiceConsumption;
    private final LoadCurrencyPort loadCurrencyPort;

    public ConsumptionService(BusinessServiceCard businessServiceCard, BusinessServiceBalance businessServiceBalance, BusinessServiceBenefit businessServiceBenefit, BusinessServiceConsumption businessServiceConsumption, LoadCurrencyPort loadCurrencyPort) {
        this.businessServiceCard = businessServiceCard;
        this.businessServiceBalance = businessServiceBalance;
        this.businessServiceBenefit = businessServiceBenefit;
        this.businessServiceConsumption = businessServiceConsumption;
        this.loadCurrencyPort = loadCurrencyPort;
    }

    @Override
    public ConsumptionId processConsumption(CardProcessConsumptionCommand cardProcessConsumptionCommand) {

        var card = businessServiceCard.get(cardProcessConsumptionCommand.cardId());
        var balance = businessServiceBalance.get(cardProcessConsumptionCommand.cardId(), CONSUMPTION);
        var benefit = businessServiceBenefit.get(cardProcessConsumptionCommand.cardId());

        var consumptionCurrency = loadCurrencyPort.load(cardProcessConsumptionCommand.currency())
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_CURRENCY_NOT_FOUND));

        var consumption = Consumption.builder()
                .consumptionAmount(cardProcessConsumptionCommand.amount(),
                        consumptionCurrency)
                .cardId(cardProcessConsumptionCommand.cardId())
                .sellerName(cardProcessConsumptionCommand.sellerName())
                .build();

        card.validateIfCardIfInDebt();

        benefit.accumulate(consumption.getConsumptionAmount(), card.getRatio());
        balance.apply(consumption.getConsumptionAmount());
        card.updateStatus(balance.isOvercharged());

        var id = businessServiceConsumption.save(consumption);
        businessServiceBalance.save(balance);
        businessServiceBenefit.save(benefit);
        businessServiceCard.save(card);

        return id;
    }
}
