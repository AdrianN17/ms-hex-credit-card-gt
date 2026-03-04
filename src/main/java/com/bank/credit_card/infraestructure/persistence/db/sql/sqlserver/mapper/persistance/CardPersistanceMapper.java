package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardEntity;

public interface CardPersistanceMapper extends GenericMapper<Card, CardEntity> {

}
