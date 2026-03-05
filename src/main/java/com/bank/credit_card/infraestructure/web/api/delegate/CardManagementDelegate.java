package com.bank.credit_card.infraestructure.web.api.delegate;

import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateCardRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.UUID;

public interface CardManagementDelegate {

    ResponseEntity<DefaultResponse2xx> controlCard(
            Long cardId
    );

    ResponseEntity<DefaultResponse2xx> controlConsumption(
            Long cardId,
            UUID consumptionId
    );

    ResponseEntity<DefaultResponse2xx> controlPayment(
            Long cardId,
            UUID paymentId
    );

    ResponseEntity<DefaultResponse2xx> initiatePayment(
            Long cardId,
            InitiatePaymentRequest initiatePaymentRequest,
            BindingResult bindingResult
    );

    ResponseEntity<DefaultResponse2xx> exchangeConsumption(
            Integer cardId,
            UUID consumptionId,
            ExchangeConsumptionRequest exchangeConsumptionRequest,
            BindingResult bindingResult
    );

    ResponseEntity<DefaultResponse2xx> initiateCard(
            InitiateCardRequest initiateCardRequest,
            BindingResult bindingResult
    );

    ResponseEntity<DefaultResponse2xx> initiateConsumption(
            Long cardId,
            InitiateConsumptionRequest initiateConsumptionRequest,
            BindingResult bindingResult
    );

    ResponseEntity<RetrieveBalance200Response> retrieveBalance(
            Long cardId
    );

    ResponseEntity<RetrieveConsumption200Response> retrieveConsumption(
            Long cardId,
            LocalDate dateStart,
            LocalDate dateEnd
    );

    ResponseEntity<RetrievePayment200Response> retrievePayment(
            Long cardId,
            LocalDate dateStart,
            LocalDate dateEnd
    );
}
