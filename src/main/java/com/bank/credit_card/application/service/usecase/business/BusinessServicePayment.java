package com.bank.credit_card.application.service.usecase.business;

import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.domain.payment.vo.PaymentId;

import java.util.UUID;

public interface BusinessServicePayment {
    Payment get(Long cardId, UUID paymentId);

    PaymentId save(Payment payment);
}
