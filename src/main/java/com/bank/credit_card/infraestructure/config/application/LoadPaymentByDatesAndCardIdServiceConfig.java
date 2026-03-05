package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.payment.query.LoadPaymentsByDatesAndCardIdPort;
import com.bank.credit_card.application.service.query.LoadPaymentByDatesAndCardIdService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadPaymentByDatesAndCardIdServiceConfig {

    @Bean
    LoadPaymentByDatesAndCardIdService loadPaymentByDatesAndCardIdService(
            LoadPaymentsByDatesAndCardIdPort loadPaymentsByDatesAndCardIdPort
    ) {
        return new LoadPaymentByDatesAndCardIdService(loadPaymentsByDatesAndCardIdPort);
    }
}
