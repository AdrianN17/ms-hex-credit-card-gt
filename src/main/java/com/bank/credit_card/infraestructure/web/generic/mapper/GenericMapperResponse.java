package com.bank.credit_card.infraestructure.web.generic.mapper;

@FunctionalInterface
public interface GenericMapperResponse<R, V> {
    R toResponse(V view);
}
