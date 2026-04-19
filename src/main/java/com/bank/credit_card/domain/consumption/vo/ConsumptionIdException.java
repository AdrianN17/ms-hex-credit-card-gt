package com.bank.credit_card.domain.consumption.vo;


import com.bank.credit_card.domain.exception.DomainException;

public class ConsumptionIdException extends DomainException {

    public ConsumptionIdException() {
    }

    public ConsumptionIdException(String message) {
        super(message);
    }

    public ConsumptionIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsumptionIdException(Throwable cause) {
        super(cause);
    }
}

