package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository;

import com.azure.spring.data.cosmos.repository.Query;
import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericCosmosRespository;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntityCosmos;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentCosmosRepository extends GenericCosmosRespository<PaymentEntityCosmos, UUID> {

    @Query("SELECT c.paymentId, c.cardId, c.amount, c.currency, c.paymentDate, c.paymentApprobationDate, c.channel, c.category " +
            "FROM c " +
            "WHERE c.cardId = @cardId " +
            "AND c.paymentDate >= @start " +
            "AND c.paymentDate <= @end")
    List<PaymentEntityCosmos> findByCardIdAndPaymentDateBetween(String cardId, LocalDateTime start, LocalDateTime end);
}


