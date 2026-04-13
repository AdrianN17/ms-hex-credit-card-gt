package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionCurrencyPort;
import com.bank.credit_card.application.port.out.consumption.usecase.LoadConsumptionPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.service.usecase.SplitConsumptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SplitConsumptionServiceConfig {

    @Bean
    SplitConsumptionService splitConsumptionService(
            LoadCardPort loadCardPort,
            LoadBalancePort loadBalancePort,
            SaveBalancePort saveBalancePort,
            SaveConsumptionPort saveConsumptionPort,
            LoadConsumptionPort loadConsumptionPort,
            LoadCurrencyPort loadCurrencyPort,
            LoadCardCurrencyPort loadCardCurrencyPort,
            LoadConsumptionCurrencyPort loadConsumptionCurrencyPort) {

        return new SplitConsumptionService(loadCardPort,
                loadBalancePort,
                saveBalancePort,
                saveConsumptionPort,
                loadConsumptionPort,
                loadCurrencyPort,
                loadCardCurrencyPort,
                loadConsumptionCurrencyPort);
    }
}

