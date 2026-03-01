package com.bank.credit_card.application.error.consumption;

import com.bank.credit_card.application.error.ApplicationException;

public class ApplicationConsumptionException
        extends ApplicationException {

    public ApplicationConsumptionException(String message) {
        super(message);
    }
}
