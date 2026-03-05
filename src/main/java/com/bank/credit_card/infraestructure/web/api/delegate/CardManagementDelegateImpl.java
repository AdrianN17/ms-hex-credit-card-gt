package com.bank.credit_card.infraestructure.web.api.delegate;

import com.bank.credit_card.application.port.in.query.criteria.FindConsumptionByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.criteria.FindPaymentByDatesAndCardIdCriteria;
import com.bank.credit_card.application.service.query.LoadCardByIdService;
import com.bank.credit_card.application.service.query.LoadConsumptionByDatesAndCardIdService;
import com.bank.credit_card.application.service.query.LoadPaymentByDatesAndCardIdService;
import com.bank.credit_card.application.service.usecase.*;
import com.bank.credit_card.infraestructure.web.api.mapper.CardApiMapperRequestCommand;
import com.bank.credit_card.infraestructure.web.api.mapper.ConsumptionApiMapperCommand;
import com.bank.credit_card.infraestructure.web.api.mapper.PaymentApiMapperCommand;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateCardRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CardManagementDelegateImpl implements CardManagementDelegate {

    private final CreateCardService createCardService;
    private final CardCancelPaymentService cardCancelPaymentService;
    private final CardCancelConsumptionService cardCancelConsumptionService;
    private final CardCloseService cardCloseService;
    private final CardConsumptionService cardConsumptionService;
    private final CardPaymentService cardPaymentService;
    private final SplitConsumptionService splitConsumptionService;

    private final LoadConsumptionByDatesAndCardIdService loadConsumptionByDatesAndCardIdService;
    private final LoadPaymentByDatesAndCardIdService loadPaymentByDatesAndCardIdService;
    private final LoadCardByIdService loadCardByIdService;

    private final CardApiMapperRequestCommand cardApiMapperRequestCommand;
    private final ConsumptionApiMapperCommand consumptionApiMapperCommand;
    private final PaymentApiMapperCommand paymentApiMapperCommand;

    public CardManagementDelegateImpl(CreateCardService createCardService, CardCancelPaymentService cardCancelPaymentService, CardCancelConsumptionService cardCancelConsumptionService, CardCloseService cardCloseService, CardConsumptionService cardConsumptionService, CardPaymentService cardPaymentService, SplitConsumptionService splitConsumptionService, LoadConsumptionByDatesAndCardIdService loadConsumptionByDatesAndCardIdService, LoadPaymentByDatesAndCardIdService loadPaymentByDatesAndCardIdService, LoadCardByIdService loadCardByIdService, CardApiMapperRequestCommand cardApiMapperRequestCommand, ConsumptionApiMapperCommand consumptionApiMapperCommand, PaymentApiMapperCommand paymentApiMapperCommand) {
        this.createCardService = createCardService;
        this.cardCancelPaymentService = cardCancelPaymentService;
        this.cardCancelConsumptionService = cardCancelConsumptionService;
        this.cardCloseService = cardCloseService;
        this.cardConsumptionService = cardConsumptionService;
        this.cardPaymentService = cardPaymentService;
        this.splitConsumptionService = splitConsumptionService;
        this.loadConsumptionByDatesAndCardIdService = loadConsumptionByDatesAndCardIdService;
        this.loadPaymentByDatesAndCardIdService = loadPaymentByDatesAndCardIdService;
        this.loadCardByIdService = loadCardByIdService;
        this.cardApiMapperRequestCommand = cardApiMapperRequestCommand;
        this.consumptionApiMapperCommand = consumptionApiMapperCommand;
        this.paymentApiMapperCommand = paymentApiMapperCommand;
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> controlCard(Long cardId) {

        cardCloseService.closeCard(cardApiMapperRequestCommand.toCommandId(cardId));

        return null;
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> controlConsumption(Long cardId, UUID consumptionId) {

        cardCancelConsumptionService.cancelConsumption(consumptionApiMapperCommand.toCommandId(consumptionId, cardId));

        return null;
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> controlPayment(Long cardId, UUID paymentId) {

        cardCancelPaymentService.cancelPayment(paymentApiMapperCommand.toCommandId(paymentId, cardId));

        return null;
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> initiatePayment(Long cardId, InitiatePaymentRequest initiatePaymentRequest, BindingResult bindingResult) {

        cardPaymentService.processPayment(paymentApiMapperCommand.toCommand(initiatePaymentRequest.getData(), cardId));

        return null;
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> exchangeConsumption(Integer cardId, UUID consumptionId, ExchangeConsumptionRequest exchangeConsumptionRequest, BindingResult bindingResult) {

        //splitConsumptionService.splitConsumption()

        return null;
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> initiateCard(InitiateCardRequest initiateCardRequest, BindingResult bindingResult) {


        createCardService.createCard(cardApiMapperRequestCommand.toCommand(initiateCardRequest.getData()));

        return null;
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> initiateConsumption(Long cardId, InitiateConsumptionRequest initiateConsumptionRequest, BindingResult bindingResult) {

        cardConsumptionService.processConsumption(consumptionApiMapperCommand.toCommand(initiateConsumptionRequest.getData(), cardId));

        return null;
    }

    @Override
    public ResponseEntity<RetrieveBalance200Response> retrieveBalance(Long cardId) {

        CardResponse cardResponse = cardApiMapperRequestCommand.toResponse(loadCardByIdService.findById(cardId));

        return null;
    }

    @Override
    public ResponseEntity<RetrieveConsumption200Response> retrieveConsumption(Long cardId, LocalDate dateStart, LocalDate dateEnd) {

        List<ConsumptionResponse> responseConsumptions = loadConsumptionByDatesAndCardIdService.findByDatesAndCardId(new FindConsumptionByDatesAndCardIdCriteria(
                dateStart,
                dateEnd,
                cardId
        )).stream().map(consumptionApiMapperCommand::toResponse).toList();

        return null;
    }

    @Override
    public ResponseEntity<RetrievePayment200Response> retrievePayment(Long cardId, LocalDate dateStart, LocalDate dateEnd) {
        List<PaymentResponse> responsePayments = loadPaymentByDatesAndCardIdService.findByDatesAndCardId(new FindPaymentByDatesAndCardIdCriteria(
                dateStart,
                dateEnd,
                cardId
        )).stream().map(paymentApiMapperCommand::toResponse).toList();

        return null;
    }
}
