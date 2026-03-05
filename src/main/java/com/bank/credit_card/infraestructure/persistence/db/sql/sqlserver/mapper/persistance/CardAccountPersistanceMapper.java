package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericEntityMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardAccountEntity;

public interface CardAccountPersistanceMapper extends GenericEntityMapper<Card, CardAccountEntity> {

}
