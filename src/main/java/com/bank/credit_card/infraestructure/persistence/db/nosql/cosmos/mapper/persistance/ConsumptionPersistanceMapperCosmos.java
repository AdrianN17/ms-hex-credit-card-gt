package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericDomainCurrencyMapper;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericEntityMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.ConsumptionEntityCosmos;

public interface ConsumptionPersistanceMapperCosmos extends GenericDomainCurrencyMapper<Consumption, ConsumptionEntityCosmos, Currency>,
        GenericEntityMapper<Consumption, ConsumptionEntityCosmos> {

}
