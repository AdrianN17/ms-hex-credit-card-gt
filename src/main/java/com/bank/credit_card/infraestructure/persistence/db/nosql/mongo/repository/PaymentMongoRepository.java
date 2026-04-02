package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.repository;

import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericMongoRespository;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.PaymentEntityMongo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentMongoRepository extends GenericMongoRespository<PaymentEntityMongo, UUID> {

    @Query("{ 'cardId': ?0, 'status': 'ACTIVE', 'paymentDate': { $gte: ?1, $lte: ?2 } }")
    List<PaymentEntityMongo> findByCardIdAndPaymentDateBetween(String cardId, LocalDateTime start, LocalDateTime end);
}


