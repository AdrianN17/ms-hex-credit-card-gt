package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.error.payment.ApplicationPaymentException;
import com.bank.credit_card.application.port.in.command.CardCancelPaymentCommand;
import com.bank.credit_card.application.port.in.usecase.CardCancelPaymentUseCase;
import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.port.out.payment.query.LoadPaymentCurrencyPort;
import com.bank.credit_card.application.port.out.payment.usecase.LoadPaymentPort;
import com.bank.credit_card.application.port.out.payment.usecase.SavePaymentPort;
import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.payment.Payment;

import java.util.UUID;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.BALANCE_NOT_FOUND;
import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_UPDATE_BALANCE;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_CURRENCY_NOT_FOUND;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_NOT_FOUND;
import static com.bank.credit_card.application.error.payment.PaymentApplicationErrorMessage.*;

public class CardCancelPaymentService implements CardCancelPaymentUseCase {

    private final LoadCardPort loadCardPort;
    private final LoadBalancePort loadBalancePort;
    private final LoadPaymentPort loadPaymentPort;
    private final SaveBalancePort saveBalancePort;
    private final SavePaymentPort savePaymentPort;
    private final LoadCurrencyPort loadCurrencyPort;
    private final LoadCardCurrencyPort loadCardCurrencyPort;
    private final LoadPaymentCurrencyPort loadConsumptionCurrencyPort;

    public CardCancelPaymentService(LoadCardPort loadCardPort,
                                    LoadBalancePort loadBalancePort,
                                    LoadPaymentPort loadPaymentPort,
                                    SaveBalancePort saveBalancePort,
                                    SavePaymentPort savePaymentPort,
                                    LoadCurrencyPort loadCurrencyPort,
                                    LoadCardCurrencyPort loadCardCurrencyPort,
                                    LoadPaymentCurrencyPort loadConsumptionCurrencyPort) {
        this.loadCardPort = loadCardPort;
        this.loadBalancePort = loadBalancePort;
        this.loadPaymentPort = loadPaymentPort;
        this.saveBalancePort = saveBalancePort;
        this.savePaymentPort = savePaymentPort;
        this.loadCurrencyPort = loadCurrencyPort;
        this.loadCardCurrencyPort = loadCardCurrencyPort;
        this.loadConsumptionCurrencyPort = loadConsumptionCurrencyPort;
    }


    @Override
    public UUID cancelPayment(CardCancelPaymentCommand cardCancelPaymentCommand) {

        CurrencyEnum cardCurrencyEnum = loadCardCurrencyPort.load(cardCancelPaymentCommand.cardId())
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        CurrencyEnum paymentCurrencyEnum = loadConsumptionCurrencyPort.load(cardCancelPaymentCommand.paymentId(), cardCancelPaymentCommand.cardId().toString())
                .orElseThrow(() -> new ApplicationPaymentException(PAYMENT_CURRENCY_NOT_FOUND));

        Currency cardCurrency = loadCurrencyPort.load(cardCurrencyEnum)
                .orElseThrow(() -> new ApplicationCardException(CARD_CURRENCY_NOT_FOUND));

        Currency paymentCurrency = loadCurrencyPort.load(paymentCurrencyEnum)
                .orElseThrow(() -> new ApplicationPaymentException(PAYMENT_CURRENCY_NOT_FOUND));

        Payment payment = loadPaymentPort
                .load(cardCancelPaymentCommand.paymentId(), cardCancelPaymentCommand.cardId().toString(), paymentCurrency)
                .orElseThrow(() -> new ApplicationPaymentException(PAYMENT_NOT_FOUND));

        Card card = loadCardPort
                .load(cardCancelPaymentCommand.cardId(), cardCurrency)
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        Balance balance = loadBalancePort
                .load(cardCancelPaymentCommand.cardId(), cardCurrency)
                .orElseThrow(() -> new ApplicationBalanceException(BALANCE_NOT_FOUND));

        card.cancelPayment(balance, payment);

        this.savePaymentPort.save(payment).orElseThrow(() -> new ApplicationPaymentException(FAILED_TO_UPDATE_PAYMENT));
        this.saveBalancePort.save(balance).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_UPDATE_BALANCE));

        return payment.getId();
    }
}
