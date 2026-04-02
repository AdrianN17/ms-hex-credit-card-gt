package com.bank.credit_card.infraestructure.config.nosql.cosmos;

import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.adapter.ConsumptionCosmosRepositoryAdapter;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.ConsumptionPersistanceMapperCosmos;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.ConsumptionPersistanceMapperCosmosImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.ConsumptionQueryMapperCosmos;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.ConsumptionQueryMapperCosmosImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository.ConsumptionCosmosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("new")
@Configuration
public class ConsumptionCosmosRepositoryAdapterConfigCosmos {

    ConsumptionCosmosRepositoryAdapter consumptionCosmosRepositoryAdapter(ConsumptionCosmosRepository consumptionCosmosRepository,
                                                                          ConsumptionPersistanceMapperCosmos consumptionPersistanceMapperCosmos,
                                                                          ConsumptionQueryMapperCosmos consumptionQueryMapperMongo) {
        return new ConsumptionCosmosRepositoryAdapter(consumptionCosmosRepository,
                consumptionPersistanceMapperCosmos,
                consumptionQueryMapperMongo);
    }

    @Bean
    ConsumptionPersistanceMapperCosmos consumptionPersistanceMapper() {
        return new ConsumptionPersistanceMapperCosmosImpl();
    }

    @Bean
    ConsumptionQueryMapperCosmos consumptionQueryMapper() {
        return new ConsumptionQueryMapperCosmosImpl();
    }
}
