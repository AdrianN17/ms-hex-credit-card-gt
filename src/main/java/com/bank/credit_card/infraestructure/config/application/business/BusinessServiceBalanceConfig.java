package com.bank.credit_card.infraestructure.config.application.business;

import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalanceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessServiceBalanceConfig {
    @Bean
    BusinessServiceBalance businessServiceBalance(
            LoadBalancePort loadBalancePort,
            SaveBalancePort saveBalancePort,
            LoadCurrencyPort loadCurrencyPort,
            LoadCardCurrencyPort loadCardCurrencyPort) {
        return new BusinessServiceBalanceImpl(loadBalancePort, saveBalancePort, loadCurrencyPort, loadCardCurrencyPort);
    }
}
