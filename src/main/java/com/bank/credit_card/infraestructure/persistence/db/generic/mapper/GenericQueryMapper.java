package com.bank.credit_card.infraestructure.persistence.db.generic.mapper;

@FunctionalInterface
public interface GenericQueryMapper<V, E> {
    V toView(E entity);
}
