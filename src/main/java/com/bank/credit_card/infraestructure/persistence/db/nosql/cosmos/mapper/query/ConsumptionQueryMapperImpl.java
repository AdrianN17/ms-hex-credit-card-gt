package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.ConsumptionEntity;
import org.springframework.stereotype.Component;

@Component
public class ConsumptionQueryMapperImpl implements ConsumptionQueryMapper {

    @Override
    public LoadConsumptionView toView(ConsumptionEntity entity) {
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
