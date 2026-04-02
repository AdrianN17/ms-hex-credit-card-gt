package com.bank.credit_card.infraestructure.config.nosql.mongo;

import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.adapter.ConsumptionMongoRepositoryAdapter;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.persistance.ConsumptionPersistanceMapperMongo;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.persistance.ConsumptionPersistanceMapperMongoImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.query.ConsumptionQueryMapperMongo;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.query.ConsumptionQueryMapperMongoImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.repository.ConsumptionMongoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("old")
@Configuration
public class ConsumptionMongoRepositoryAdapterConfigMongo {

    @Bean
    ConsumptionMongoRepositoryAdapter consumptionMongoRepositoryAdapter(ConsumptionMongoRepository consumptionMongoRepository,
                                                                        ConsumptionPersistanceMapperMongo consumptionPersistanceMapperMongo,
                                                                        ConsumptionQueryMapperMongo consumptionQueryMapperMongo) {
        return new ConsumptionMongoRepositoryAdapter(consumptionMongoRepository,
                consumptionPersistanceMapperMongo,
                consumptionQueryMapperMongo);
    }

    @Bean
    ConsumptionPersistanceMapperMongo consumptionPersistanceMapperMongo() {
        return new ConsumptionPersistanceMapperMongoImpl();
    }

    @Bean
    ConsumptionQueryMapperMongo consumptionQueryMapperMongo() {
        return new ConsumptionQueryMapperMongoImpl();
    }
}
