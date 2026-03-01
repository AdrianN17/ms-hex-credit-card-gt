package com.bank.credit_card.application.port.in.query.view;

import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.card.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.ChannelPaymentEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LoadPaymentView(
        Long paymentId,
        BigDecimal amount,
        CurrencyEnum currency,
        LocalDateTime paymentDate,
        LocalDateTime paymentApprobationDate,
        CategoryPaymentEnum category,
        Long cardId,
        ChannelPaymentEnum channelPayment
) {
}
