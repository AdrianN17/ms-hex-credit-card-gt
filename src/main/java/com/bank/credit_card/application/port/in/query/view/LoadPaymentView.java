package com.bank.credit_card.application.port.in.query.view;

import com.bank.credit_card.domain.base.enums.CurrencyEnum;
import com.bank.credit_card.domain.card.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.ChannelPaymentEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LoadPaymentView(
        UUID paymentId,
        BigDecimal amount,
        CurrencyEnum currency,
        LocalDateTime paymentDate,
        LocalDateTime paymentApprobationDate,
        CategoryPaymentEnum category,
        String cardId,
        ChannelPaymentEnum channelPayment
) {
}
