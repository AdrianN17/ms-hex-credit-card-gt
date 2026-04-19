package com.bank.credit_card.infraestructure.config.application.business;

import com.bank.credit_card.application.port.out.benefit.LoadBenefitPort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBenefit;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBenefitImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessServiceBenefitConfig {

    @Bean
    BusinessServiceBenefit businessServiceBenefit(
            LoadBenefitPort loadBenefitPort,
            SaveBenefitPort saveBenefitPort) {
        return new BusinessServiceBenefitImpl(loadBenefitPort, saveBenefitPort);
    }
}

