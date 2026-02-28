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

    public Balance nuevo() {
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

    public Boolean noDisponiblePago(LocalDate fecha) {
        return getDateRange().ensureWithinRange(fecha);
    }

    public Amount calcularPago(Amount pago) {
        return getAvailable().mas(pago);
    }

    public void aplicarPago(Amount pago) {
        this.available = getAvailable().mas(pago);
    }

    public Amount calcularConsumo(Amount consumo) {
        return getAvailable().menos(consumo);
    }

    public void aplicarConsumo(Amount consumo) {
        this.available = getAvailable().menos(consumo);
    }

    public void aplicarPagoAnulado(Amount pago) {
        this.available = getAvailable().menos(pago);
    }

    public void aplicarConsumoAnulado(Amount consumo) {
        this.available = getAvailable().mas(consumo);
    }

    public Boolean estaSobregirado() {
        BigDecimal limiteSobregiro = getTotal().getAmount().multiply(OVERCHARGE_LIMIT);
        BigDecimal limiteTotal = getTotal().getAmount().add(limiteSobregiro);

        return getAvailable().getAmount().compareTo(limiteTotal) > 0;
    }

    public void pagoAdelantado(Payment pago) {

        isNotNull(pago, new BalanceException(PAYMENT_CANNOT_BE_NULL));

        isConditional(!Objects.equals(pago.getCategory(), ADELANTADO),
                new BalanceException(DATE_NOT_WITHIN_RANGE));

        aplicarPago(pago.getPago());

        Amount totalDisponible = getAvailable();
        Amount totalBalance = getTotal();

        isConditional(totalDisponible.estaSobrando(totalBalance),
                new BalanceException(PAYMENT_CATEGORY_EXCEED_LIKE + totalDisponible.menos(totalBalance).toString()));
    }

    public void pago(Payment pago) {

        isNotNull(pago, new BalanceException(PAYMENT_CANNOT_BE_NULL));

        isConditional(noDisponiblePago(pago.getDiaPago()),
                new BalanceException(DATE_NOT_WITHIN_RANGE));

        Amount totalDisponible = getAvailable();
        Amount totalBalance = getTotal();

        isConditional(totalBalance.esIgual(totalDisponible) && !Objects.equals(pago.getCategory(), CategoryPaymentEnum.TOTAL),
                new BalanceException(PAYMENT_CATEGORY_NOT_SAME_AS_PAYMENT));
        isConditional(totalDisponible.estaSobrando(totalBalance),
                new BalanceException(PAYMENT_CATEGORY_EXCEED_LIKE + totalDisponible.menos(totalBalance).toString()));

        aplicarPago(pago.getPago());
    }

    public void anularConsumo(Consumption consumption) {
        isNotNull(consumption, new BalanceException(CONSUMPTION_CANNOT_BE_NULL));
        aplicarConsumoAnulado(consumption.getConsumo());
        consumption.softDelete();
    }

    public void anularPago(Payment pago) {
        isNotNull(pago, new BalanceException(PAYMENT_CANNOT_BE_NULL));
        aplicarPagoAnulado(pago.getPago());
        pago.softDelete();
    }

    public void cerrar()
    {
        softDelete();
    }
}
