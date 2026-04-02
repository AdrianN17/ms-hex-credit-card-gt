package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.repository;

import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericMongoRespository;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.ConsumptionEntityMongo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsumptionMongoRepository extends GenericMongoRespository<ConsumptionEntityMongo, UUID> {

    @Query("{ 'cardId': ?0, 'consumptionDate': { $gte: ?1, $lte: ?2 } }")
    List<ConsumptionEntityMongo> findByCardIdAndConsumptionDateBetween(String cardId, LocalDateTime start, LocalDateTime end);
}
