package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.service.usecase.CancelPaymentService;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.application.service.usecase.business.BusinessServicePayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CancelPaymentServiceConfig {

    @Bean
    CancelPaymentService cancelPaymentService(
            BusinessServiceCard businessServiceCard,
            BusinessServiceBalance businessServiceBalance,
            BusinessServicePayment businessServicePayment) {

        return new CancelPaymentService(businessServiceCard, businessServiceBalance, businessServicePayment);
    }
}
