package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository;

import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericCosmosRespository;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.ConsumptionEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsumptionCosmosRepository extends GenericCosmosRespository<ConsumptionEntity, UUID> {

    List<ConsumptionEntity> findByCardIdAndConsumptionDateBetween(String cardId, LocalDateTime start, LocalDateTime end);
}
