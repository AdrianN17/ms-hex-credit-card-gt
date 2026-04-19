package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.adapter;

import com.bank.credit_card.application.port.in.query.criteria.FindConsumptionByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionCurrencyPort;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionsByDatesAndCardIdPort;
import com.bank.credit_card.application.port.out.consumption.usecase.LoadConsumptionPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.domain.base.enums.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.ConsumptionEntityCosmos;
import com.bank.credit_card.infraestructure.persistence.db.nosql.common.exception.ConsumptionPersistanceException;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.ConsumptionPersistanceMapperCosmos;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.ConsumptionQueryMapperCosmos;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository.ConsumptionCosmosRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.bank.credit_card.infraestructure.persistence.db.nosql.common.constant.TimeConstant.*;
import static com.bank.credit_card.infraestructure.persistence.db.nosql.common.exception.ConsumptionErrorMessage.*;

public class ConsumptionCosmosRepositoryAdapter implements LoadConsumptionPort, SaveConsumptionPort, LoadConsumptionsByDatesAndCardIdPort, LoadConsumptionCurrencyPort {

    private final ConsumptionCosmosRepository consumptionCosmosRepository;
    private final ConsumptionPersistanceMapperCosmos consumptionPersistanceMapperCosmos;
    private final ConsumptionQueryMapperCosmos consumptionQueryMapperCosmos;

    public ConsumptionCosmosRepositoryAdapter(ConsumptionCosmosRepository consumptionCosmosRepository, ConsumptionPersistanceMapperCosmos consumptionPersistanceMapperCosmos, ConsumptionQueryMapperCosmos consumptionQueryMapperCosmos) {
        this.consumptionCosmosRepository = consumptionCosmosRepository;
        this.consumptionPersistanceMapperCosmos = consumptionPersistanceMapperCosmos;
        this.consumptionQueryMapperCosmos = consumptionQueryMapperCosmos;
    }

    @Override
    public List<LoadConsumptionView> load(FindConsumptionByDatesAndCardIdCriteria criteria) {

        return Optional.of(consumptionCosmosRepository.findByCardIdAndConsumptionDateBetween(
                        String.valueOf(criteria.cardId()),
                        criteria.start().atStartOfDay(),
                        criteria.end().atTime(LAST_HOUR, LAST_MINUTE, LAST_SECOND)))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ConsumptionPersistanceException(NO_CONSUMPTIONS_FOUND))
                .stream()
                .map(consumptionQueryMapperCosmos::toView)
                .toList();

    }

    @Override
    public Optional<UUID> save(Consumption consumption) {

        return Optional.of(Optional.of(consumption)
                .map(consumptionPersistanceMapperCosmos::toEntity)
                .map(consumptionCosmosRepository::save)
                .map(ConsumptionEntityCosmos::getConsumptionId)
                .orElseThrow(() -> new ConsumptionPersistanceException(CONSUMPTION_NOT_SAVED)));

    }

    @Override
    public Optional<Consumption> load(UUID consumptionId, String cardId, Currency currency) {
        return Optional.of(consumptionCosmosRepository.findActiveById(consumptionId, cardId)
                        .orElseThrow(() -> new ConsumptionPersistanceException(CONSUMPTION_NOT_FOUND)))
                .map(consumption -> consumptionPersistanceMapperCosmos.toDomain(consumption, currency));
    }

    @Override
    public Optional<CurrencyEnum> load(UUID consumptionId, String cardId) {
        return Optional.of(consumptionCosmosRepository.findActiveById(consumptionId, cardId)
                        .orElseThrow(() -> new ConsumptionPersistanceException(CONSUMPTION_NOT_FOUND)))
                .map(ConsumptionEntityCosmos::getCurrency);
    }
}
