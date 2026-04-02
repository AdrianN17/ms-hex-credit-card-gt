package com.bank.credit_card.infraestructure.web.generic.mapper;

public interface GenericMapperCommandIdsR <C, ID, SUBID, R>{
    C toCommandIdR(ID id, SUBID subId, R re);
}
