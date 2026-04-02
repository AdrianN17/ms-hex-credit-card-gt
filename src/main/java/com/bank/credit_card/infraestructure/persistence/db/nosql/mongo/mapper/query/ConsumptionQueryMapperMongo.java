package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.ConsumptionEntityMongo;

public interface ConsumptionQueryMapperMongo extends GenericQueryMapper<LoadConsumptionView, ConsumptionEntityMongo> {
}
