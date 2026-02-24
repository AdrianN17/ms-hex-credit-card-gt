package com.bank.credit_card.domain.base;

import com.bank.credit_card.domain.exception.DomainException;

public abstract class GenericDomain implements IsValid {

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
        if (id == null || id <= 0) {
            throw new DomainException(String.format("El id = %d no es válido", id));
        }
    }
}