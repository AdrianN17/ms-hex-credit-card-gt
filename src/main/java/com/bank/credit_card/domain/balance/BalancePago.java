package com.bank.credit_card.domain.balance;


import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.base.vo.DateRange;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.generic.GenericDomain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.bank.credit_card.domain.balance.BalanceConstant.OVERCHARGE_LIMIT;
import static com.bank.credit_card.domain.balance.BalanceErrorMessage.*;
import static com.bank.credit_card.domain.base.enums.StatusEnum.ACTIVE;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;


public class BalancePago extends GenericDomain<BalanceId> implements Balance {

    private final CardId cardId;
    private final Amount total;
    private final Amount old;
    private final DateRange dateRange;
    private Amount available;

    protected BalancePago(BalanceId id,
                          StatusEnum status,
                          LocalDateTime createdDate,
                          LocalDateTime updatedDate,
                          CardId cardId,
                          Amount total,
                          Amount old,
                          DateRange dateRange,
                          Amount available) {
        super(id, status, createdDate, updatedDate);
        this.cardId = cardId;
        this.total = total;
        this.old = old;
        this.dateRange = dateRange;
        this.available = available;
    }

    @Override
    public CardId getCardId() {
        return this.cardId;
    }

    @Override
    public Amount getTotal() {
        return this.total;
    }

    @Override
    public Amount getOld() {
        return this.old;
    }

    @Override
    public DateRange getDateRange() {
        return this.dateRange;
    }

    @Override
    public Amount getAvailable() {
        return this.available;
    }

    @Override
    public void close() {
        softDelete();
    }

    @Override
    public Boolean isOvercharged() {
        BigDecimal limitOvercharge = getTotal().getAmount().multiply(OVERCHARGE_LIMIT);
        BigDecimal totalLimit = getTotal().getAmount().add(limitOvercharge);
        return getAvailable().getAmount().compareTo(totalLimit) > 0;
    }

    @Override
    public void apply(Amount amount) {
        isNotNull(amount, new BalanceException(PAYMENT_CANNOT_BE_NULL));
        this.available = getAvailable().mas(amount);
        isNotConditional(getAvailable().estaSobrando(getTotal()),
                new BalanceException(PAYMENT_CATEGORY_EXCEED_LIKE + getAvailable().menos(getTotal()).toString()));
    }

    @Override
    public void cancel(Amount amount) {
        isNotNull(amount, new BalanceException(PAYMENT_CANNOT_BE_NULL));
        this.available = getAvailable().menos(amount);
    }

    public static BalancePagoBuilder builder() {
        return new BalancePagoBuilder();
    }

    public static class BalancePagoBuilder {
        private BalanceId id;
        private StatusEnum status;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private CardId cardId;
        private Amount total;
        private Amount old;
        private Amount available;
        private DateRange dateRange;

        private Currency currency;

        public BalancePagoBuilder balanceId(Long balanceId) {
            this.id = BalanceId.create(balanceId);
            return this;
        }

        public BalancePagoBuilder cardId(Long cardId) {
            this.cardId = CardId.create(cardId);
            return this;
        }

        public BalancePagoBuilder currency(Currency currency) {
            isNotNull(currency, new BalanceException(CURRENCY_CANNOT_BE_NULL));
            this.currency = currency;
            return this;
        }

        public BalancePagoBuilder total(BigDecimal total) {
            isNotNull(total, new BalanceException(TOTAL_AMOUNT_CANNOT_BE_NULL));
            this.total = Amount.create(currency, total);
            return this;
        }

        public BalancePagoBuilder old(BigDecimal old) {
            isNotNull(old, new BalanceException(OLD_AMOUNT_CANNOT_BE_NULL));
            this.old = Amount.create(currency, old);
            return this;
        }

        public BalancePagoBuilder available(BigDecimal available) {
            isNotNull(available, new BalanceException(AVAILABLE_AMOUNT_CANNOT_BE_NULL));
            this.available = Amount.create(currency, available);
            return this;
        }

        public BalancePagoBuilder dateRange(Short paymentDay) {
            isNotNull(paymentDay, new BalanceException(DATE_RANGE_CANNOT_BE_NULL));
            this.dateRange = DateRange.create(paymentDay);
            return this;
        }

        public BalancePagoBuilder dateRange(LocalDate startDate, LocalDate endDate) {
            isNotNull(startDate, new BalanceException(DATE_RANGE_CANNOT_BE_NULL));
            isNotNull(endDate, new BalanceException(DATE_RANGE_CANNOT_BE_NULL));
            this.dateRange = DateRange.create(startDate, endDate);
            return this;
        }

        public BalancePagoBuilder status(Integer status) {
            this.status = StatusEnum.ofValue(status).orElseThrow();
            return this;
        }

        public BalancePagoBuilder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public BalancePagoBuilder updatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public BalancePago build() {
            isNotNull(id, new BalanceException(ID_CANNOT_BE_NULL));
            isNotNull(cardId, new BalanceException(CARD_ID_CANNOT_BE_NULL));
            isNotNull(total, new BalanceException(TOTAL_AMOUNT_CANNOT_BE_NULL));
            isNotNull(dateRange, new BalanceException(DATE_RANGE_CANNOT_BE_NULL));

            if (this.status == null) this.status = ACTIVE;
            if (this.createdDate == null) this.createdDate = LocalDateTime.now();

            if (this.old == null) this.old = this.total;
            if (this.available == null) this.available = this.total;

            return new BalancePago(id, status, createdDate, updatedDate,
                    cardId, total, old, dateRange, available);
        }
    }
}
