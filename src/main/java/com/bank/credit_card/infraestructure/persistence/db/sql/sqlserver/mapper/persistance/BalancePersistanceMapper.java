package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.card.Balance;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BalanceEntity;

public interface BalancePersistanceMapper extends GenericMapper<Balance, BalanceEntity> {

}
