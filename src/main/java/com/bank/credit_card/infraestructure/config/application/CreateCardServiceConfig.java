package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.usecase.SaveCardPort;
import com.bank.credit_card.application.service.usecase.CreateCardService;
import com.bank.credit_card.domain.generator.CardIdGenerator;
import com.bank.credit_card.infraestructure.generator.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateCardServiceConfig {


    @Bean
    CreateCardService createCardService(
            SaveCardPort saveCardPort,
            SaveBalancePort saveBalancePort,
            SaveBenefitPort saveBenefitPort,
            CardIdGenerator idGenerator) {

        return new CreateCardService(saveCardPort,
                saveBalancePort,
                saveBenefitPort,
                idGenerator);
    }

    @Bean
    CardIdGenerator cardIdGenerator() {
        return new SnowflakeIdGenerator();
    }
}
