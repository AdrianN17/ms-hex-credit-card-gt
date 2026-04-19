package com.bank.credit_card.infraestructure.config.application;

import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.port.out.generator.LoadIdPort;
import com.bank.credit_card.application.service.usecase.CreateCardService;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBenefit;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.domain.balance.factory.BalanceFactory;
import com.bank.credit_card.domain.balance.factory.BalanceFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateCardServiceConfig {

    @Bean
    BalanceFactory balanceFactory() {
        return new BalanceFactoryImpl();
    }

    @Bean
    CreateCardService createCardService(
            BusinessServiceCard businessServiceCard,
            BusinessServiceBalance businessServiceBalance,
            BusinessServiceBenefit businessServiceBenefit,
            LoadIdPort loadIdPort,
            LoadCurrencyPort loadCurrencyPort,
            BalanceFactory balanceFactory) {

        return new CreateCardService(businessServiceCard, businessServiceBalance, businessServiceBenefit, loadIdPort, loadCurrencyPort, balanceFactory);
    }
}
