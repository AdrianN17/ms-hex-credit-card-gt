package com.bank.credit_card.infraestructure.config.spring;

import com.bank.credit_card.application.service.query.LoadCardByIdService;
import com.bank.credit_card.application.service.query.LoadConsumptionByDatesAndCardIdService;
import com.bank.credit_card.application.service.query.LoadPaymentByDatesAndCardIdService;
import com.bank.credit_card.application.service.usecase.*;
import com.bank.credit_card.application.service.usecase.SplitConsumptionService;
import com.bank.credit_card.infraestructure.web.api.delegate.CardManagementDelegate;
import com.bank.credit_card.infraestructure.web.api.delegate.CardManagementDelegateImpl;
import com.bank.credit_card.infraestructure.web.api.mapper.command.CardApiMapperRequestCommand;
import com.bank.credit_card.infraestructure.web.api.mapper.command.ConsumptionApiMapperRequestCommand;
import com.bank.credit_card.infraestructure.web.api.mapper.command.PaymentApiMapperRequestCommand;
import com.bank.credit_card.infraestructure.web.api.mapper.command.CardApiMapperRequestCommandImpl;
import com.bank.credit_card.infraestructure.web.api.mapper.command.ConsumptionApiMapperRequestCommandImpl;
import com.bank.credit_card.infraestructure.web.api.mapper.command.PaymentApiMapperRequestCommandImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardManagementAdapterConfig {

    @Bean
    CardApiMapperRequestCommand cardApiMapperRequestCommand() {
        return new CardApiMapperRequestCommandImpl();
    }

    @Bean
    ConsumptionApiMapperRequestCommand consumptionApiMapperRequestCommand() {
        return new ConsumptionApiMapperRequestCommandImpl();
    }

    @Bean
    PaymentApiMapperRequestCommand paymentApiMapperRequestCommand() {
        return new PaymentApiMapperRequestCommandImpl();
    }


    @Bean
    CardManagementDelegate cardManagementDelegate(CreateCardService createCardService,
                                                  CancelPaymentService cardCancelPaymentService,
                                                  CancelConsumptionService cardCancelConsumptionService,
                                                  CardCloseService cardCloseService,
                                                  ConsumptionService cardConsumptionService,
                                                  PaymentService cardPaymentService,
                                                  SplitConsumptionService splitConsumptionService,
                                                  LoadConsumptionByDatesAndCardIdService loadConsumptionByDatesAndCardIdService,
                                                  LoadPaymentByDatesAndCardIdService loadPaymentByDatesAndCardIdService,
                                                  LoadCardByIdService loadCardByIdService,
                                                  CardApiMapperRequestCommand cardApiMapperRequestCommand,
                                                  ConsumptionApiMapperRequestCommand consumptionApiMapperRequestCommand,
                                                  PaymentApiMapperRequestCommand paymentApiMapperRequestCommand) {
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
                consumptionApiMapperRequestCommand,
                paymentApiMapperRequestCommand);
    }
}
