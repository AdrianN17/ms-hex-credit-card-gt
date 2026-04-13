package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.port.out.payment.query.LoadPaymentCurrencyPort;
import com.bank.credit_card.application.port.out.payment.usecase.LoadPaymentPort;
import com.bank.credit_card.application.port.out.payment.usecase.SavePaymentPort;
import com.bank.credit_card.application.service.usecase.CardCancelPaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardCancelPaymentServiceConfig {

    @Bean
    CardCancelPaymentService cardCancelPaymentService(
            LoadCardPort loadCardPort,
            LoadBalancePort loadBalancePort,
            LoadPaymentPort loadPaymentPort,
            SaveBalancePort saveBalancePort,
            SavePaymentPort savePaymentPort,
            LoadCurrencyPort loadCurrencyPort,
            LoadCardCurrencyPort loadCardCurrencyPort,
            LoadPaymentCurrencyPort loadConsumptionCurrencyPort) {

        return new CardCancelPaymentService(loadCardPort,
                loadBalancePort,
                loadPaymentPort,
                saveBalancePort,
                savePaymentPort,
                loadCurrencyPort,
                loadCardCurrencyPort,
                loadConsumptionCurrencyPort);
    }
}
