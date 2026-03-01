package com.bank.credit_card.application.error.card;

import com.bank.credit_card.application.error.ApplicationException;

public class ApplicationCardException
        extends ApplicationException {

    public ApplicationCardException(String message) {
        super(message);
    }
}
