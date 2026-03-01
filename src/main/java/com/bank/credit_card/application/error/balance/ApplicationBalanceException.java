package com.bank.credit_card.application.error.balance;

import com.bank.credit_card.application.error.ApplicationException;

public class ApplicationBalanceException
        extends ApplicationException {

    public ApplicationBalanceException(String message) {
        super(message);
    }
}
