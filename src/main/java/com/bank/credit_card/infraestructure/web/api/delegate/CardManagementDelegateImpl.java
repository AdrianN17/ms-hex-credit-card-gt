package com.bank.credit_card.infraestructure.web.api.delegate;

import com.bank.credit_card.application.port.in.query.criteria.FindConsumptionByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.criteria.FindPaymentByDatesAndCardIdCriteria;
import com.bank.credit_card.application.service.query.LoadCardByIdService;
import com.bank.credit_card.application.service.query.LoadConsumptionByDatesAndCardIdService;
import com.bank.credit_card.application.service.query.LoadPaymentByDatesAndCardIdService;
import com.bank.credit_card.application.service.usecase.*;
import com.bank.credit_card.application.service.usecase.SplitConsumptionService;
import com.bank.credit_card.infraestructure.web.api.mapper.command.CardApiMapperRequestCommand;
import com.bank.credit_card.infraestructure.web.api.mapper.command.ConsumptionApiMapperRequestCommand;
import com.bank.credit_card.infraestructure.web.api.mapper.command.PaymentApiMapperRequestCommand;
import com.bank.credit_card.infraestructure.web.api.schema.request.ExchangeConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateCardRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.UUID;

import static com.bank.credit_card.infraestructure.web.api.util.MapperResponse.*;
import static com.bank.credit_card.infraestructure.web.api.util.BindingValidator.validate;

@Slf4j
public class CardManagementDelegateImpl implements CardManagementDelegate {

    private final CreateCardService createCardService;
    private final CancelPaymentService cardCancelPaymentService;
    private final CancelConsumptionService cardCancelConsumptionService;
    private final CardCloseService cardCloseService;
    private final ConsumptionService cardConsumptionService;
    private final PaymentService cardPaymentService;
    private final SplitConsumptionService splitConsumptionService;

    private final LoadConsumptionByDatesAndCardIdService loadConsumptionByDatesAndCardIdService;
    private final LoadPaymentByDatesAndCardIdService loadPaymentByDatesAndCardIdService;
    private final LoadCardByIdService loadCardByIdService;

    private final CardApiMapperRequestCommand cardApiMapperRequestCommand;
    private final ConsumptionApiMapperRequestCommand consumptionApiMapperRequestCommand;
    private final PaymentApiMapperRequestCommand paymentApiMapperRequestCommand;

    public CardManagementDelegateImpl(CreateCardService createCardService,
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
    public ResponseEntity<Long202Response> controlCard(Long cardId) {
        var id = cardCloseService.closeCard(cardApiMapperRequestCommand.toCommandId(cardId));
        return getLong202Response(id);
    }

    @Override
    public ResponseEntity<UUID202Response> controlConsumption(Long cardId, UUID consumptionId) {
        var id = cardCancelConsumptionService.cancelConsumption(consumptionApiMapperRequestCommand.toCommandId(consumptionId, cardId));
        return getUUID202Response(id);
    }

    @Override
    public ResponseEntity<UUID202Response> controlPayment(Long cardId, UUID paymentId) {
        var id =  cardCancelPaymentService.cancelPayment(paymentApiMapperRequestCommand.toCommandId(paymentId, cardId));
        return getUUID202Response(id);
    }

    @Override
    public ResponseEntity<UUID202Response> initiatePayment(Long cardId, InitiatePaymentRequest initiatePaymentRequest, BindingResult bindingResult) {
        validate(bindingResult);
        var payment = cardPaymentService.processPayment(paymentApiMapperRequestCommand.toCommand(initiatePaymentRequest.getData(), cardId));
        return getUUID202Response(payment.getId());
    }

    @Override
    public ResponseEntity<UUIDList202Response> exchangeConsumption(Long cardId, UUID consumptionId, ExchangeConsumptionRequest exchangeConsumptionRequest, BindingResult bindingResult) {
        validate(bindingResult);
        var consumptions = splitConsumptionService.splitConsumption(consumptionApiMapperRequestCommand.toCommandIdR(consumptionId, cardId, exchangeConsumptionRequest.getData()));
        return getUUIDList202Response(consumptions.stream()
                .map(c -> c.getId())
                .toList());
    }

    @Override
    public ResponseEntity<Long202Response> initiateCard(InitiateCardRequest initiateCardRequest, BindingResult bindingResult) {
        validate(bindingResult);
        var card = createCardService.createCard(cardApiMapperRequestCommand.toCommand(initiateCardRequest.getData()));
        return getLong202Response(card.getId());
    }

    @Override
    public ResponseEntity<UUID202Response> initiateConsumption(Long cardId, InitiateConsumptionRequest initiateConsumptionRequest, BindingResult bindingResult) {
        validate(bindingResult);
        var id = cardConsumptionService.processConsumption(consumptionApiMapperRequestCommand.toCommand(initiateConsumptionRequest.getData(), cardId));
        return getUUID202Response(consumption.getId());
    }

    @Override
    public ResponseEntity<RetrieveBalance200Response> retrieveBalance(Long cardId) {

        var cardResponse = cardApiMapperRequestCommand.toResponse(loadCardByIdService.findById(cardId));
        return getRetrieveBalance(cardResponse);
    }

    @Override
    public ResponseEntity<RetrieveConsumption200Response> retrieveConsumption(Long cardId, LocalDate dateStart, LocalDate dateEnd) {
        var responseConsumptions = loadConsumptionByDatesAndCardIdService.findByDatesAndCardId(new FindConsumptionByDatesAndCardIdCriteria(
                dateStart,
                dateEnd,
                cardId
        )).stream().map(consumptionApiMapperRequestCommand::toResponse).toList();
        return getConsumptionResponse(responseConsumptions);
    }

    @Override
    public ResponseEntity<RetrievePayment200Response> retrievePayment(Long cardId, LocalDate dateStart, LocalDate dateEnd) {
        var responsePayments = loadPaymentByDatesAndCardIdService.findByDatesAndCardId(new FindPaymentByDatesAndCardIdCriteria(
                dateStart,
                dateEnd,
                cardId
        )).stream().map(paymentApiMapperRequestCommand::toResponse).toList();
        return getPaymentResponse(responsePayments);
    }
}
