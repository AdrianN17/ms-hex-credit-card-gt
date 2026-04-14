package com.bank.credit_card.application.port.out.payment.query;

import com.bank.credit_card.domain.base.constants.CurrencyEnum;

import java.util.Optional;
import java.util.UUID;

public interface LoadPaymentCurrencyPort {
    Optional<CurrencyEnum> load(UUID paymentId, String cardId);
}
