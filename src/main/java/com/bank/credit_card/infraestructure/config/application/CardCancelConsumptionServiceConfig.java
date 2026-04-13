package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionCurrencyPort;
import com.bank.credit_card.application.port.out.consumption.usecase.LoadConsumptionPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.service.usecase.CardCancelConsumptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardCancelConsumptionServiceConfig {

    @Bean
    CardCancelConsumptionService cardCancelConsumptionService(
            LoadCardPort loadCardPort,
            LoadBalancePort loadBalancePort,
            LoadConsumptionPort loadConsumptionPort,
            SaveBalancePort saveBalancePort,
            SaveConsumptionPort saveConsumptionPort,
            LoadCurrencyPort loadCurrencyPort,
            LoadCardCurrencyPort loadCardCurrencyPort,
            LoadConsumptionCurrencyPort loadConsumptionCurrencyPort) {

        return new CardCancelConsumptionService(loadCardPort,
                loadBalancePort,
                loadConsumptionPort,
                saveBalancePort,
                saveConsumptionPort,
                loadCurrencyPort,
                loadCardCurrencyPort,
                loadConsumptionCurrencyPort);
    }
}

