package com.bank.credit_card.domain.generic;

import com.bank.credit_card.domain.base.StatusEnum;
import com.bank.credit_card.domain.exception.DomainException;

import java.time.LocalDateTime;

import static com.bank.credit_card.domain.base.DomainErrorMessage.INVALID_ID;
import static java.util.Objects.isNull;

public abstract class GenericDomain<T> {

    protected final T id;
    protected StatusEnum status;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;

    protected GenericDomain(T id, StatusEnum status, LocalDateTime createdDate, LocalDateTime updatedDate) throws DomainException {
        this.isValidId(id);
        this.id = id;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void softDelete() {
        this.status = StatusEnum.INACTIVE;
        this.updatedDate = LocalDateTime.now();
    }


}