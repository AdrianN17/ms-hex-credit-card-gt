package com.bank.credit_card.infraestructure.web.api.delegate;

import com.bank.credit_card.infraestructure.web.api.schema.request.ExchangeConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateCardRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.UUID;

public interface CardManagementDelegate {

    ResponseEntity<Long202Response> controlCard(Long cardId);

    ResponseEntity<UUID202Response> controlConsumption(Long cardId, UUID consumptionId);

    ResponseEntity<UUID202Response> controlPayment(Long cardId, UUID paymentId);

    ResponseEntity<UUID202Response> initiatePayment(
            Long cardId,
            InitiatePaymentRequest initiatePaymentRequest,
            BindingResult bindingResult
    );

    ResponseEntity<UUIDList202Response> exchangeConsumption(
            Long cardId,
            UUID consumptionId,
            ExchangeConsumptionRequest exchangeConsumptionRequest,
            BindingResult bindingResult
    );

    ResponseEntity<Long202Response> initiateCard(
            InitiateCardRequest initiateCardRequest,
            BindingResult bindingResult
    );

    ResponseEntity<UUID202Response> initiateConsumption(
            Long cardId,
            InitiateConsumptionRequest initiateConsumptionRequest,
            BindingResult bindingResult
    );

    ResponseEntity<RetrieveBalance200Response> retrieveBalance(Long cardId);

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
