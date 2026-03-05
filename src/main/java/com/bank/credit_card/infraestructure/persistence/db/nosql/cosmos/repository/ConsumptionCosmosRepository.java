package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository;

import com.azure.spring.data.cosmos.repository.Query;
import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericCosmosRespository;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.ConsumptionEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsumptionCosmosRepository extends GenericCosmosRespository<ConsumptionEntity, UUID> {

    @Query("SELECT c.consumptionId, c.cardId, c.sellerName, c.currency, c.amount, c.consumptionDate, c.consumptionApprobationDate " +
            "FROM c " +
            "WHERE c.cardId = @cardId " +
            "AND c.consumptionDate >= @start " +
            "AND c.consumptionDate <= @end")
    List<ConsumptionEntity> findByCardIdAndConsumptionDateBetween(String cardId, LocalDateTime start, LocalDateTime end);
}
