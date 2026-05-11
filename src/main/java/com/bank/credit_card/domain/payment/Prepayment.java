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
import java.util.UUID;

import static com.bank.credit_card.domain.base.constants.DateRangeErrorMessage.DATE_NOT_WITHIN_RANGE;
import static com.bank.credit_card.domain.base.enums.StatusEnum.ACTIVE;
import static com.bank.credit_card.domain.payment.CategoryPaymentEnum.ADELANTADO;
import static com.bank.credit_card.domain.payment.PaymentErrorMessage.*;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;
import static java.util.Objects.isNull;

public class Prepayment extends GenericDomain<PaymentId> implements Payment {
    private final Amount paymentAmount;
    private final Approbation paymentApprobation;
    private final CategoryPaymentEnum category;
    private final CardId cardId;
    private final ChannelPaymentEnum channelPayment;

    public Prepayment(PaymentId paymentId,
                      StatusEnum status,
                      LocalDateTime createdDate,
                      LocalDateTime updatedDate,
                      Amount paymentAmount,
                      Approbation paymentApprobation,
                      CardId cardId,
                      ChannelPaymentEnum channelPayment) {

        super(paymentId, status, createdDate, updatedDate);

        this.paymentAmount = paymentAmount;
        this.paymentApprobation = paymentApprobation;
        this.category = ADELANTADO;
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

        isNotConditional(dateRange.ensureWithinRange(paymentApprobation.getDate().toLocalDate()),
                new PaymentException(DATE_NOT_WITHIN_RANGE));

        isNotConditional(total.esIgual(getPaymentAmount()),
                new PaymentException(PAYMENT_IT_NOT_NECCESARY));
    }

    public static PrepaymentBuilder builder() {
        return new PrepaymentBuilder();
    }

    public static class PrepaymentBuilder {
        private PaymentId id;
        private StatusEnum status;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private Amount paymentAmount;
        private Approbation paymentApprobation;
        private CardId cardId;
        private ChannelPaymentEnum channelPayment;

        public PrepaymentBuilder id(UUID id) {
            this.id = PaymentId.create(id);
            return this;
        }

        public PrepaymentBuilder status(StatusEnum status) {
            this.status = status;
            return this;
        }

        public PrepaymentBuilder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public PrepaymentBuilder updatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public PrepaymentBuilder paymentAmount(BigDecimal amount, Currency currency) {
            isNotNull(amount, new PaymentException(PAYMENT_AMOUNT_NOT_NULL));
            isNotNull(currency, new PaymentException(PAYMENT_CURRENCY_NOT_NULL));
            this.paymentAmount = Amount.create(currency, amount);
            return this;
        }

        public PrepaymentBuilder cardId(CardId cardId) {
            this.cardId = cardId;
            return this;
        }

        public PrepaymentBuilder cardId(Long cardId) {
            isNotNull(cardId, new PaymentException(CARD_ID_NOT_NULL));
            this.cardId = CardId.create(cardId);
            return this;
        }

        public PrepaymentBuilder channelPayment(ChannelPaymentEnum channelPayment) {
            isNotNull(channelPayment, new PaymentException(CHANNEL_PAYMENT_NOT_NULL));
            this.channelPayment = channelPayment;
            return this;
        }

        public PrepaymentBuilder approbation(LocalDateTime date, LocalDateTime approbationDate) {
            this.paymentApprobation = Approbation.create(date, approbationDate);
            return this;
        }

        public PrepaymentBuilder approbation(LocalDateTime date) {
            this.paymentApprobation = Approbation.create(date);
            return this;
        }

        public Prepayment build() {
            if (this.id == null) this.id = PaymentId.create(UUID.randomUUID());
            if (this.status == null) this.status = ACTIVE;
            if (this.createdDate == null) this.createdDate = LocalDateTime.now();
            if (this.paymentApprobation == null) this.paymentApprobation = Approbation.create(LocalDateTime.now());

            isNotNull(paymentAmount, new PaymentException(PAYMENT_AMOUNT_NOT_NULL));
            isNotNull(cardId, new PaymentException(CARD_ID_NOT_NULL));
            isNotNull(channelPayment, new PaymentException(CHANNEL_PAYMENT_NOT_NULL));
            isNotConditional(paymentAmount.estaVacio(), new PaymentException(PAYMENT_AMOUNT_NOT_ZERO));

            return new Prepayment(id, status, createdDate, updatedDate,
                    paymentAmount, paymentApprobation, cardId, channelPayment);
        }
    }
}
