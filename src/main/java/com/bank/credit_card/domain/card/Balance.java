package com.bank.credit_card.domain.card;

import com.bank.credit_card.domain.base.GenericDomain;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.DateRange;
import com.bank.credit_card.domain.card.vo.IdentifierId;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.domain.payment.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static com.bank.credit_card.domain.base.vo.DateRangeErrorMessage.DATE_NOT_WITHIN_RANGE;
import static com.bank.credit_card.domain.card.BalanceErrorMessage.*;
import static com.bank.credit_card.domain.card.CardConstant.OVERCHARGE_LIMIT;
import static com.bank.credit_card.domain.card.CardErrorMessage.PAYMENT_CATEGORY_EXCEED_LIKE;
import static com.bank.credit_card.domain.card.CardErrorMessage.PAYMENT_CATEGORY_NOT_SAME_AS_PAYMENT;
import static com.bank.credit_card.domain.card.CategoryPaymentEnum.ADELANTADO;
import static com.bank.credit_card.domain.util.Validation.isConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Balance extends GenericDomain<Long> {
    private final Amount total;
    private final Amount old;
    private final DateRange dateRange;
    private Amount available;
    private final IdentifierId identifierId;

    private Balance(Long id, Amount total, Amount old, DateRange dateRange, Amount available, IdentifierId identifierId) {
        super(id);
        this.total = total;
        this.old = old;
        this.dateRange = dateRange;
        this.available = available;
        this.identifierId = identifierId;
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

    public IdentifierId getIdentifierId() {
        return identifierId;
    }

    public static Balance create(Long id,
                                 Amount total,
                                 DateRange dateRange,
                                 IdentifierId identifierId) {

        isNotNull(total, new BalanceException(TOTAL_AMOUNT_CANNOT_BE_NULL));
        isNotNull(dateRange, new BalanceException(DATE_RANGE_CANNOT_BE_NULL));

        return new Balance(
                id,
                total,
                total,
                dateRange,
                total,
                identifierId
        );
    }

    public static Balance create(Long id,
                                 Amount total,
                                 Amount old,
                                 DateRange dateRange,
                                 Amount available,
                                 IdentifierId identifierId) {

        isNotNull(total, new BalanceException(TOTAL_AMOUNT_CANNOT_BE_NULL));
        isNotNull(old, new BalanceException(OLD_AMOUNT_CANNOT_BE_NULL));
        isNotNull(dateRange, new BalanceException(DATE_RANGE_CANNOT_BE_NULL));
        isNotNull(available, new BalanceException(AVAILABLE_AMOUNT_CANNOT_BE_NULL));
        isNotNull(identifierId, new BalanceException(IDENTIFIER_ID_CANNOT_BE_NULL));

        return new Balance(
                id,
                total,
                old,
                dateRange,
                available,
                identifierId
        );
    }

    public static Balance create(Long id,
                                 Amount total,
                                 Amount old,
                                 DateRange dateRange,
                                 IdentifierId identifierId) {

        isNotNull(total, new BalanceException(TOTAL_AMOUNT_CANNOT_BE_NULL));
        isNotNull(old, new BalanceException(OLD_AMOUNT_CANNOT_BE_NULL));
        isNotNull(dateRange, new BalanceException(DATE_RANGE_CANNOT_BE_NULL));
        isNotNull(identifierId, new BalanceException(IDENTIFIER_ID_CANNOT_BE_NULL));

        return new Balance(
                id,
                total,
                old,
                dateRange,
                total,
                identifierId
        );
    }

    public Balance generate() {
        softDelete();

        return new Balance(
                null,
                Amount.create(getTotal().getCurrency(), getTotal().getAmount()),
                Amount.create(getOld().getCurrency(), getOld().getAmount()),
                DateRange.create(getDateRange()),
                getAvailable(),
                getIdentifierId()
        );
    }

    public Boolean UnavailablePayment(LocalDate fecha) {
        return getDateRange().ensureWithinRange(fecha);
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

    public void pagoAdelantado(Payment payment) {

        isNotNull(payment, new BalanceException(PAYMENT_CANNOT_BE_NULL));

        isConditional(!Objects.equals(payment.getCategory(), ADELANTADO),
                new BalanceException(DATE_NOT_WITHIN_RANGE));

        applyPayment(payment.getPaymentAmount());

        Amount totalAmount = getAvailable();
        Amount totalBalance = getTotal();

        isConditional(totalAmount.estaSobrando(totalBalance),
                new BalanceException(PAYMENT_CATEGORY_EXCEED_LIKE + totalAmount.menos(totalBalance).toString()));
    }

    public void pay(Payment payment) {

        isNotNull(payment, new BalanceException(PAYMENT_CANNOT_BE_NULL));

        isConditional(UnavailablePayment(payment.getPaymentDate()),
                new BalanceException(DATE_NOT_WITHIN_RANGE));

        Amount totalAmount = getAvailable();
        Amount totalBalance = getTotal();

        isConditional(totalBalance.esIgual(totalAmount) && !Objects.equals(payment.getCategory(), CategoryPaymentEnum.TOTAL),
                new BalanceException(PAYMENT_CATEGORY_NOT_SAME_AS_PAYMENT));
        isConditional(totalAmount.estaSobrando(totalBalance),
                new BalanceException(PAYMENT_CATEGORY_EXCEED_LIKE + totalAmount.menos(totalBalance).toString()));

        applyPayment(payment.getPaymentAmount());
    }

    public void cancellConsumption(Consumption consumption) {
        isNotNull(consumption, new BalanceException(CONSUMPTION_CANNOT_BE_NULL));
        applyCancelledConsumption(consumption.getConsumptionAmount());
        consumption.softDelete();
    }

    public void cancellPayment(Payment payment) {
        isNotNull(payment, new BalanceException(PAYMENT_CANNOT_BE_NULL));
        applyCancelledPayment(payment.getPaymentAmount());
        payment.softDelete();
    }

    public void close()
    {
        softDelete();
    }
}
