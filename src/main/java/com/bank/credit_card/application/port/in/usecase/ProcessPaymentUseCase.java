package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardProcessPaymentCommand;
import com.bank.credit_card.domain.payment.vo.PaymentId;

@FunctionalInterface
public interface ProcessPaymentUseCase {
    PaymentId processPayment(CardProcessPaymentCommand cardProcessPaymentCommand);
}
