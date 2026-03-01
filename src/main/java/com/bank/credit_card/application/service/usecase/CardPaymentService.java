package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.benefit.ApplicationBenefitException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.error.payment.ApplicationPaymentException;
import com.bank.credit_card.application.port.in.usecase.CardProcessPaymentUseCase;
import com.bank.credit_card.application.port.in.command.CardProcessPaymentCommand;
import com.bank.credit_card.application.port.in.query.view.LoadBalanceView;
import com.bank.credit_card.application.port.in.query.view.LoadBenefitView;
import com.bank.credit_card.application.port.in.query.view.LoadCardView;
import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.LoadBenefitPort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.payment.usecase.SavePaymentPort;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.base.vo.DateRange;
import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.domain.benefit.Point;
import com.bank.credit_card.domain.benefit.vo.DiscountPolicy;
import com.bank.credit_card.domain.card.Balance;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.card.vo.CardAccountId;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.card.vo.Credit;
import com.bank.credit_card.domain.payment.Payment;

import java.math.BigDecimal;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.*;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.*;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_NOT_FOUND;
import static com.bank.credit_card.application.error.payment.PaymentApplicationErrorMessage.FAILED_TO_CREATE_PAYMENT;
import static java.util.Objects.isNull;

public class CardPaymentService implements CardProcessPaymentUseCase {

    private final LoadBalancePort loadBalancePort;
    private final LoadBenefitPort loadBenefitPort;
    private final LoadCardPort loadCardPort;
    private final SaveBenefitPort saveBenefitPort;
    private final SaveBalancePort saveBalancePort;
    private final SavePaymentPort savePaymentPort;

    public CardPaymentService(LoadBalancePort loadBalancePort, LoadBenefitPort loadBenefitPort, LoadCardPort loadCardPort, SaveBenefitPort saveBenefitPort, SaveBalancePort saveBalancePort, SavePaymentPort savePaymentPort) {
        this.loadBalancePort = loadBalancePort;
        this.loadBenefitPort = loadBenefitPort;
        this.loadCardPort = loadCardPort;
        this.saveBenefitPort = saveBenefitPort;
        this.saveBalancePort = saveBalancePort;
        this.savePaymentPort = savePaymentPort;
    }


    @Override
    public Payment processPayment(CardProcessPaymentCommand cardProcessPaymentCommand) {

        LoadBalanceView balanceView = loadBalancePort
                .load(cardProcessPaymentCommand.cardId())
                .orElseThrow(() -> new ApplicationBalanceException(BALANCE_NOT_FOUND));
        LoadBenefitView benefitView = loadBenefitPort
                .load(cardProcessPaymentCommand.cardId())
                .orElseThrow(() -> new ApplicationBenefitException(BENEFIT_NOT_FOUND));
        LoadCardView loadCardView = loadCardPort
                .load(cardProcessPaymentCommand.cardId())
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        Currency currency = Currency.create(loadCardView.currency(),
                BigDecimal.ONE);
        //corregir currency

        Balance balance = Balance.create(balanceView.balanceId(),
                Amount.create(currency,
                        balanceView.total()),
                Amount.create(currency,
                        balanceView.old()),
                DateRange.create(balanceView.startDate(),
                        balanceView.endDate()),
                Amount.create(currency,
                        balanceView.available()),
                CardId.create(balanceView.cardId()));


        Benefit benefit = Benefit.create(benefitView.benefitId(),
                Point.create(benefitView.totalPoint()),
                DiscountPolicy.create(benefitView.hasDiscount(),
                        benefitView.multiplierPoints()),
                CardId.create(benefitView.cardId()));

        Card card = Card.create(loadCardView.cardId(),
                loadCardView.typeCard(),
                loadCardView.categoryCard(),
                Credit.create(
                        Amount.create(
                                currency,
                                loadCardView.creditTotal()),
                        loadCardView.debtTax()),
                loadCardView.cardStatus(),
                balance, benefit,
                CardAccountId.create(loadCardView.cardAccountId()));

        Payment payment = Payment.create(
                Amount.create(
                        Currency.create(
                                cardProcessPaymentCommand.currency(),
                                BigDecimal.ONE),
                        cardProcessPaymentCommand.amount()),
                cardProcessPaymentCommand.paymentDate(),
                cardProcessPaymentCommand.category(),
                CardId.create(cardProcessPaymentCommand.cardId()),
                cardProcessPaymentCommand.channelPayment()
        );

        if (isNull(cardProcessPaymentCommand.pointsUsed())) {
            card.pay(payment);
        } else {
            Point point = Point.create(cardProcessPaymentCommand.pointsUsed());
            card.pay(payment, point);
            this.saveBenefitPort.save(benefit).orElseThrow(() -> new ApplicationBenefitException(FAILED_TO_UPDATE_BENEFIT));
        }

        this.savePaymentPort.save(payment).orElseThrow(() -> new ApplicationPaymentException(FAILED_TO_CREATE_PAYMENT));
        this.saveBalancePort.save(balance).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_UPDATE_BALANCE));


        return payment;
    }
}
