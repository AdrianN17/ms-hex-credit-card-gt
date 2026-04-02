package com.bank.credit_card.infraestructure.config.nosql.mongo;

import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.adapter.PaymentMongoRepositoryAdapter;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.persistance.PaymentPersistanceMapperMongo;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.persistance.PaymentPersistanceMapperMongoImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.query.PaymentQueryMapperMongo;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.query.PaymentQueryMapperMongoImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.repository.PaymentMongoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("old")
@Configuration
public class PaymentMongoRepositoryAdapterConfigMongo {

    @Bean
    PaymentMongoRepositoryAdapter paymentMongoRepositoryAdapter(PaymentMongoRepository paymentMongoRepository,
                                                                PaymentPersistanceMapperMongo paymentPersistanceMapperMongo,
                                                                PaymentQueryMapperMongo paymentQueryMapperMongo) {
        return new PaymentMongoRepositoryAdapter(paymentMongoRepository,
                paymentPersistanceMapperMongo,
                paymentQueryMapperMongo);
    }

    @Bean
    PaymentPersistanceMapperMongo paymentPersistanceMapperMongo() {
        return new PaymentPersistanceMapperMongoImpl();
    }

    @Bean
    PaymentQueryMapperMongo paymentQueryMapperMongo() {
        return new PaymentQueryMapperMongoImpl();
    }
}
