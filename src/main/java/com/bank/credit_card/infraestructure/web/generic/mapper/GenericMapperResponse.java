package com.bank.credit_card.infraestructure.web.generic.mapper;

public interface GenericMapperResponse<R, V> {
    R toResponse(V view);
}
