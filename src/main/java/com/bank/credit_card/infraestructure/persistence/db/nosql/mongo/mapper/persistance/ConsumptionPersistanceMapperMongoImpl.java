package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.ConsumptionEntityMongo;

public class ConsumptionPersistanceMapperMongoImpl implements ConsumptionPersistanceMapperMongo {

    @Override
    public Consumption toDomain(ConsumptionEntityMongo consumptionEntity, Currency currency) {
        return Consumption.create(
                consumptionEntity.getConsumptionId(),
                consumptionEntity.getStatus(),
                consumptionEntity.getCreatedDate(),
                consumptionEntity.getUpdatedDate(),
                Amount.create(currency, consumptionEntity.getAmount()),
                consumptionEntity.getConsumptionDate(),
                consumptionEntity.getConsumptionApprobationDate(),
                CardId.create(consumptionEntity.getCardId()),
                consumptionEntity.getSellerName()
        );
    }

    @Override
    public ConsumptionEntityMongo toEntity(Consumption consumption) {

        return ConsumptionEntityMongo.builder()
                .consumptionId(consumption.getId())
                .amount(consumption.getConsumptionAmount().getAmount())
                .currency(consumption.getConsumptionAmount().getCurrency().getCurrency())
                .consumptionDate(consumption.getConsumptionDate())
                .consumptionApprobationDate(consumption.getConsumptionApprobationDate())
                .sellerName(consumption.getSellerName())
                .cardId(consumption.getCardId().getValue().toString())
                .createdDate(consumption.getCreatedDate())
                .updatedDate(consumption.getUpdatedDate())
                .status(consumption.getStatus())
                .build();
    }
}
