package com.bank.credit_card.infraestructure.web.generic.mapper;

@FunctionalInterface
public interface GenericMapperRequest<C, R> {
    C toCommand(R request);
}
