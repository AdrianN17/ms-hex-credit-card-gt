package com.bank.credit_card.infraestructure.web.api.util;

import com.bank.credit_card.infraestructure.web.api.schema.response.*;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class MapperResponse {
    public static ResponseEntity<ControlCard202Response> getControlCard202Response() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ControlCard202Response(new Tracking(UUID.randomUUID(), OffsetDateTime.now())));
    }

    public static ResponseEntity<RetrieveBalance200Response> getRetrieveBalance(CardResponse response) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RetrieveBalance200Response(response, new Tracking(UUID.randomUUID(), OffsetDateTime.now())));
    }

    public static ResponseEntity<RetrievePayment200Response> getPaymentResponse(List<PaymentResponse> response) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RetrievePayment200Response(new Tracking(UUID.randomUUID(), OffsetDateTime.now()), response));
    }

    public static ResponseEntity<RetrieveConsumption200Response> getConsumptionResponse(List<ConsumptionResponse> response) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RetrieveConsumption200Response(new Tracking(UUID.randomUUID(), OffsetDateTime.now()), response));
    }
}
