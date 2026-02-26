package com.bank.credit_card.domain.base;

import com.bank.credit_card.domain.exception.DomainException;

import static com.bank.credit_card.domain.base.DomainErrorMessage.INVALID_ID;
import static java.util.Objects.isNull;

public abstract class GenericDomain {

    protected Long id;

    protected GenericDomain(Long id) throws DomainException {
        this.isValidId(id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.isValidId(id);
        this.id = id;
    }

    protected void isValidId(Long id) throws DomainException {
        if (isNull(id) || id <= 0) {
            throw new DomainException(String.format(INVALID_ID, id));
        }
    }
}