package com.bank.credit_card.application.service.query;

import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.port.in.query.LoadCardByIdQuery;
import com.bank.credit_card.application.port.in.query.view.LoadCardBalanceBenefitView;
import com.bank.credit_card.application.port.out.card.query.LoadCardBalanceBenefitPort;

import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_BALANCE_BENEFIT_NOT_FOUND;

public class LoadCardByIdService implements LoadCardByIdQuery {

    private final LoadCardBalanceBenefitPort loadCardBalanceBenefitPort;

    public LoadCardByIdService(LoadCardBalanceBenefitPort loadCardBalanceBenefitPort) {
        this.loadCardBalanceBenefitPort = loadCardBalanceBenefitPort;
    }

    @Override
    public LoadCardBalanceBenefitView findById(Long cardId) {
        return loadCardBalanceBenefitPort
                .load(cardId)
                .orElseThrow(() ->
                        new ApplicationCardException(CARD_BALANCE_BENEFIT_NOT_FOUND));
    }
}
