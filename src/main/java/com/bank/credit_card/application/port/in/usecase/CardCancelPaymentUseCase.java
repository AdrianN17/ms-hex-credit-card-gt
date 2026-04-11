package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardCancelPaymentCommand;

import java.util.UUID;

@FunctionalInterface
public interface CardCancelPaymentUseCase {
    UUID cancelPayment(CardCancelPaymentCommand cardCancelPaymentCommand);
}
