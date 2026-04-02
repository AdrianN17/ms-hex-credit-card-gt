package com.bank.credit_card.infraestructure.config.spring;

import com.bank.credit_card.application.service.query.LoadCardByIdService;
import com.bank.credit_card.application.service.query.LoadConsumptionByDatesAndCardIdService;
import com.bank.credit_card.application.service.query.LoadPaymentByDatesAndCardIdService;
import com.bank.credit_card.application.service.usecase.*;
import com.bank.credit_card.infraestructure.web.api.controller.CardManagementApi;
import com.bank.credit_card.infraestructure.web.api.controller.CardManagementApiImpl;
import com.bank.credit_card.infraestructure.web.api.delegate.CardManagementDelegate;
import com.bank.credit_card.infraestructure.web.api.delegate.CardManagementDelegateImpl;
import com.bank.credit_card.infraestructure.web.api.mapper.CardApiMapperRequestCommand;
import com.bank.credit_card.infraestructure.web.api.mapper.ConsumptionApiMapperCommand;
import com.bank.credit_card.infraestructure.web.api.mapper.PaymentApiMapperCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardManagementAdapterConfig {

    @Bean
    CardManagementApi cardManagementApi(CardManagementDelegate cardManagementDelegate) {
        return new CardManagementApiImpl(cardManagementDelegate);
    }

    @Bean
    CardManagementDelegate cardManagementDelegate(CreateCardService createCardService,
                                                  CardCancelPaymentService cardCancelPaymentService,
                                                  CardCancelConsumptionService cardCancelConsumptionService,
                                                  CardCloseService cardCloseService,
                                                  CardConsumptionService cardConsumptionService,
                                                  CardPaymentService cardPaymentService,
                                                  SplitConsumptionService splitConsumptionService,
                                                  LoadConsumptionByDatesAndCardIdService loadConsumptionByDatesAndCardIdService,
                                                  LoadPaymentByDatesAndCardIdService loadPaymentByDatesAndCardIdService,
                                                  LoadCardByIdService loadCardByIdService,
                                                  CardApiMapperRequestCommand cardApiMapperRequestCommand,
                                                  ConsumptionApiMapperCommand consumptionApiMapperCommand,
                                                  PaymentApiMapperCommand paymentApiMapperCommand) {
        return new CardManagementDelegateImpl(createCardService,
                cardCancelPaymentService,
                cardCancelConsumptionService,
                cardCloseService,
                cardConsumptionService,
                cardPaymentService,
                splitConsumptionService,
                loadConsumptionByDatesAndCardIdService,
                loadPaymentByDatesAndCardIdService,
                loadCardByIdService,
                cardApiMapperRequestCommand,
                consumptionApiMapperCommand,
                paymentApiMapperCommand);
    }
}
