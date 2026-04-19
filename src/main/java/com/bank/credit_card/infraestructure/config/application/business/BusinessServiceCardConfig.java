package com.bank.credit_card.infraestructure.config.application.business;

import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.card.usecase.SaveCardPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCardImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessServiceCardConfig {

    @Bean
    BusinessServiceCard businessServiceCard(
            LoadCardPort loadCardPort,
            SaveCardPort saveCardPort,
            LoadCurrencyPort loadCurrencyPort,
            LoadCardCurrencyPort loadCardCurrencyPort) {
        return new BusinessServiceCardImpl(loadCardPort, saveCardPort, loadCurrencyPort, loadCardCurrencyPort);
    }
}

