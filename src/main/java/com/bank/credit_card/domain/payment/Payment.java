package com.bank.credit_card.domain.payment;

import com.bank.credit_card.domain.base.constants.StatusEnum;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.card.CategoryPaymentEnum;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.generic.GenericDomain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.bank.credit_card.domain.base.constants.StatusEnum.ACTIVE;
import static com.bank.credit_card.domain.payment.PaymentErrorMessage.*;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;
import static java.util.Objects.isNull;

public class Payment extends GenericDomain<UUID> {
    private final Amount paymentAmount;
    private final LocalDateTime paymentDate;
    private final LocalDateTime paymentApprobationDate;
    private final CategoryPaymentEnum category;
    private final CardId cardId;
    private final ChannelPaymentEnum channelPayment;

    private Payment(
            UUID id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Amount paymentAmount,
            LocalDateTime paymentDate,
            LocalDateTime paymentApprobationDate,
            CategoryPaymentEnum category,
            CardId cardId, ChannelPaymentEnum channelPayment) {

        super(id, status, createdDate, updatedDate);
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.paymentApprobationDate = paymentApprobationDate;
        this.category = category;
        this.cardId = cardId;
        this.channelPayment = channelPayment;
    }

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public CategoryPaymentEnum getCategory() {
        return category;
    }

    public CardId getCardId() {
        return cardId;
    }

    public LocalDateTime getPaymentApprobationDate() {
        return paymentApprobationDate;
    }

    public ChannelPaymentEnum getChannelPayment() {
        return channelPayment;
    }

    public static Payment create(
            UUID id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Amount paymentAmount,
            LocalDateTime paymentDate,
            LocalDateTime paymentApprobationDate,
            CategoryPaymentEnum category,
            CardId cardId,
            ChannelPaymentEnum channelPayment) {

        isNotNull(paymentAmount, new PaymentException(PAYMENT_AMOUNT_NOT_NULL));
        isNotNull(paymentDate, new PaymentException(PAYMENT_DAY_NOT_NULL));
        isNotNull(category, new PaymentException(PAYMENT_CATEGORY_NOT_NULL));
        isNotConditional(paymentAmount.estaVacio(), new PaymentException(PAYMENT_AMOUNT_NOT_ZERO));
        isNotNull(cardId, new PaymentException(CARD_ID_NOT_NULL));
        isNotNull(channelPayment, new PaymentException(CHANGE_PAYMENT_NOT_NULL));

        return new Payment(
                id,
                status,
                createdDate,
                updatedDate,
                paymentAmount,
                paymentDate,
                paymentApprobationDate,
                category,
                cardId,
                channelPayment);
    }

    public static Payment create(
            Amount paymentAmount,
            CategoryPaymentEnum category,
            CardId cardId,
            ChannelPaymentEnum channelPayment) {

        isNotNull(paymentAmount, new PaymentException(PAYMENT_AMOUNT_NOT_NULL));
        isNotNull(category, new PaymentException(PAYMENT_CATEGORY_NOT_NULL));
        isNotConditional(paymentAmount.estaVacio(), new PaymentException(PAYMENT_AMOUNT_NOT_ZERO));
        isNotNull(channelPayment, new PaymentException(CHANGE_PAYMENT_NOT_NULL));

        return new Payment(
                UUID.randomUUID(),
                ACTIVE,
                LocalDateTime.now(),
                null,
                paymentAmount,
                LocalDateTime.now(),
                null,
                category,
                cardId,
                channelPayment);
    }

    public Payment discount(BigDecimal discount) {
        return Payment.create(getPaymentAmount().descuento(discount),
                getCategory(), getCardId(), getChannelPayment());
    }

    public void validateIfPaymentIsInApprobation() {
        isNotConditional(isNull(getPaymentApprobationDate()),
                new PaymentException(PAYMENT_IS_STILL_IN_APPROBATION));
    }

    public void cancel() {
        softDelete();
    }
}

