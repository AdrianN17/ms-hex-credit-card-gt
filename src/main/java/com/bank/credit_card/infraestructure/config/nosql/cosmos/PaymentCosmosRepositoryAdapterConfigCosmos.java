package com.bank.credit_card.infraestructure.config.nosql.cosmos;

import com.bank.credit_card.domain.payment.factory.PaymentFactory;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.adapter.PaymentCosmosRepositoryAdapter;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.PaymentPersistanceMapperCosmos;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.PaymentPersistanceMapperCosmosImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.PaymentQueryMapperCosmos;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.PaymentQueryMapperCosmosImpl;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository.PaymentCosmosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("new")
@Configuration
public class PaymentCosmosRepositoryAdapterConfigCosmos {

    @Bean
    PaymentCosmosRepositoryAdapter paymentCosmosRepositoryAdapter(PaymentCosmosRepository paymentCosmosRepository,
                                                                  PaymentPersistanceMapperCosmos paymentPersistanceMapperCosmos,
                                                                  PaymentQueryMapperCosmos paymentQueryMapperCosmos) {
        return new PaymentCosmosRepositoryAdapter(paymentCosmosRepository,
                paymentPersistanceMapperCosmos,
                paymentQueryMapperCosmos);
    }

    @Bean
    PaymentPersistanceMapperCosmos paymentPersistanceMapperCosmos(PaymentFactory paymentFactory) {
        return new PaymentPersistanceMapperCosmosImpl(paymentFactory);
    }

    @Bean
    PaymentQueryMapperCosmos paymentQueryMapperCosmos() {
        return new PaymentQueryMapperCosmosImpl();
    }
}
