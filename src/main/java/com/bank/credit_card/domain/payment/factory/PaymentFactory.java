package com.bank.credit_card.domain.payment.factory;

import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.payment.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.ChannelPaymentEnum;
import com.bank.credit_card.domain.payment.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface PaymentFactory {

    Payment create(
            UUID id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Currency currency,
            BigDecimal amount,
            LocalDateTime paymentDate,
            LocalDateTime paymentApprobationDate,
            CategoryPaymentEnum category,
            Long cardId,
            ChannelPaymentEnum channelPayment

    );

    Payment create(
            Currency currency,
            BigDecimal amount,
            CategoryPaymentEnum category,
            Long cardId,
            ChannelPaymentEnum channelPayment
    );
}
