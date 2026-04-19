package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.service.usecase.PaymentService;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBenefit;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.application.service.usecase.business.BusinessServicePayment;
import com.bank.credit_card.domain.payment.factory.PaymentFactory;
import com.bank.credit_card.domain.payment.factory.PaymentFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentServiceConfig {

    @Bean
    PaymentFactory paymentFactory() {
        return new PaymentFactoryImpl();
    }

    @Bean
    PaymentService paymentService(
            BusinessServiceCard businessServiceCard,
            BusinessServiceBalance businessServiceBalance,
            BusinessServiceBenefit businessServiceBenefit,
            BusinessServicePayment businessServicePayment,
            LoadCurrencyPort loadCurrencyPort,
            PaymentFactory paymentFactory) {

        return new PaymentService(businessServiceCard, businessServiceBalance, businessServiceBenefit, businessServicePayment, loadCurrencyPort, paymentFactory);
    }
}

