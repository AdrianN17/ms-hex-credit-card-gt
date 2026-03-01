package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardProcessPaymentCommand;
import com.bank.credit_card.domain.payment.Payment;

@FunctionalInterface
public interface CardProcessPaymentUseCase {
    Payment processPayment(CardProcessPaymentCommand cardProcessPaymentCommand);
}
