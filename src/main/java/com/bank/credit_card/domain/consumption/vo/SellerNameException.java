package com.bank.credit_card.domain.consumption.vo;

import com.bank.credit_card.domain.exception.DomainException;

public class SellerNameException extends DomainException {

    public SellerNameException() {
    }

    public SellerNameException(String message) {
        super(message);
    }

    public SellerNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public SellerNameException(Throwable cause) {
        super(cause);
    }
}

