package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.consumption.usecase.LoadConsumptionPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.application.service.usecase.CardCancelConsumptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardCancelConsumptionServiceConfig {

    @Bean
    CardCancelConsumptionService cardCancelConsumptionService(
            LoadCardPort loadCardPort,
            LoadConsumptionPort loadConsumptionPort,
            SaveBenefitPort saveBenefitPort,
            SaveBalancePort saveBalancePort,
            SaveConsumptionPort saveConsumptionPort) {

        return new CardCancelConsumptionService(loadCardPort,
                loadConsumptionPort,
                saveBenefitPort,
                saveBalancePort,
                saveConsumptionPort);
    }
}

