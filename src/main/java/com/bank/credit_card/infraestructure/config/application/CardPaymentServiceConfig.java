package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.port.out.payment.usecase.SavePaymentPort;
import com.bank.credit_card.application.service.usecase.CardPaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardPaymentServiceConfig {

    @Bean
    CardPaymentService cardPaymentService(
            LoadCardPort loadCardPort,
            SaveBenefitPort saveBenefitPort,
            SaveBalancePort saveBalancePort,
            SavePaymentPort savePaymentPort,
            LoadCurrencyPort loadCurrencyPort,
            LoadCardCurrencyPort loadCardCurrencyPort) {

        return new CardPaymentService(loadCardPort,
                saveBenefitPort,
                saveBalancePort,
                savePaymentPort,
                loadCurrencyPort,
                loadCardCurrencyPort);
    }
}

