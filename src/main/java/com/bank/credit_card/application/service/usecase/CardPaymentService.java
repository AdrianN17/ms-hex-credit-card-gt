package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.benefit.ApplicationBenefitException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.error.payment.ApplicationPaymentException;
import com.bank.credit_card.application.port.in.command.CardProcessPaymentCommand;
import com.bank.credit_card.application.port.in.usecase.CardProcessPaymentUseCase;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.payment.usecase.SavePaymentPort;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.benefit.Point;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.payment.Payment;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_UPDATE_BALANCE;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.FAILED_TO_UPDATE_BENEFIT;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_NOT_FOUND;
import static com.bank.credit_card.application.error.payment.PaymentApplicationErrorMessage.FAILED_TO_CREATE_PAYMENT;
import static java.util.Objects.isNull;

public class CardPaymentService implements CardProcessPaymentUseCase {

    private final LoadCardPort loadCardPort;
    private final SaveBenefitPort saveBenefitPort;
    private final SaveBalancePort saveBalancePort;
    private final SavePaymentPort savePaymentPort;

    public CardPaymentService(LoadCardPort loadCardPort, SaveBenefitPort saveBenefitPort, SaveBalancePort saveBalancePort, SavePaymentPort savePaymentPort) {
        this.loadCardPort = loadCardPort;
        this.saveBenefitPort = saveBenefitPort;
        this.saveBalancePort = saveBalancePort;
        this.savePaymentPort = savePaymentPort;
    }

    @Override
    public Payment processPayment(CardProcessPaymentCommand cardProcessPaymentCommand) {

        Card card = loadCardPort
                .load(cardProcessPaymentCommand.cardId())
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        Payment payment = Payment.create(
                Amount.create(
                        Currency.create(
                                cardProcessPaymentCommand.currency(),
                                cardProcessPaymentCommand.exchangeRate()),
                        cardProcessPaymentCommand.amount()),
                cardProcessPaymentCommand.category(),
                CardId.create(cardProcessPaymentCommand.cardId()),
                cardProcessPaymentCommand.channelPayment()
        );

        if (isNull(cardProcessPaymentCommand.pointsUsed())) {
            card.pay(payment);
        } else {
            Point point = Point.create(cardProcessPaymentCommand.pointsUsed());
            card.pay(payment, point);
            this.saveBenefitPort.save(card.getBenefit()).orElseThrow(() -> new ApplicationBenefitException(FAILED_TO_UPDATE_BENEFIT));
        }

        this.savePaymentPort.save(payment).orElseThrow(() -> new ApplicationPaymentException(FAILED_TO_CREATE_PAYMENT));
        this.saveBalancePort.save(card.getBalance()).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_UPDATE_BALANCE));


        return payment;
    }
}
