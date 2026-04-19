package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.service.usecase.ConsumptionService;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBenefit;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceConsumption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumptionServiceConfig {

    @Bean
    ConsumptionService consumptionService(
            BusinessServiceCard businessServiceCard,
            BusinessServiceBalance businessServiceBalance,
            BusinessServiceBenefit businessServiceBenefit,
            BusinessServiceConsumption businessServiceConsumption,
            LoadCurrencyPort loadCurrencyPort) {

        return new ConsumptionService(businessServiceCard, businessServiceBalance, businessServiceBenefit, businessServiceConsumption, loadCurrencyPort);
    }
}

