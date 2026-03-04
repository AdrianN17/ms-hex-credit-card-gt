package com.bank.credit_card.infraestructure.persistence.db.generic.mapper;

public interface GenericMapper<D, E> {
    D toDomain(E entity);

    E toEntity(D domain);
}
