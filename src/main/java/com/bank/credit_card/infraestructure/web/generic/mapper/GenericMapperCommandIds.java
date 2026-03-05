package com.bank.credit_card.infraestructure.web.generic.mapper;

@FunctionalInterface
public interface GenericMapperCommandIds<C, ID, SUBID> {
    C toCommandId(ID id, SUBID subId);
}
