package com.bank.credit_card.infraestructure.config.sql.sqlserver;

import com.bank.credit_card.domain.balance.factory.BalanceFactory;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.adapter.BalanceJpaRepositoryAdapter;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.BalancePersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.BalancePersistanceMapperImpl;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.BalanceJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo.BalanceVOJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BalanceJpaRepositoryAdapterConfig {

    @Bean
    BalanceJpaRepositoryAdapter balanceJpaRepositoryAdapter(BalanceJpaRepository balanceJpaRepository,
                                                            BalanceVOJpaRepository balanceVOJpaRepository,
                                                            BalancePersistanceMapper balancePersistanceMapper) {
        return new BalanceJpaRepositoryAdapter(balanceJpaRepository, balanceVOJpaRepository, balancePersistanceMapper);
    }

    @Bean
    BalancePersistanceMapper balancePersistanceMapper(BalanceFactory balanceFactory) {
        return new BalancePersistanceMapperImpl(balanceFactory);
    }
}
