package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardCancelPaymentCommand;
import com.bank.credit_card.domain.payment.vo.PaymentId;

@FunctionalInterface
public interface CancelPaymentUseCase {
    PaymentId cancelPayment(CardCancelPaymentCommand cardCancelPaymentCommand);
}
