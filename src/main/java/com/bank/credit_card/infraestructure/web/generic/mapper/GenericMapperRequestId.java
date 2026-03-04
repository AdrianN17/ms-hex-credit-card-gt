package com.bank.credit_card.infraestructure.web.generic.mapper;

public interface GenericMapperRequestId<C, ID, R> {
    C toCommand(R request, ID id);
}
