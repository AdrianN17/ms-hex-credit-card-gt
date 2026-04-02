package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.usecase.SaveCardPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.port.out.generator.LoadIdPort;
import com.bank.credit_card.application.service.usecase.CreateCardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateCardServiceConfig {


    @Bean
    CreateCardService createCardService(
            SaveCardPort saveCardPort,
            SaveBalancePort saveBalancePort,
            SaveBenefitPort saveBenefitPort,
            LoadIdPort loadIdPort,
            LoadCurrencyPort loadCurrencyPort) {

        return new CreateCardService(saveCardPort,
                saveBalancePort,
                saveBenefitPort,
                loadIdPort,
                loadCurrencyPort);
    }
}
