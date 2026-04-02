package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.adapter;

import com.bank.credit_card.application.port.in.query.criteria.FindConsumptionByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionCurrencyPort;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionsByDatesAndCardIdPort;
import com.bank.credit_card.application.port.out.consumption.usecase.LoadConsumptionPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.ConsumptionEntityMongo;
import com.bank.credit_card.infraestructure.persistence.db.nosql.common.exception.ConsumptionPersistanceException;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.persistance.ConsumptionPersistanceMapperMongo;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.query.ConsumptionQueryMapperMongo;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.repository.ConsumptionMongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.bank.credit_card.infraestructure.persistence.db.nosql.common.constant.TimeConstant.*;
import static com.bank.credit_card.infraestructure.persistence.db.nosql.common.exception.ConsumptionErrorMessage.*;

public class ConsumptionMongoRepositoryAdapter implements LoadConsumptionPort, SaveConsumptionPort, LoadConsumptionsByDatesAndCardIdPort, LoadConsumptionCurrencyPort {

    private final ConsumptionMongoRepository consumptionMongoRepository;
    private final ConsumptionPersistanceMapperMongo consumptionPersistanceMapperMongo;
    private final ConsumptionQueryMapperMongo consumptionQueryMapperMongo;

    public ConsumptionMongoRepositoryAdapter(ConsumptionMongoRepository consumptionMongoRepository, ConsumptionPersistanceMapperMongo consumptionPersistanceMapperMongo, ConsumptionQueryMapperMongo consumptionQueryMapperMongo) {
        this.consumptionMongoRepository = consumptionMongoRepository;
        this.consumptionPersistanceMapperMongo = consumptionPersistanceMapperMongo;
        this.consumptionQueryMapperMongo = consumptionQueryMapperMongo;
    }

    @Override
    public List<LoadConsumptionView> load(FindConsumptionByDatesAndCardIdCriteria criteria) {

        return Optional.of(consumptionMongoRepository.findByCardIdAndConsumptionDateBetween(
                        String.valueOf(criteria.cardId()),
                        criteria.start().atStartOfDay(),
                        criteria.end().atTime(LAST_HOUR, LAST_MINUTE, LAST_SECOND)))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ConsumptionPersistanceException(NO_CONSUMPTIONS_FOUND))
                .stream()
                .map(consumptionQueryMapperMongo::toView)
                .toList();

    }

    @Override
    public Optional<UUID> save(Consumption consumption) {

        return Optional.of(Optional.of(consumption)
                .map(consumptionPersistanceMapperMongo::toEntity)
                .map(consumptionMongoRepository::save)
                .map(ConsumptionEntityMongo::getConsumptionId)
                .orElseThrow(() -> new ConsumptionPersistanceException(CONSUMPTION_NOT_SAVED)));

    }

    @Override
    public Optional<Consumption> load(UUID consumptionId, Currency currency) {

        return Optional.of(consumptionMongoRepository.findById(consumptionId)
                        .orElseThrow(() -> new ConsumptionPersistanceException(CONSUMPTION_NOT_FOUND)))
                .map(consumption -> consumptionPersistanceMapperMongo.toDomain(consumption, currency));

    }

    @Override
    public Optional<CurrencyEnum> load(UUID consumptionId) {
        return Optional.of(consumptionMongoRepository.findById(consumptionId)
                        .orElseThrow(() -> new ConsumptionPersistanceException(CONSUMPTION_NOT_FOUND)))
                .map(ConsumptionEntityMongo::getCurrency);
    }
}
