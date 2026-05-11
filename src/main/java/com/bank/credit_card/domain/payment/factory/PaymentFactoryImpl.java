package com.bank.credit_card.domain.payment.factory;

import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.payment.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentFactoryImpl implements PaymentFactory {

    @Override
    public Payment create(UUID id,
                          StatusEnum status,
                          LocalDateTime createdDate,
                          LocalDateTime updatedDate,
                          Currency currency,
                          BigDecimal amount,
                          LocalDateTime paymentDate,
                          LocalDateTime paymentApprobationDate,
                          CategoryPaymentEnum category,
                          Long cardId,
                          ChannelPaymentEnum channelPayment) {

        return switch (category) {
            case NORMAL -> NormalPayment.builder()
                    .id(id)
                    .status(status)
                    .createdDate(createdDate)
                    .updatedDate(updatedDate)
                    .paymentAmount(amount, currency)
                    .approbation(paymentDate, paymentApprobationDate)
                    .cardId(cardId)
                    .channelPayment(channelPayment)
                    .build();
            case TOTAL -> TotalPayment.builder()
                    .id(id)
                    .status(status)
                    .createdDate(createdDate)
                    .updatedDate(updatedDate)
                    .paymentAmount(amount, currency)
                    .approbation(paymentDate, paymentApprobationDate)
                    .cardId(cardId)
                    .channelPayment(channelPayment)
                    .build();
            case MINIMO -> MinimunPayment.builder()
                    .id(id)
                    .status(status)
                    .createdDate(createdDate)
                    .updatedDate(updatedDate)
                    .paymentAmount(amount, currency)
                    .approbation(paymentDate, paymentApprobationDate)
                    .cardId(cardId)
                    .channelPayment(channelPayment)
                    .build();
            case ADELANTADO -> Prepayment.builder()
                    .id(id)
                    .status(status)
                    .createdDate(createdDate)
                    .updatedDate(updatedDate)
                    .paymentAmount(amount, currency)
                    .approbation(paymentDate, paymentApprobationDate)
                    .cardId(cardId)
                    .channelPayment(channelPayment)
                    .build();
        };
    }

    @Override
    public Payment create(Currency currency,
                          BigDecimal amount,
                          CategoryPaymentEnum category,
                          Long cardId,
                          ChannelPaymentEnum channelPayment) {

        return switch (category) {
            case NORMAL -> NormalPayment.builder()
                    .paymentAmount(amount, currency)
                    .cardId(cardId)
                    .channelPayment(channelPayment)
                    .build();
            case TOTAL -> TotalPayment.builder()
                    .paymentAmount(amount, currency)
                    .cardId(cardId)
                    .channelPayment(channelPayment)
                    .build();
            case MINIMO -> MinimunPayment.builder()
                    .paymentAmount(amount, currency)
                    .cardId(cardId)
                    .channelPayment(channelPayment)
                    .build();
            case ADELANTADO -> Prepayment.builder()
                    .paymentAmount(amount, currency)
                    .cardId(cardId)
                    .channelPayment(channelPayment)
                    .build();
        };
    }
}


