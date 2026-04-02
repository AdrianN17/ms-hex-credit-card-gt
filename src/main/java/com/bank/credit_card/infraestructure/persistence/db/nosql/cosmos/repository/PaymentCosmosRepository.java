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
            "AND c.status = 'ACTIVE' " +
            "AND (c.paymentDate[0] > @startYear OR (c.paymentDate[0] = @startYear AND c.paymentDate[1] > @startMonth) OR (c.paymentDate[0] = @startYear AND c.paymentDate[1] = @startMonth AND c.paymentDate[2] >= @startDay)) " +
            "AND (c.paymentDate[0] < @endYear OR (c.paymentDate[0] = @endYear AND c.paymentDate[1] < @endMonth) OR (c.paymentDate[0] = @endYear AND c.paymentDate[1] = @endMonth AND c.paymentDate[2] <= @endDay))")
    List<PaymentEntityCosmos> findByCardIdAndPaymentDateBetweenRaw(
            String cardId,
            int startYear, int startMonth, int startDay,
            int endYear, int endMonth, int endDay);

    default List<PaymentEntityCosmos> findByCardIdAndPaymentDateBetween(String cardId, LocalDateTime start, LocalDateTime end) {
        return findByCardIdAndPaymentDateBetweenRaw(
                cardId,
                start.getYear(), start.getMonthValue(), start.getDayOfMonth(),
                end.getYear(), end.getMonthValue(), end.getDayOfMonth());
    }
}
