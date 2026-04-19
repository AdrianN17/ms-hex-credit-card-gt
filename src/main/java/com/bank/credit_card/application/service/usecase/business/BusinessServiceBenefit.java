package com.bank.credit_card.application.service.usecase.business;

import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.domain.benefit.vo.BenefitId;

public interface BusinessServiceBenefit {
    Benefit get(Long cardId);

    BenefitId save(Benefit benefit);
}
