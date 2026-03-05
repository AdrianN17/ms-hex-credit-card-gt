package com.bank.credit_card.infraestructure.persistence.db.generic.mapper;

@FunctionalInterface
public interface GenericDomainMapper<D, E> {
    D toDomain(E entity);
}
