package com.bank.credit_card.domain.benefit.vo;

import com.bank.credit_card.domain.exception.DomainException;

public class BenefitIdException extends DomainException {

    public BenefitIdException() {
    }

    public BenefitIdException(String message) {
        super(message);
    }
}

