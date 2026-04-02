package com.bank.credit_card.infraestructure.config.generator;

import com.bank.credit_card.infraestructure.generator.SnowflakeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeGeneratorConfig {

    @Bean
    SnowflakeGenerator snowflakeGenerator() {
        return new SnowflakeGenerator();
    }
}
