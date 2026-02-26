package com.bank.credit_card.domain.card.vo;

import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.card.CategoryPaymentEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.bank.credit_card.domain.card.vo.PaymentErrorMessage.*;
import static com.bank.credit_card.domain.util.Validation.isConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Payment {
    private final Amount pago;
    private final LocalDate diaPago;
    private final CategoryPaymentEnum category;

    private Payment(Amount pago, LocalDate diaPago, CategoryPaymentEnum category) {
        this.pago = pago;
        this.diaPago = diaPago;
        this.category = category;
    }

    public Amount getPago() {
        return pago;
    }

    public LocalDate getDiaPago() {
        return diaPago;
    }

    public CategoryPaymentEnum getCategory() {
        return category;
    }

    public static Payment create(Amount pago, LocalDate diaPago, CategoryPaymentEnum category) {

        isNotNull(pago, new PaymentException(PAYMENT_AMOUNT_NOT_NULL));
        isNotNull(diaPago, new PaymentException(PAYMENT_DAY_NOT_NULL));
        isNotNull(category, new PaymentException(PAYMENT_CATEGORY_NOT_NULL));
        isConditional(pago.estaVacio(), new PaymentException(PAYMENT_AMOUNT_NOT_ZERO));

        return new Payment(pago, diaPago, category);
    }

    public Payment descontar(BigDecimal descuento) {
        return Payment.create(getPago().descuento(descuento), getDiaPago(), getCategory());
    }
}

