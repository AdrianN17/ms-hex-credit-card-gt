package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.adapter;

import com.bank.credit_card.application.port.in.query.criteria.FindConsumptionByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionCurrencyPort;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionsByDatesAndCardIdPort;
import com.bank.credit_card.application.port.out.consumption.usecase.LoadConsumptionPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.ConsumptionEntity;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.exception.ConsumptionPersistanceException;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.ConsumptionPersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.ConsumptionQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository.ConsumptionCosmosRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.constant.TimeConstant.*;
import static com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.exception.ConsumptionErrorMessage.*;

public class ConsumptionCosmosRepositoryAdapter implements LoadConsumptionPort, SaveConsumptionPort, LoadConsumptionsByDatesAndCardIdPort, LoadConsumptionCurrencyPort {

    private final ConsumptionCosmosRepository consumptionCosmosRepository;
    private final ConsumptionPersistanceMapper consumptionPersistanceMapper;
    private final ConsumptionQueryMapper consumptionQueryMapper;

    public ConsumptionCosmosRepositoryAdapter(ConsumptionCosmosRepository consumptionCosmosRepository, ConsumptionPersistanceMapper consumptionPersistanceMapper, ConsumptionQueryMapper consumptionQueryMapper) {
        this.consumptionCosmosRepository = consumptionCosmosRepository;
        this.consumptionPersistanceMapper = consumptionPersistanceMapper;
        this.consumptionQueryMapper = consumptionQueryMapper;
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
                .map(consumptionQueryMapper::toView)
                .toList();

    }

    @Override
    public Optional<UUID> save(Consumption consumption) {

        return Optional.of(Optional.of(consumption)
                .map(consumptionPersistanceMapper::toEntity)
                .map(consumptionCosmosRepository::save)
                .map(ConsumptionEntity::getConsumptionId)
                .orElseThrow(() -> new ConsumptionPersistanceException(CONSUMPTION_NOT_SAVED)));

    }

    @Override
    public Optional<Consumption> load(UUID consumptionId, Currency currency) {

        return Optional.of(consumptionCosmosRepository.findById(consumptionId)
                        .orElseThrow(() -> new ConsumptionPersistanceException(CONSUMPTION_NOT_FOUND)))
                .map(consumption -> consumptionPersistanceMapper.toDomain(consumption, currency));

    }

    @Override
    public Optional<CurrencyEnum> load(UUID consumptionId) {
        return Optional.of(consumptionCosmosRepository.findById(consumptionId)
                        .orElseThrow(() -> new ConsumptionPersistanceException(CONSUMPTION_NOT_FOUND)))
                .map(ConsumptionEntity::getCurrency);
    }
}
