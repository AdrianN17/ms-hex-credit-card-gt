package com.bank.credit_card.application.service.usecase.business;

import com.bank.credit_card.application.error.payment.ApplicationPaymentException;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.port.out.payment.query.LoadPaymentCurrencyPort;
import com.bank.credit_card.application.port.out.payment.usecase.LoadPaymentPort;
import com.bank.credit_card.application.port.out.payment.usecase.SavePaymentPort;
import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.domain.payment.vo.PaymentId;

import java.util.UUID;

import static com.bank.credit_card.application.error.payment.PaymentApplicationErrorMessage.*;

public class BusinessServicePaymentImpl implements BusinessServicePayment {

    private final LoadCurrencyPort loadCurrencyPort;
    private final LoadPaymentPort loadPaymentPort;
    private final SavePaymentPort savePaymentPort;
    private final LoadPaymentCurrencyPort loadConsumptionCurrencyPort;

    public BusinessServicePaymentImpl(LoadCurrencyPort loadCurrencyPort, LoadPaymentPort loadPaymentPort, SavePaymentPort savePaymentPort, LoadPaymentCurrencyPort loadConsumptionCurrencyPort) {
        this.loadCurrencyPort = loadCurrencyPort;
        this.loadPaymentPort = loadPaymentPort;
        this.savePaymentPort = savePaymentPort;
        this.loadConsumptionCurrencyPort = loadConsumptionCurrencyPort;
    }

    @Override
    public Payment get(Long cardId, UUID paymentId) {
        var paymentCurrencyValue = loadConsumptionCurrencyPort
                .load(paymentId, cardId.toString())
                .orElseThrow(() -> new ApplicationPaymentException(PAYMENT_CURRENCY_NOT_FOUND));

        var paymentCurrency = loadCurrencyPort.load(paymentCurrencyValue)
                .orElseThrow(() -> new ApplicationPaymentException(PAYMENT_CURRENCY_NOT_FOUND));

        return loadPaymentPort
                .load(paymentId, cardId.toString(), paymentCurrency)
                .orElseThrow(() -> new ApplicationPaymentException(PAYMENT_NOT_FOUND));
    }

    @Override
    public PaymentId save(Payment payment) {
        return this.savePaymentPort.save(payment)
                .map(PaymentId::create)
                .orElseThrow(() -> new ApplicationPaymentException(FAILED_TO_CREATE_PAYMENT));
    }
}
