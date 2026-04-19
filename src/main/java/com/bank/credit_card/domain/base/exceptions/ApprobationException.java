package com.bank.credit_card.domain.base.exceptions;

import com.bank.credit_card.domain.exception.DomainException;


public class ApprobationException extends DomainException {

    public ApprobationException() {
    }

    public ApprobationException(String message) {
        super(message);
    }

    public ApprobationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApprobationException(Throwable cause) {
        super(cause);
    }

    public ApprobationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

