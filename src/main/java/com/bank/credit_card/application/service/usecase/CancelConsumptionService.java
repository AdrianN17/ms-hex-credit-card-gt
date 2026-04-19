package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.port.in.command.CardCancelConsumptionCommand;
import com.bank.credit_card.application.port.in.usecase.CancelConsumptionUseCase;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceConsumption;
import com.bank.credit_card.domain.consumption.vo.ConsumptionId;

import static com.bank.credit_card.domain.balance.factory.BalanceType.CONSUMPTION;

public class CancelConsumptionService implements CancelConsumptionUseCase {

    private final BusinessServiceCard businessServiceCard;
    private final BusinessServiceBalance businessServiceBalance;
    private final BusinessServiceConsumption businessServiceConsumption;

    public CancelConsumptionService(BusinessServiceCard businessServiceCard, BusinessServiceBalance businessServiceBalance, BusinessServiceConsumption businessServiceConsumption) {
        this.businessServiceCard = businessServiceCard;
        this.businessServiceBalance = businessServiceBalance;
        this.businessServiceConsumption = businessServiceConsumption;
    }


    @Override
    public ConsumptionId cancelConsumption(CardCancelConsumptionCommand cardCancelConsumptionCommand) {

        var card = businessServiceCard.get(cardCancelConsumptionCommand.cardId());
        var balance = businessServiceBalance.get(cardCancelConsumptionCommand.cardId(), CONSUMPTION);
        var consumption = businessServiceConsumption.get(cardCancelConsumptionCommand.cardId(),
                cardCancelConsumptionCommand.consumptionId());

        balance.cancel(consumption.getConsumptionAmount());
        card.updateStatus(balance.isOvercharged());
        consumption.close();

        var id = businessServiceConsumption.save(consumption);
        businessServiceBalance.save(balance);
        businessServiceCard.save(card);

        return id;
    }
}
