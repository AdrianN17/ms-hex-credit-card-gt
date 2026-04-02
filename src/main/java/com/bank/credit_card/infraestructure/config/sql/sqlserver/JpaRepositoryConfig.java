package com.bank.credit_card.infraestructure.config.sql.sqlserver;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository"
)
@EntityScan(
        basePackages = "com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity"
)
public class JpaRepositoryConfig {
}
