package com.bank.credit_card.infraestructure.config.sqlserver;

import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.adapter.BalanceJpaRepositoryAdapter;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.BalancePersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.BalancePersistanceMapperImpl;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.BalanceJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BalanceJpaRepositoryAdapterConfig {

    @Bean
    BalanceJpaRepositoryAdapter balanceJpaRepositoryAdapter(BalanceJpaRepository balanceJpaRepository,
                                                             BalancePersistanceMapper balancePersistanceMapper) {
        return new BalanceJpaRepositoryAdapter(balanceJpaRepository, balancePersistanceMapper);
    }

    @Bean
    BalancePersistanceMapper balancePersistanceMapper() {
        return new BalancePersistanceMapperImpl();
    }
}
