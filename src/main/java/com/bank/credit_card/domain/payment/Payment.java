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
    private final Amount pago;
    private final LocalDate diaPago;
    private final CategoryPaymentEnum category;
    private final IdentifierId identifierId;

    private Payment(Long id, Amount pago, LocalDate diaPago, CategoryPaymentEnum category, IdentifierId identifierId) {
        super(id);
        this.pago = pago;
        this.diaPago = diaPago;
        this.category = category;
        this.identifierId = identifierId;
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

    public IdentifierId getIdentifierId() {
        return identifierId;
    }

    public static Payment create(Long id, Amount pago, LocalDate diaPago, CategoryPaymentEnum category, IdentifierId identifierId) {

        isNotNull(pago, new PaymentException(PAYMENT_AMOUNT_NOT_NULL));
        isNotNull(diaPago, new PaymentException(PAYMENT_DAY_NOT_NULL));
        isNotNull(category, new PaymentException(PAYMENT_CATEGORY_NOT_NULL));
        isConditional(pago.estaVacio(), new PaymentException(PAYMENT_AMOUNT_NOT_ZERO));
        isNotNull(identifierId, new PaymentException(IDENTIFIER_ID_NOT_NULL));

        return new Payment(id, pago, diaPago, category, identifierId);
    }

    public static Payment create(Amount pago, LocalDate diaPago, CategoryPaymentEnum category, IdentifierId identifierId) {

        isNotNull(pago, new PaymentException(PAYMENT_AMOUNT_NOT_NULL));
        isNotNull(diaPago, new PaymentException(PAYMENT_DAY_NOT_NULL));
        isNotNull(category, new PaymentException(PAYMENT_CATEGORY_NOT_NULL));
        isConditional(pago.estaVacio(), new PaymentException(PAYMENT_AMOUNT_NOT_ZERO));
        isNotNull(identifierId, new PaymentException(IDENTIFIER_ID_NOT_NULL));

        return new Payment(-1L, pago, diaPago, category, identifierId);
    }

    public Payment descontar(BigDecimal descuento) {
        return Payment.create(getPago().descuento(descuento), getDiaPago(), getCategory(), getIdentifierId());
    }
}

