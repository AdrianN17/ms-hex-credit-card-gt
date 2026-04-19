package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.service.usecase.CancelConsumptionService;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceConsumption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CancelConsumptionServiceConfig {

    @Bean
    CancelConsumptionService cancelConsumptionService(
            BusinessServiceCard businessServiceCard,
            BusinessServiceBalance businessServiceBalance,
            BusinessServiceConsumption businessServiceConsumption) {

        return new CancelConsumptionService(businessServiceCard, businessServiceBalance, businessServiceConsumption);
    }
}

