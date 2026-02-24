package com.bank.credit_card.domain.base;

import com.bank.credit_card.domain.exception.DomainException;

@FunctionalInterface
public interface IsValid {
    boolean valid() throws DomainException;
}
