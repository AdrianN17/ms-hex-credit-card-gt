package com.bank.credit_card.infraestructure.web.api.exception;

import org.springframework.validation.BindingResult;

public class RequestValidationException extends RuntimeException {

    private final BindingResult bindingResult;

    public RequestValidationException(BindingResult bindingResult) {
        super("Request validation failed");
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}

