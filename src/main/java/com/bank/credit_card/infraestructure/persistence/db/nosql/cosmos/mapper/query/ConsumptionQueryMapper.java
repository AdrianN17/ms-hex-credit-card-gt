package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.ConsumptionEntity;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericQueryMapper;

public interface ConsumptionQueryMapper extends GenericQueryMapper<LoadConsumptionView, ConsumptionEntity> {
}
