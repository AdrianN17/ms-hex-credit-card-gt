package com.bank.credit_card.domain.payment;

import com.bank.credit_card.domain.base.GenericDomain;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.card.CategoryPaymentEnum;
import com.bank.credit_card.domain.card.vo.IdentifierId;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.bank.credit_card.domain.payment.PaymentErrorMessage.*;
import static com.bank.credit_card.domain.util.Validation.isConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Payment extends GenericDomain<Long> {
    private final Amount paymentAmount;
    private final LocalDate paymentDate;
    private final CategoryPaymentEnum category;
    private final IdentifierId identifierId;

    private Payment(Long id, Amount paymentAmount, LocalDate paymentDate, CategoryPaymentEnum category, IdentifierId identifierId) {
        super(id);
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.category = category;
        this.identifierId = identifierId;
    }

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public CategoryPaymentEnum getCategory() {
        return category;
    }

    public IdentifierId getIdentifierId() {
        return identifierId;
    }

    public static Payment create(Long id, Amount paymentAmount, LocalDate paymentDate, CategoryPaymentEnum category, IdentifierId identifierId) {

        isNotNull(paymentAmount, new PaymentException(PAYMENT_AMOUNT_NOT_NULL));
        isNotNull(paymentDate, new PaymentException(PAYMENT_DAY_NOT_NULL));
        isNotNull(category, new PaymentException(PAYMENT_CATEGORY_NOT_NULL));
        isConditional(paymentAmount.estaVacio(), new PaymentException(PAYMENT_AMOUNT_NOT_ZERO));
        isNotNull(identifierId, new PaymentException(IDENTIFIER_ID_NOT_NULL));

        return new Payment(id, paymentAmount, paymentDate, category, identifierId);
    }

    public static Payment create(Amount paymentAmount, LocalDate paymentDate, CategoryPaymentEnum category, IdentifierId identifierId) {

        isNotNull(paymentAmount, new PaymentException(PAYMENT_AMOUNT_NOT_NULL));
        isNotNull(paymentDate, new PaymentException(PAYMENT_DAY_NOT_NULL));
        isNotNull(category, new PaymentException(PAYMENT_CATEGORY_NOT_NULL));
        isConditional(paymentAmount.estaVacio(), new PaymentException(PAYMENT_AMOUNT_NOT_ZERO));
        isNotNull(identifierId, new PaymentException(IDENTIFIER_ID_NOT_NULL));

        return new Payment(-1L, paymentAmount, paymentDate, category, identifierId);
    }

    public Payment discount(BigDecimal discount) {
        return Payment.create(getPaymentAmount().descuento(discount), getPaymentDate(), getCategory(), getIdentifierId());
    }
}

