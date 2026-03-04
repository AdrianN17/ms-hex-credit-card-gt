package com.bank.credit_card.infraestructure.config.cosmos;

import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.adapter.PaymentCosmosRepositoryAdapter;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.PaymentPersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.PaymentPersistanceMapperImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.PaymentQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.PaymentQueryMapperImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository.PaymentCosmosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentCosmosRepositoryAdapterConfig {

    @Bean
    PaymentCosmosRepositoryAdapter paymentCosmosRepositoryAdapter(PaymentCosmosRepository paymentCosmosRepository,
                                                                   PaymentPersistanceMapper paymentPersistanceMapper,
                                                                   PaymentQueryMapper paymentQueryMapper) {
        return new PaymentCosmosRepositoryAdapter(paymentCosmosRepository,
                paymentPersistanceMapper,
                paymentQueryMapper);
    }

    @Bean
    PaymentPersistanceMapper paymentPersistanceMapper() {
        return new PaymentPersistanceMapperImpl();
    }

    @Bean
    PaymentQueryMapper paymentQueryMapper() {
        return new PaymentQueryMapperImpl();
    }
}
