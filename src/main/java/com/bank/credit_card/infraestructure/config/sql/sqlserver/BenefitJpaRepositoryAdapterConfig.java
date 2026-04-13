package com.bank.credit_card.infraestructure.config.sql.sqlserver;

import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.adapter.BenefitJpaRepositoryAdapter;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.BenefitPersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.BenefitPersistanceMapperImpl;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.BenefitJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo.BenefitVOJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BenefitJpaRepositoryAdapterConfig {

    @Bean
    BenefitJpaRepositoryAdapter benefitJpaRepositoryAdapter(BenefitJpaRepository benefitJpaRepository,
                                                            BenefitVOJpaRepository benefitVOJpaRepository,
                                                            BenefitPersistanceMapper benefitPersistanceMapper) {
        return new BenefitJpaRepositoryAdapter(benefitJpaRepository, benefitVOJpaRepository, benefitPersistanceMapper);
    }

    @Bean
    BenefitPersistanceMapper benefitPersistanceMapper() {
        return new BenefitPersistanceMapperImpl();
    }
}
