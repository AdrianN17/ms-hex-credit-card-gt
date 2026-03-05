package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.card.usecase.SaveCardPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.service.usecase.CardCloseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardCloseServiceConfig {

    @Bean
    CardCloseService cardCloseService(
            LoadCardPort loadCardPort,
            SaveBenefitPort saveBenefitPort,
            SaveBalancePort saveBalancePort,
            SaveCardPort saveCardPort,
            LoadCurrencyPort loadCurrencyPort,
            LoadCardCurrencyPort loadCardCurrencyPort) {

        return new CardCloseService(loadCardPort,
                saveBenefitPort,
                saveBalancePort,
                saveCardPort,
                loadCurrencyPort,
                loadCardCurrencyPort);
    }
}

