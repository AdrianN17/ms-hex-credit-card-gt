package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionsByDatesAndCardIdPort;
import com.bank.credit_card.application.service.query.LoadConsumptionByDatesAndCardIdService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadConsumptionByDatesAndCardIdServiceConfig {

    LoadConsumptionByDatesAndCardIdService loadConsumptionByDatesAndCardIdService(
            LoadConsumptionsByDatesAndCardIdPort loadConsumptionsByDatesAndCardIdPort
    ) {
        return new LoadConsumptionByDatesAndCardIdService(loadConsumptionsByDatesAndCardIdPort);
    }
}
