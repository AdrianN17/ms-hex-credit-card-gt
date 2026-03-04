package com.bank.credit_card.application.port.in.command;

import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.card.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.ChannelPaymentEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CardProcessPaymentCommand(
        BigDecimal amount,
        CurrencyEnum currency,
        CategoryPaymentEnum category,
        Long cardId,
        ChannelPaymentEnum channelPayment,
        Integer pointsUsed,
        BigDecimal exchangeRate
) {
}
