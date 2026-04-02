package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.ConsumptionEntityMongo;
import org.springframework.stereotype.Component;

@Component
public class ConsumptionQueryMapperMongoImpl implements ConsumptionQueryMapperMongo {

    @Override
    public LoadConsumptionView toView(ConsumptionEntityMongo entity) {
        return new LoadConsumptionView(
                entity.getConsumptionId(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getConsumptionDate(),
                entity.getConsumptionApprobationDate(),
                entity.getCardId(),
                entity.getSellerName()
        );
    }
}
