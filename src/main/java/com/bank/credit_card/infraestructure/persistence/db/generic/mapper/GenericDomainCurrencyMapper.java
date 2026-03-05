package com.bank.credit_card.infraestructure.persistence.db.generic.mapper;

import com.bank.credit_card.domain.base.vo.Currency;

@FunctionalInterface
public interface GenericDomainCurrencyMapper<D, E, C extends Currency> {
    D toDomain(E entity, C currency);
}