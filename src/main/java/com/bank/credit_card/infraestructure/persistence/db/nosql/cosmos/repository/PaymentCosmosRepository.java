package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository;

import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntity;
import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericCosmosRespository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentCosmosRepository extends GenericCosmosRespository<PaymentEntity, UUID> {
    List<PaymentEntity> findByCardIdAndPaymentDateBetween(String cardId, LocalDateTime start, LocalDateTime end);
}
