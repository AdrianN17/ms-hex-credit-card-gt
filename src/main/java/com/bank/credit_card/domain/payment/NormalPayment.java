package com.bank.credit_card.domain.payment;

import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Approbation;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.base.vo.DateRange;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.generic.GenericDomain;
import com.bank.credit_card.domain.payment.vo.PaymentId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.bank.credit_card.domain.base.constants.DateRangeErrorMessage.DATE_NOT_WITHIN_RANGE;
import static com.bank.credit_card.domain.base.enums.StatusEnum.ACTIVE;
import static com.bank.credit_card.domain.payment.PaymentErrorMessage.*;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;
import static java.util.Objects.isNull;

public class NormalPayment extends GenericDomain<PaymentId> implements Payment {
    private final Amount paymentAmount;
    private final Approbation paymentApprobation;
    private final CategoryPaymentEnum category;
    private final CardId cardId;
    private final ChannelPaymentEnum channelPayment;

    public NormalPayment(PaymentId paymentId,
                         StatusEnum status,
                         LocalDateTime createdDate,
                         LocalDateTime updatedDate,
                         Amount paymentAmount,
                         Approbation paymentApprobation,
                         CategoryPaymentEnum category,
                         CardId cardId,
                         ChannelPaymentEnum channelPayment) {

        super(paymentId, status, createdDate, updatedDate);

        isNotConditional(Objects.equals(category, CategoryPaymentEnum.NORMAL),
                new PaymentException(PAYMENT_CATEGORY_NOT_SAME_AS_PAYMENT));

        this.paymentAmount = paymentAmount;
        this.paymentApprobation = paymentApprobation;
        this.category = category;
        this.cardId = cardId;
        this.channelPayment = channelPayment;
    }

    @Override
    public Amount getPaymentAmount() {
        return this.paymentAmount;
    }

    @Override
    public Approbation getPaymentApprobation() {
        return this.paymentApprobation;
    }

    @Override
    public CategoryPaymentEnum getCategory() {
        return this.category;
    }

    @Override
    public CardId getCardId() {
        return this.cardId;
    }

    @Override
    public ChannelPaymentEnum getChannelPayment() {
        return this.channelPayment;
    }

    @Override
    public void close() {
        isNotConditional(isNull(getPaymentApprobation().getApprobationDate()),
                new PaymentException(PAYMENT_IS_STILL_IN_APPROBATION));

        softDelete();
    }


    @Override
    public void validateIfPaymentIsPossible(Amount available, Amount total, DateRange dateRange) {

        isNotConditional(!dateRange.ensureWithinRange(paymentApprobation.getDate().toLocalDate()),
                new PaymentException(DATE_NOT_WITHIN_RANGE));

        isNotConditional(total.esIgual(available),
                new PaymentException(PAYMENT_IT_NOT_NECCESARY));

        var totalAvailableFraction = total.menos(available).dividir(3);

        isNotConditional(totalAvailableFraction.estaFaltando(getPaymentAmount()),
                new PaymentException(NORMAL_PAYMENT_MUST_BE_MORE_THAN_MINIMUN));

        isNotConditional(!total.esIgual(available.mas(getPaymentAmount())),
                new PaymentException(NORMAL_PAYMENT_MUST_BE_LESS_THAN_TOTAL));
    }

    public static NormalPaymentBuilder builder() {
        return new NormalPaymentBuilder();
    }

    public static class NormalPaymentBuilder {
        private PaymentId id;
        private StatusEnum status;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private Amount paymentAmount;
        private Approbation paymentApprobation;
        private CategoryPaymentEnum category;
        private CardId cardId;
        private ChannelPaymentEnum channelPayment;

        public NormalPaymentBuilder id(UUID id) {
            this.id = PaymentId.create(id);
            return this;
        }

        public NormalPaymentBuilder status(StatusEnum status) {
            this.status = status;
            return this;
        }

        public NormalPaymentBuilder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public NormalPaymentBuilder updatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public NormalPaymentBuilder paymentAmount(BigDecimal amount, Currency currency) {
            isNotNull(amount, new PaymentException(PAYMENT_AMOUNT_NOT_NULL));
            isNotNull(currency, new PaymentException(PAYMENT_CURRENCY_NOT_NULL));
            this.paymentAmount = Amount.create(currency, amount);
            return this;
        }

        public NormalPaymentBuilder category(CategoryPaymentEnum category) {
            this.category = category;
            return this;
        }

        public NormalPaymentBuilder cardId(CardId cardId) {
            this.cardId = cardId;
            return this;
        }

        public NormalPaymentBuilder cardId(Long cardId) {
            isNotNull(cardId, new PaymentException(CARD_ID_NOT_NULL));
            this.cardId = CardId.create(cardId);
            return this;
        }

        public NormalPaymentBuilder channelPayment(ChannelPaymentEnum channelPayment) {
            this.channelPayment = channelPayment;
            return this;
        }

        public NormalPaymentBuilder approbation(LocalDateTime date, LocalDateTime approbationDate) {
            this.paymentApprobation = Approbation.create(date, approbationDate);
            return this;
        }

        public NormalPaymentBuilder approbation(LocalDateTime date) {
            this.paymentApprobation = Approbation.create(date);
            return this;
        }

        public NormalPayment build() {
            if (this.id == null) this.id = PaymentId.create(UUID.randomUUID());
            if (this.status == null) this.status = ACTIVE;
            if (this.createdDate == null) this.createdDate = LocalDateTime.now();
            if (this.paymentApprobation == null) this.paymentApprobation = Approbation.create(LocalDateTime.now());

            isNotNull(paymentAmount, new PaymentException(PAYMENT_AMOUNT_NOT_NULL));
            isNotNull(category, new PaymentException(PAYMENT_CATEGORY_NOT_NULL));
            isNotNull(cardId, new PaymentException(CARD_ID_NOT_NULL));
            isNotNull(channelPayment, new PaymentException(CHANNEL_PAYMENT_NOT_NULL));
            isNotConditional(paymentAmount.estaVacio(), new PaymentException(PAYMENT_AMOUNT_NOT_ZERO));

            return new NormalPayment(id, status, createdDate, updatedDate,
                    paymentAmount, paymentApprobation, category, cardId, channelPayment);
        }
    }
}