package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.port.in.command.CardSplitConsumptionCommand;
import com.bank.credit_card.application.port.in.usecase.SplitConsumptionUseCase;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceConsumption;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.domain.consumption.vo.ConsumptionId;

import java.util.List;

import static com.bank.credit_card.domain.balance.factory.BalanceType.CONSUMPTION;

public class SplitConsumptionService implements SplitConsumptionUseCase {

    private final BusinessServiceCard businessServiceCard;
    private final BusinessServiceBalance businessServiceBalance;
    private final BusinessServiceConsumption businessServiceConsumption;

    public SplitConsumptionService(BusinessServiceCard businessServiceCard, BusinessServiceBalance businessServiceBalance, BusinessServiceConsumption businessServiceConsumption) {
        this.businessServiceCard = businessServiceCard;
        this.businessServiceBalance = businessServiceBalance;
        this.businessServiceConsumption = businessServiceConsumption;
    }

    @Override
    public List<ConsumptionId> splitConsumption(CardSplitConsumptionCommand cardSplitConsumptionCommand) {

        var card = businessServiceCard.get(cardSplitConsumptionCommand.cardId());
        var balance = businessServiceBalance.get(cardSplitConsumptionCommand.cardId(), CONSUMPTION);
        var consumption = businessServiceConsumption.get(cardSplitConsumptionCommand.cardId(),
                cardSplitConsumptionCommand.consumptionId());

        var consumptions = consumption.split(cardSplitConsumptionCommand.installments(), card.getCredit().getDebtTax());
        balance.cancel(consumption.getConsumptionAmount());
        card.validateIfCardIfInDebt();
        consumption.close();
        card.updateStatus(balance.isOvercharged());

        consumptions.stream()
                .map(Consumption::getConsumptionAmount)
                .forEach(amount -> {
                    balance.apply(amount);
                    card.updateStatus(balance.isOvercharged());
                });

        var consumptionIds = consumptions.stream()
                .map(businessServiceConsumption::save)
                .toList();

        businessServiceConsumption.save(consumption);
        businessServiceBalance.save(balance);
        businessServiceCard.save(card);

        return consumptionIds;
    }
}
