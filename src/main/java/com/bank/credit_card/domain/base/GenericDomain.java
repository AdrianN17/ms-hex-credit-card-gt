package com.bank.credit_card.domain.base;

import com.bank.credit_card.domain.exception.DomainException;

import static com.bank.credit_card.domain.base.DomainErrorMessage.INVALID_ID;
import static java.util.Objects.isNull;

public abstract class GenericDomain<T> {

    protected final T id;
    protected StatusEnum status;

    protected GenericDomain(T id) throws DomainException {
        this.isValidId(id);
        this.id = id;
    }

    public T getId() {
        return id;
    }

    protected void isValidId(T id) throws DomainException {
        if (isNull(id)) {
            throw new DomainException(String.format(INVALID_ID, id));
        }
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void softDelete() {
        this.status = StatusEnum.INACTIVE;
    }
}