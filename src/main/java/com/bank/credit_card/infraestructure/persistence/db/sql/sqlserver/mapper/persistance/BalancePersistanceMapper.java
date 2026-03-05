package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.Balance;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericDomainCurrencyMapper;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericEntityMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BalanceEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.BalanceEntityVO;

public interface BalancePersistanceMapper extends GenericDomainCurrencyMapper<Balance, BalanceEntityVO, Currency>,
        GenericEntityMapper<Balance, BalanceEntity> {

}
