package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardCancelPaymentCommand;

@FunctionalInterface
public interface CardCancelPaymentUseCase {
    void cancelPayment(CardCancelPaymentCommand cardCancelPaymentCommand);
}
