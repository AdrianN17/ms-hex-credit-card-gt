package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.benefit.ApplicationBenefitException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.error.payment.ApplicationPaymentException;
import com.bank.credit_card.application.port.in.command.CardCancelPaymentCommand;
import com.bank.credit_card.application.port.in.usecase.CardCancelPaymentUseCase;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.payment.usecase.LoadPaymentPort;
import com.bank.credit_card.application.port.out.payment.usecase.SavePaymentPort;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.payment.Payment;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_UPDATE_BALANCE;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.FAILED_TO_UPDATE_BENEFIT;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_NOT_FOUND;
import static com.bank.credit_card.application.error.consumption.ConsumptionApplicationErrorMessage.FAILED_TO_UPDATE_CONSUMPTION;
import static com.bank.credit_card.application.error.payment.PaymentApplicationErrorMessage.PAYMENT_NOT_FOUND;

public class CardCancelPaymentService implements CardCancelPaymentUseCase {

    private final LoadCardPort loadCardPort;
    private final LoadPaymentPort loadPaymentPort;
    private final SaveBenefitPort saveBenefitPort;
    private final SaveBalancePort saveBalancePort;
    private final SavePaymentPort savePaymentPort;

    public CardCancelPaymentService(LoadCardPort loadCardPort, LoadPaymentPort loadPaymentPort, SaveBenefitPort saveBenefitPort, SaveBalancePort saveBalancePort, SavePaymentPort savePaymentPort) {
        this.loadCardPort = loadCardPort;
        this.loadPaymentPort = loadPaymentPort;
        this.saveBenefitPort = saveBenefitPort;
        this.saveBalancePort = saveBalancePort;
        this.savePaymentPort = savePaymentPort;
    }

    @Override
    public void cancellPayment(CardCancelPaymentCommand cardCancelPaymentCommand) {
        Payment payment = loadPaymentPort
                .load(cardCancelPaymentCommand.paymentId())
                .orElseThrow(() -> new ApplicationPaymentException(PAYMENT_NOT_FOUND));
        Card card = loadCardPort
                .load(cardCancelPaymentCommand.cardId())
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        card.cancelPayment(payment);

        this.savePaymentPort.save(payment).orElseThrow(() -> new ApplicationPaymentException(FAILED_TO_UPDATE_CONSUMPTION));
        this.saveBalancePort.save(card.getBalance()).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_UPDATE_BALANCE));
        this.saveBenefitPort.save(card.getBenefit()).orElseThrow(() -> new ApplicationBenefitException(FAILED_TO_UPDATE_BENEFIT));
    }
}
