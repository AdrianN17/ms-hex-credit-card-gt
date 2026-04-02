package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository;

import com.azure.spring.data.cosmos.repository.Query;
import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericCosmosRespository;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.ConsumptionEntityCosmos;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsumptionCosmosRepository extends GenericCosmosRespository<ConsumptionEntityCosmos, UUID> {

    @Query("SELECT c.consumptionId, c.cardId, c.sellerName, c.currency, c.amount, c.consumptionDate, c.consumptionApprobationDate " +
            "FROM c " +
            "WHERE c.cardId = @cardId " +
            "AND c.status = 'ACTIVE' " +
            "AND (c.consumptionDate[0] > @startYear OR (c.consumptionDate[0] = @startYear AND c.consumptionDate[1] > @startMonth) OR (c.consumptionDate[0] = @startYear AND c.consumptionDate[1] = @startMonth AND c.consumptionDate[2] >= @startDay)) " +
            "AND (c.consumptionDate[0] < @endYear OR (c.consumptionDate[0] = @endYear AND c.consumptionDate[1] < @endMonth) OR (c.consumptionDate[0] = @endYear AND c.consumptionDate[1] = @endMonth AND c.consumptionDate[2] <= @endDay))")
    List<ConsumptionEntityCosmos> findByCardIdAndConsumptionDateBetweenRaw(
            String cardId,
            int startYear, int startMonth, int startDay,
            int endYear, int endMonth, int endDay);

    default List<ConsumptionEntityCosmos> findByCardIdAndConsumptionDateBetween(String cardId, LocalDateTime start, LocalDateTime end) {
        return findByCardIdAndConsumptionDateBetweenRaw(
                cardId,
                start.getYear(), start.getMonthValue(), start.getDayOfMonth(),
                end.getYear(), end.getMonthValue(), end.getDayOfMonth());
    }
}
