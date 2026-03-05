package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericDomainMapper;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericEntityMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.CardEntityVO;

public interface CardPersistanceMapper extends GenericEntityMapper<Card, CardEntity>,
        GenericDomainMapper<Card, CardEntityVO> {

}
