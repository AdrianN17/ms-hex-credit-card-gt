package com.bank.credit_card.infraestructure.web.api.controller;

import com.bank.credit_card.infraestructure.web.api.delegate.CardManagementDelegate;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateCardRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.UUID;

public class CardManagementApiImpl implements CardManagementApi {

    private final CardManagementDelegate cardManagementDelegate;

    public CardManagementApiImpl(CardManagementDelegate cardManagementDelegate) {
        this.cardManagementDelegate = cardManagementDelegate;
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> controlCard(Long cardId) {
        return cardManagementDelegate.controlCard(cardId);
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> controlConsumption(Long cardId, UUID consumptionId) {
        return cardManagementDelegate.controlConsumption(cardId, consumptionId);
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> controlPayment(Long cardId, UUID paymentId) {
        return cardManagementDelegate.controlPayment(cardId, paymentId);
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> initiatePayment(Long cardId, InitiatePaymentRequest initiatePaymentRequest, BindingResult bindingResult) {
        return cardManagementDelegate.initiatePayment(cardId, initiatePaymentRequest, bindingResult);
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> exchangeConsumption(Integer cardId, UUID consumptionId, ExchangeConsumptionRequest exchangeConsumptionRequest, BindingResult bindingResult) {
        return cardManagementDelegate.exchangeConsumption(cardId, consumptionId, exchangeConsumptionRequest, bindingResult);
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> initiateCard(InitiateCardRequest initiateCardRequest, BindingResult bindingResult) {
        return cardManagementDelegate.initiateCard(initiateCardRequest, bindingResult);
    }

    @Override
    public ResponseEntity<DefaultResponse2xx> initiateConsumption(Long cardId, InitiateConsumptionRequest initiateConsumptionRequest, BindingResult bindingResult) {
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
