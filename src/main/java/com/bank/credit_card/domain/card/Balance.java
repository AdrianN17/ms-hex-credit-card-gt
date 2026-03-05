package com.bank.credit_card.domain.card;

import com.bank.credit_card.domain.base.StatusEnum;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.DateRange;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.domain.generator.CardIdGenerator;
import com.bank.credit_card.domain.generic.GenericDomain;
import com.bank.credit_card.domain.payment.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.bank.credit_card.domain.base.StatusEnum.ACTIVE;
import static com.bank.credit_card.domain.base.vo.DateRangeErrorMessage.DATE_NOT_WITHIN_RANGE;
import static com.bank.credit_card.domain.card.BalanceErrorMessage.*;
import static com.bank.credit_card.domain.card.CardConstant.OVERCHARGE_LIMIT;
import static com.bank.credit_card.domain.card.CardErrorMessage.PAYMENT_CATEGORY_EXCEED_LIKE;
import static com.bank.credit_card.domain.card.CardErrorMessage.PAYMENT_CATEGORY_NOT_SAME_AS_PAYMENT;
import static com.bank.credit_card.domain.card.CategoryPaymentEnum.ADELANTADO;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Balance extends GenericDomain<Long> {
    private final Amount total;
    private final Amount old;
    private final DateRange dateRange;
    private Amount available;
    private final CardId cardId;

    private Balance(
            Long id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Amount total,
            Amount old,
            DateRange dateRange,
            Amount available,
            CardId cardId) {

        super(id, status, createdDate, updatedDate);
        this.total = total;
        this.old = old;
        this.dateRange = dateRange;
        this.available = available;
        this.cardId = cardId;
    }

    public Amount getTotal() {
        return total;
    }

    public Amount getOld() {
        return old;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public Amount getAvailable() {
        return available;
    }

    public CardId getCardId() {
        return cardId;
    }

    public static Balance create(
            CardIdGenerator cardIdGenerator,
            Amount total,
            DateRange dateRange,
            CardId cardId) {

        isNotNull(cardIdGenerator, new BalanceException(CARD_GENERATOR_CANNOT_BE_NULL));

        isNotNull(total, new BalanceException(TOTAL_AMOUNT_CANNOT_BE_NULL));
        isNotNull(dateRange, new BalanceException(DATE_RANGE_CANNOT_BE_NULL));
        isNotNull(cardId, new BalanceException(CARD_ID_CANNOT_BE_NULL));

        return new Balance(
                cardIdGenerator.nextId(),
                ACTIVE,
                LocalDateTime.now(),
                null,
                total,
                total,
                dateRange,
                total,
                cardId
        );
    }

    public static Balance create(
            Long id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Amount total,
            Amount old,
            DateRange dateRange,
            Amount available,
            CardId cardId) {

        isNotNull(total, new BalanceException(TOTAL_AMOUNT_CANNOT_BE_NULL));
        isNotNull(old, new BalanceException(OLD_AMOUNT_CANNOT_BE_NULL));
        isNotNull(dateRange, new BalanceException(DATE_RANGE_CANNOT_BE_NULL));
        isNotNull(available, new BalanceException(AVAILABLE_AMOUNT_CANNOT_BE_NULL));
        isNotNull(cardId, new BalanceException(CARD_ID_CANNOT_BE_NULL));

        return new Balance(
                id,
                status,
                createdDate,
                updatedDate,
                total,
                old,
                dateRange,
                available,
                cardId
        );
    }

    public Boolean UnavailablePayment(LocalDateTime fecha) {
        return getDateRange().ensureWithinRange(fecha.toLocalDate());
    }

    public Amount calculatePayment(Amount payment) {
        return getAvailable().mas(payment);
    }

    public void applyPayment(Amount payment) {
        this.available = getAvailable().mas(payment);
    }

    public Amount calculateConsumption(Amount consumption) {
        return getAvailable().menos(consumption);
    }

    public void applyConsumption(Amount consumption) {
        this.available = getAvailable().menos(consumption);
    }

    public void applyCancelledPayment(Amount payment) {
        this.available = getAvailable().menos(payment);
    }

    public void applyCancelledConsumption(Amount consumption) {
        this.available = getAvailable().mas(consumption);
    }

    public Boolean isOvercharged() {
        BigDecimal limitOvercharge = getTotal().getAmount().multiply(OVERCHARGE_LIMIT);
        BigDecimal totalLimit = getTotal().getAmount().add(limitOvercharge);

        return getAvailable().getAmount().compareTo(totalLimit) > 0;
    }

    public void prePay(Payment payment) {

        isNotNull(payment, new BalanceException(PAYMENT_CANNOT_BE_NULL));

        isNotConditional(!Objects.equals(payment.getCategory(), ADELANTADO),
                new BalanceException(DATE_NOT_WITHIN_RANGE));

        applyPayment(payment.getPaymentAmount());

        Amount totalAmount = getAvailable();
        Amount totalBalance = getTotal();

        isNotConditional(totalAmount.estaSobrando(totalBalance),
                new BalanceException(PAYMENT_CATEGORY_EXCEED_LIKE + totalAmount.menos(totalBalance).toString()));
    }

    public void pay(Payment payment) {

        isNotNull(payment, new BalanceException(PAYMENT_CANNOT_BE_NULL));

        isNotConditional(UnavailablePayment(payment.getPaymentDate()),
                new BalanceException(DATE_NOT_WITHIN_RANGE));

        Amount totalAmount = getAvailable();
        Amount totalBalance = getTotal();

        isNotConditional(totalBalance.esIgual(totalAmount) && !Objects.equals(payment.getCategory(), CategoryPaymentEnum.TOTAL),
                new BalanceException(PAYMENT_CATEGORY_NOT_SAME_AS_PAYMENT));
        isNotConditional(totalAmount.estaSobrando(totalBalance),
                new BalanceException(PAYMENT_CATEGORY_EXCEED_LIKE + totalAmount.menos(totalBalance).toString()));

        applyPayment(payment.getPaymentAmount());
    }

    public void consumptionSplitted(List<Consumption> consumptions) {
        isNotNull(consumptions, new BalanceException(CONSUMPTIONS_CANNOT_BE_NULL));
        isNotConditional(consumptions.isEmpty(), new BalanceException(CONSUMPTIONS_CANNOT_BE_EMPTY));

        consumptions.forEach(consumption -> {
            isNotNull(consumption, new BalanceException(CONSUMPTION_CANNOT_BE_NULL));
            applyConsumption(consumption.getConsumptionAmount());
        });
    }

    public void cancelConsumption(Consumption consumption) {
        isNotNull(consumption, new BalanceException(CONSUMPTION_CANNOT_BE_NULL));
        applyCancelledConsumption(consumption.getConsumptionAmount());
        consumption.cancel();
    }

    public void cancelPayment(Payment payment) {
        isNotNull(payment, new BalanceException(PAYMENT_CANNOT_BE_NULL));
        applyCancelledPayment(payment.getPaymentAmount());
        payment.cancel();
    }

    public void close() {
        softDelete();
    }
}
