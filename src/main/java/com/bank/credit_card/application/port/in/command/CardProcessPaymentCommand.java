package com.bank.credit_card.application.port.in.command;

import com.bank.credit_card.domain.base.enums.CurrencyEnum;
import com.bank.credit_card.domain.payment.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.ChannelPaymentEnum;

import java.math.BigDecimal;

public record CardProcessPaymentCommand(
        BigDecimal amount,
        CurrencyEnum currency,
        CategoryPaymentEnum category,
        Long cardId,
        ChannelPaymentEnum channelPayment,
        Integer pointsUsed
) {
}
