package com.bank.credit_card.application.service.usecase.business;

import com.bank.credit_card.application.error.benefit.ApplicationBenefitException;
import com.bank.credit_card.application.port.out.benefit.LoadBenefitPort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.domain.benefit.vo.BenefitId;

import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.BENEFIT_NOT_FOUND;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.FAILED_TO_CREATE_BENEFIT;

public class BusinessServiceBenefitImpl implements BusinessServiceBenefit {

    private final LoadBenefitPort loadBenefitPort;
    private final SaveBenefitPort saveBenefitPort;

    public BusinessServiceBenefitImpl(LoadBenefitPort loadBenefitPort, SaveBenefitPort saveBenefitPort) {
        this.loadBenefitPort = loadBenefitPort;
        this.saveBenefitPort = saveBenefitPort;
    }

    @Override
    public Benefit get(Long cardId) {
        return loadBenefitPort
                .load(cardId)
                .orElseThrow(() -> new ApplicationBenefitException(BENEFIT_NOT_FOUND));
    }

    @Override
    public BenefitId save(Benefit benefit) {
        return saveBenefitPort.save(benefit)
                .map(BenefitId::create)
                .orElseThrow(() -> new ApplicationBenefitException(FAILED_TO_CREATE_BENEFIT));
    }
}
