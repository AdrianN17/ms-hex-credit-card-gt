package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.ConsumptionEntityMongo;

public class ConsumptionPersistanceMapperMongoImpl implements ConsumptionPersistanceMapperMongo {

    @Override
    public Consumption toDomain(ConsumptionEntityMongo consumptionEntity, Currency currency) {
        return Consumption.builder()
                .id(consumptionEntity.getConsumptionId())
                .status(consumptionEntity.getStatus())
                .createdDate(consumptionEntity.getCreatedDate())
                .updatedDate(consumptionEntity.getUpdatedDate())
                .consumptionAmount(consumptionEntity.getAmount(), currency)
                .consumptionApprobation(consumptionEntity.getConsumptionDate(), consumptionEntity.getConsumptionApprobationDate())
                .cardId(CardId.create(consumptionEntity.getCardId()))
                .sellerName(consumptionEntity.getSellerName())
                .build();
    }

    @Override
    public ConsumptionEntityMongo toEntity(Consumption consumption) {
        return ConsumptionEntityMongo.builder()
                .consumptionId(consumption.getId().getValue())
                .amount(consumption.getConsumptionAmount().getAmount())
                .currency(consumption.getConsumptionAmount().getCurrency().getCurrency())
                .consumptionDate(consumption.getConsumptionApprobation().getDate())
                .consumptionApprobationDate(consumption.getConsumptionApprobation().getApprobationDate())
                .sellerName(consumption.getSellerName().getValue())
                .cardId(consumption.getCardId().getValue().toString())
                .createdDate(consumption.getCreatedDate())
                .updatedDate(consumption.getUpdatedDate())
                .status(consumption.getStatus())
                .build();
    }
}
