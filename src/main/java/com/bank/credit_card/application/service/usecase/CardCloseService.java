package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.port.in.command.CardCloseCommand;
import com.bank.credit_card.application.port.in.usecase.CardCloseUseCase;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBenefit;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.domain.card.vo.CardId;

public class CardCloseService implements CardCloseUseCase {

    private final BusinessServiceCard businessServiceCard;
    private final BusinessServiceBalance businessServiceBalance;
    private final BusinessServiceBenefit businessServiceBenefit;

    public CardCloseService(BusinessServiceCard businessServiceCard, BusinessServiceBalance businessServiceBalance, BusinessServiceBenefit businessServiceBenefit) {
        this.businessServiceCard = businessServiceCard;
        this.businessServiceBalance = businessServiceBalance;
        this.businessServiceBenefit = businessServiceBenefit;
    }

    @Override
    public CardId closeCard(CardCloseCommand cardCloseCommand) {

        var card = businessServiceCard.get(cardCloseCommand.cardId());
        var balance = businessServiceBalance.get(cardCloseCommand.cardId());
        var benefit = businessServiceBenefit.get(cardCloseCommand.cardId());

        card.close();
        balance.close();
        benefit.close();

        var id = businessServiceCard.save(card);
        businessServiceBalance.save(balance);
        businessServiceBenefit.save(benefit);

        return id;
    }
}
