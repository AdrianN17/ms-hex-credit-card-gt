package com.bank.credit_card.infraestructure.web.api.delegate;

import com.bank.credit_card.application.port.in.query.criteria.FindConsumptionByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.criteria.FindPaymentByDatesAndCardIdCriteria;
import com.bank.credit_card.application.service.query.LoadCardByIdService;
import com.bank.credit_card.application.service.query.LoadConsumptionByDatesAndCardIdService;
import com.bank.credit_card.application.service.query.LoadPaymentByDatesAndCardIdService;
import com.bank.credit_card.application.service.usecase.*;
import com.bank.credit_card.infraestructure.web.api.mapper.command.CardApiMapperRequestCommand;
import com.bank.credit_card.infraestructure.web.api.mapper.command.ConsumptionApiMapperRequestCommand;
import com.bank.credit_card.infraestructure.web.api.mapper.command.PaymentApiMapperRequestCommand;
import com.bank.credit_card.infraestructure.web.api.schema.request.ExchangeConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateCardRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.bank.credit_card.infraestructure.web.api.util.MapperResponse.*;
import static com.bank.credit_card.infraestructure.web.api.util.BindingValidator.validate;

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
    private final ConsumptionApiMapperRequestCommand consumptionApiMapperRequestCommand;
    private final PaymentApiMapperRequestCommand paymentApiMapperRequestCommand;

    public CardManagementDelegateImpl(CreateCardService createCardService,
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
                                      ConsumptionApiMapperRequestCommand consumptionApiMapperRequestCommand,
                                      PaymentApiMapperRequestCommand paymentApiMapperRequestCommand) {
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
        this.consumptionApiMapperRequestCommand = consumptionApiMapperRequestCommand;
        this.paymentApiMapperRequestCommand = paymentApiMapperRequestCommand;
    }

    @Override
    public ResponseEntity<ControlCard202Response> controlCard(Long cardId) {
        cardCloseService.closeCard(cardApiMapperRequestCommand.toCommandId(cardId));
        return getControlCard202Response();
    }

    @Override
    public ResponseEntity<ControlCard202Response> controlConsumption(Long cardId, UUID consumptionId) {
        cardCancelConsumptionService.cancelConsumption(consumptionApiMapperRequestCommand.toCommandId(consumptionId, cardId));
        return getControlCard202Response();
    }

    @Override
    public ResponseEntity<ControlCard202Response> controlPayment(Long cardId, UUID paymentId) {
        cardCancelPaymentService.cancelPayment(paymentApiMapperRequestCommand.toCommandId(paymentId, cardId));
        return getControlCard202Response();
    }

    @Override
    public ResponseEntity<ControlCard202Response> initiatePayment(Long cardId, InitiatePaymentRequest initiatePaymentRequest, BindingResult bindingResult) {
        validate(bindingResult);
        cardPaymentService.processPayment(paymentApiMapperRequestCommand.toCommand(initiatePaymentRequest.getData(), cardId));
        return getControlCard202Response();
    }

    @Override
    public ResponseEntity<ControlCard202Response> exchangeConsumption(Long cardId, UUID consumptionId, ExchangeConsumptionRequest exchangeConsumptionRequest, BindingResult bindingResult) {
        validate(bindingResult);
        splitConsumptionService.splitConsumption(consumptionApiMapperRequestCommand.toCommandIdR(consumptionId, cardId, exchangeConsumptionRequest.getData()));
        return getControlCard202Response();
    }

    @Override
    public ResponseEntity<ControlCard202Response> initiateCard(InitiateCardRequest initiateCardRequest, BindingResult bindingResult) {
        validate(bindingResult);
        createCardService.createCard(cardApiMapperRequestCommand.toCommand(initiateCardRequest.getData()));
        return getControlCard202Response();
    }

    @Override
    public ResponseEntity<ControlCard202Response> initiateConsumption(Long cardId, InitiateConsumptionRequest initiateConsumptionRequest, BindingResult bindingResult) {
        validate(bindingResult);
        cardConsumptionService.processConsumption(consumptionApiMapperRequestCommand.toCommand(initiateConsumptionRequest.getData(), cardId));
        return getControlCard202Response();
    }

    @Override
    public ResponseEntity<RetrieveBalance200Response> retrieveBalance(Long cardId) {

        CardResponse cardResponse = cardApiMapperRequestCommand.toResponse(loadCardByIdService.findById(cardId));

        return getRetrieveBalance(cardResponse);
    }

    @Override
    public ResponseEntity<RetrieveConsumption200Response> retrieveConsumption(Long cardId, LocalDate dateStart, LocalDate dateEnd) {
        List<ConsumptionResponse> responseConsumptions = loadConsumptionByDatesAndCardIdService.findByDatesAndCardId(new FindConsumptionByDatesAndCardIdCriteria(
                dateStart,
                dateEnd,
                cardId
        )).stream().map(consumptionApiMapperRequestCommand::toResponse).toList();
        return getConsumptionResponse(responseConsumptions);
    }

    @Override
    public ResponseEntity<RetrievePayment200Response> retrievePayment(Long cardId, LocalDate dateStart, LocalDate dateEnd) {
        List<PaymentResponse> responsePayments = loadPaymentByDatesAndCardIdService.findByDatesAndCardId(new FindPaymentByDatesAndCardIdCriteria(
                dateStart,
                dateEnd,
                cardId
        )).stream().map(paymentApiMapperRequestCommand::toResponse).toList();
        return getPaymentResponse(responsePayments);
    }
}
