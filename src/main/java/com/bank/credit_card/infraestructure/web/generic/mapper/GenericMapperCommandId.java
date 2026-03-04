package com.bank.credit_card.infraestructure.web.generic.mapper;

public interface GenericMapperCommandId<C, ID> {
    C toCommandId(ID id);
}
