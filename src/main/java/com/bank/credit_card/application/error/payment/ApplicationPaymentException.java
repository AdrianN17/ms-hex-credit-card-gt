package com.bank.credit_card.application.error.payment;

import com.bank.credit_card.application.error.ApplicationException;

public class ApplicationPaymentException
        extends ApplicationException {

    public ApplicationPaymentException(String message) {
        super(message);
    }
}
