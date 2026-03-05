package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance;

import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericDomainMapper;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericEntityMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.ConsumptionEntity;

public interface ConsumptionPersistanceMapper extends GenericDomainMapper<Consumption, ConsumptionEntity>,
        GenericEntityMapper<Consumption, ConsumptionEntity> {

}
