package com.bank.credit_card.infraestructure.persistence.db.generic.mapper;

@FunctionalInterface
public interface GenericEntityMapper<D, E> {
    E toEntity(D domain);
}
