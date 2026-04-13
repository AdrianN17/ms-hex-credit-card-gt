package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.LoadBenefitPort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.service.usecase.CardConsumptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardConsumptionServiceConfig {

    @Bean
    CardConsumptionService cardConsumptionService(
            LoadCardPort loadCardPort,
            LoadBalancePort loadBalancePort,
            LoadBenefitPort loadBenefitPort,
            SaveBenefitPort saveBenefitPort,
            SaveBalancePort saveBalancePort,
            SaveConsumptionPort saveConsumptionPort,
            LoadCurrencyPort loadCurrencyPort,
            LoadCardCurrencyPort loadCardCurrencyPort) {

        return new CardConsumptionService(loadCardPort,
                loadBalancePort,
                loadBenefitPort,
                saveBenefitPort,
                saveBalancePort,
                saveConsumptionPort,
                loadCurrencyPort,
                loadCardCurrencyPort);
    }
}

