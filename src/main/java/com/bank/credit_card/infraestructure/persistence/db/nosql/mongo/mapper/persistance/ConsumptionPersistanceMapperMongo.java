package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericDomainCurrencyMapper;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericEntityMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.ConsumptionEntityMongo;

public interface ConsumptionPersistanceMapperMongo extends GenericDomainCurrencyMapper<Consumption, ConsumptionEntityMongo, Currency>,
        GenericEntityMapper<Consumption, ConsumptionEntityMongo> {

}
