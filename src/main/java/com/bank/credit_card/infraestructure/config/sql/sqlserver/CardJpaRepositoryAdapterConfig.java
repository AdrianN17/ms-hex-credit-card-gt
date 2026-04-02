package com.bank.credit_card.infraestructure.config.sql.sqlserver;

import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.adapter.CardJpaRepositoryAdapter;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.*;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.query.CardQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.query.CardQueryMapperImpl;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.CardAccountJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.CardJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo.CardVOJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardJpaRepositoryAdapterConfig {

    @Bean
    CardJpaRepositoryAdapter cardJpaRepositoryAdapter(CardJpaRepository cardJpaRepository,
                                                      CardVOJpaRepository cardVOJpaRepository,
                                                      CardPersistanceMapper cardPersistanceMapper,
                                                      CardAccountJpaRepository cardAccountJpaRepository,
                                                      CardAccountPersistanceMapper cardAccountPersistanceMapper,
                                                      CardQueryMapper cardQueryMapper) {
        return new CardJpaRepositoryAdapter(cardJpaRepository,
                cardVOJpaRepository,
                cardPersistanceMapper,
                cardAccountJpaRepository,
                cardAccountPersistanceMapper,
                cardQueryMapper);
    }

    @Bean
    CardPersistanceMapper cardPersistanceMapper(BalancePersistanceMapper balancePersistanceMapper,
                                                BenefitPersistanceMapper benefitPersistanceMapper) {
        return new CardPersistanceMapperImpl(balancePersistanceMapper, benefitPersistanceMapper);
    }

    @Bean
    CardQueryMapper cardQueryMapper() {
        return new CardQueryMapperImpl();
    }

    @Bean
    CardAccountPersistanceMapper cardAccountPersistanceMapper() {
        return new CardAccountPersistanceMapperImpl();
    }
}
