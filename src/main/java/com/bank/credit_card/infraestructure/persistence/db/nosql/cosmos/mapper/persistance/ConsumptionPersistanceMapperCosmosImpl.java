package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.ConsumptionEntityCosmos;

public class ConsumptionPersistanceMapperCosmosImpl implements ConsumptionPersistanceMapperCosmos {

    @Override
    public Consumption toDomain(ConsumptionEntityCosmos consumptionEntity, Currency currency) {
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
    public ConsumptionEntityCosmos toEntity(Consumption consumption) {
        return ConsumptionEntityCosmos.builder()
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
