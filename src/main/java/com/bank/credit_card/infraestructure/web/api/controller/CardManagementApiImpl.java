package com.bank.credit_card.infraestructure.web.api.controller;

import com.bank.credit_card.infraestructure.web.api.delegate.CardManagementDelegate;
import com.bank.credit_card.infraestructure.web.api.schema.request.ExchangeConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateCardRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
public class CardManagementApiImpl implements CardManagementApi {

    private final CardManagementDelegate cardManagementDelegate;

    public CardManagementApiImpl(CardManagementDelegate cardManagementDelegate) {
        this.cardManagementDelegate = cardManagementDelegate;
    }

    @Override
    public ResponseEntity<Long202Response> controlCard(Long cardId) {
        return cardManagementDelegate.controlCard(cardId);
    }

    @Override
    public ResponseEntity<UUID202Response> controlConsumption(Long cardId, UUID consumptionId) {
        return cardManagementDelegate.controlConsumption(cardId, consumptionId);
    }

    @Override
    public ResponseEntity<UUID202Response> controlPayment(Long cardId, UUID paymentId) {
        return cardManagementDelegate.controlPayment(cardId, paymentId);
    }

    @Override
    public ResponseEntity<UUID202Response> initiatePayment(Long cardId, InitiatePaymentRequest initiatePaymentRequest, BindingResult bindingResult) {
        return cardManagementDelegate.initiatePayment(cardId, initiatePaymentRequest, bindingResult);
    }

    @Override
    public ResponseEntity<UUIDList202Response> exchangeConsumption(Long cardId, UUID consumptionId, ExchangeConsumptionRequest exchangeConsumptionRequest, BindingResult bindingResult) {
        return cardManagementDelegate.exchangeConsumption(cardId, consumptionId, exchangeConsumptionRequest, bindingResult);
    }

    @Override
    public ResponseEntity<Long202Response> initiateCard(InitiateCardRequest initiateCardRequest, BindingResult bindingResult) {
        return cardManagementDelegate.initiateCard(initiateCardRequest, bindingResult);
    }

    @Override
    public ResponseEntity<UUID202Response> initiateConsumption(Long cardId, InitiateConsumptionRequest initiateConsumptionRequest, BindingResult bindingResult) {
        return cardManagementDelegate.initiateConsumption(cardId, initiateConsumptionRequest, bindingResult);
    }

    @Override
    public ResponseEntity<RetrieveBalance200Response> retrieveBalance(Long cardId) {
        return cardManagementDelegate.retrieveBalance(cardId);
    }

    @Override
    public ResponseEntity<RetrieveConsumption200Response> retrieveConsumption(Long cardId, LocalDate dateStart, LocalDate dateEnd) {
        return cardManagementDelegate.retrieveConsumption(cardId, dateStart, dateEnd);
    }

    @Override
    public ResponseEntity<RetrievePayment200Response> retrievePayment(Long cardId, LocalDate dateStart, LocalDate dateEnd) {
        return cardManagementDelegate.retrievePayment(cardId, dateStart, dateEnd);
    }
}
