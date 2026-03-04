package com.bank.credit_card.infraestructure.config.cosmos;

import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.adapter.ConsumptionCosmosRepositoryAdapter;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.ConsumptionPersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.ConsumptionPersistanceMapperImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.ConsumptionQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.ConsumptionQueryMapperImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository.ConsumptionCosmosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumptionCosmosRepositoryAdapterConfig {

    ConsumptionCosmosRepositoryAdapter consumptionCosmosRepositoryAdapter(ConsumptionCosmosRepository consumptionCosmosRepository,
                                                                          ConsumptionPersistanceMapper consumptionPersistanceMapper,
                                                                          ConsumptionQueryMapper consumptionQueryMapper) {
        return new ConsumptionCosmosRepositoryAdapter(consumptionCosmosRepository,
                consumptionPersistanceMapper,
                consumptionQueryMapper);
    }

    @Bean
    ConsumptionPersistanceMapper consumptionPersistanceMapper() {
        return new ConsumptionPersistanceMapperImpl();
    }

    @Bean
    ConsumptionQueryMapper consumptionQueryMapper() {
        return new ConsumptionQueryMapperImpl();
    }
}
