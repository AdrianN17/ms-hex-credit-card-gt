package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.service.usecase.CardCloseService;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBenefit;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardCloseServiceConfig {

    @Bean
    CardCloseService cardCloseService(
            BusinessServiceCard businessServiceCard,
            BusinessServiceBalance businessServiceBalance,
            BusinessServiceBenefit businessServiceBenefit) {

        return new CardCloseService(businessServiceCard, businessServiceBalance, businessServiceBenefit);
    }
}
