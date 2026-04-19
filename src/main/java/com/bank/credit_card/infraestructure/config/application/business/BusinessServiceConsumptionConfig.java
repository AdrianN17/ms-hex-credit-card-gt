package com.bank.credit_card.infraestructure.config.application.business;

import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionCurrencyPort;
import com.bank.credit_card.application.port.out.consumption.usecase.LoadConsumptionPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceConsumption;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceConsumptionImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessServiceConsumptionConfig {

    @Bean
    BusinessServiceConsumption businessServiceConsumption(
            LoadCurrencyPort loadCurrencyPort,
            LoadConsumptionPort loadConsumptionPort,
            SaveConsumptionPort saveConsumptionPort,
            LoadConsumptionCurrencyPort loadConsumptionCurrencyPort) {
        return new BusinessServiceConsumptionImpl(loadCurrencyPort, loadConsumptionPort, saveConsumptionPort, loadConsumptionCurrencyPort);
    }
}

