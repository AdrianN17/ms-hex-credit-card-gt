package com.bank.credit_card.infraestructure.config.application.business;

import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.port.out.payment.query.LoadPaymentCurrencyPort;
import com.bank.credit_card.application.port.out.payment.usecase.LoadPaymentPort;
import com.bank.credit_card.application.port.out.payment.usecase.SavePaymentPort;
import com.bank.credit_card.application.service.usecase.business.BusinessServicePayment;
import com.bank.credit_card.application.service.usecase.business.BusinessServicePaymentImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessServicePaymentConfig {

    @Bean
    BusinessServicePayment businessServicePayment(
            LoadCurrencyPort loadCurrencyPort,
            LoadPaymentPort loadPaymentPort,
            SavePaymentPort savePaymentPort,
            LoadPaymentCurrencyPort loadPaymentCurrencyPort) {
        return new BusinessServicePaymentImpl(loadCurrencyPort, loadPaymentPort, savePaymentPort, loadPaymentCurrencyPort);
    }
}

