package com.bank.credit_card.application.port.out.benefit;

import com.bank.credit_card.application.port.in.query.view.LoadBenefitView;

import java.util.Optional;

public interface LoadBenefitPort {
    Optional<LoadBenefitView> load(Long benefitId);
}
