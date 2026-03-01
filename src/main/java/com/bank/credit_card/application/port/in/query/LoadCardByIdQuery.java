package com.bank.credit_card.application.port.in.query;

import com.bank.credit_card.application.port.in.query.view.LoadCardBalanceBenefitView;

@FunctionalInterface
public interface LoadCardByIdQuery {
    LoadCardBalanceBenefitView findById(Long cardId);
}
