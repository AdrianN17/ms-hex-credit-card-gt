package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.card.query.LoadCardBalanceBenefitPort;
import com.bank.credit_card.application.service.query.LoadCardByIdService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadCardByIdServiceConfig {

    @Bean
    LoadCardByIdService loadCardByIdService(LoadCardBalanceBenefitPort loadCardBalanceBenefitPort) {
        return new LoadCardByIdService(loadCardBalanceBenefitPort);
    }
}
