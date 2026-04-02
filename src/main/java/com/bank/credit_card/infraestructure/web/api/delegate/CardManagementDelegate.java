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

    ResponseEntity<ControlCard202Response> controlCard(Long cardId);

    ResponseEntity<ControlCard202Response> controlConsumption(Long cardId, UUID consumptionId);

    ResponseEntity<ControlCard202Response> controlPayment(Long cardId, UUID paymentId);

    ResponseEntity<ControlCard202Response> initiatePayment(
            Long cardId,
            InitiatePaymentRequest initiatePaymentRequest,
            BindingResult bindingResult
    );

    ResponseEntity<ControlCard202Response> exchangeConsumption(
            Long cardId,
            UUID consumptionId,
            ExchangeConsumptionRequest exchangeConsumptionRequest,
            BindingResult bindingResult
    );

    ResponseEntity<ControlCard202Response> initiateCard(
            InitiateCardRequest initiateCardRequest,
            BindingResult bindingResult
    );

    ResponseEntity<ControlCard202Response> initiateConsumption(
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
