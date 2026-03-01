package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.benefit.ApplicationBenefitException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.error.payment.ApplicationPaymentException;
import com.bank.credit_card.application.port.in.usecase.CardCancelPaymentUseCase;
import com.bank.credit_card.application.port.in.command.CardCancelPaymentCommand;
import com.bank.credit_card.application.port.in.query.view.LoadBalanceView;
import com.bank.credit_card.application.port.in.query.view.LoadBenefitView;
import com.bank.credit_card.application.port.in.query.view.LoadCardView;
import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.LoadBenefitPort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.payment.usecase.LoadPaymentPort;
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

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.BALANCE_NOT_FOUND;
import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_UPDATE_BALANCE;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.BENEFIT_NOT_FOUND;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.FAILED_TO_UPDATE_BENEFIT;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_NOT_FOUND;
import static com.bank.credit_card.application.error.consumption.ConsumptionApplicationErrorMessage.FAILED_TO_UPDATE_CONSUMPTION;
import static com.bank.credit_card.application.error.payment.PaymentApplicationErrorMessage.PAYMENT_NOT_FOUND;

public class CardCancelPaymentService implements CardCancelPaymentUseCase {

    private final LoadBalancePort loadBalancePort;
    private final LoadBenefitPort loadBenefitPort;
    private final LoadCardPort loadCardPort;
    private final LoadPaymentPort loadPaymentPort;
    private final SaveBenefitPort saveBenefitPort;
    private final SaveBalancePort saveBalancePort;
    private final SavePaymentPort savePaymentPort;

    public CardCancelPaymentService(LoadBalancePort loadBalancePort, LoadBenefitPort loadBenefitPort, LoadCardPort loadCardPort, LoadPaymentPort loadPaymentPort, SaveBenefitPort saveBenefitPort, SaveBalancePort saveBalancePort, SavePaymentPort savePaymentPort) {
        this.loadBalancePort = loadBalancePort;
        this.loadBenefitPort = loadBenefitPort;
        this.loadCardPort = loadCardPort;
        this.loadPaymentPort = loadPaymentPort;
        this.saveBenefitPort = saveBenefitPort;
        this.saveBalancePort = saveBalancePort;
        this.savePaymentPort = savePaymentPort;
    }

    @Override
    public void cancellPayment(CardCancelPaymentCommand cardCancelPaymentCommand) {
        LoadPaymentView loadPaymentView = loadPaymentPort
                .load(cardCancelPaymentCommand.paymentId())
                .orElseThrow(() -> new ApplicationPaymentException(PAYMENT_NOT_FOUND));
        LoadBalanceView balanceView = loadBalancePort
                .load(loadPaymentView.cardId())
                .orElseThrow(() -> new ApplicationBalanceException(BALANCE_NOT_FOUND));
        LoadBenefitView benefitView = loadBenefitPort
                .load(loadPaymentView.cardId())
                .orElseThrow(() -> new ApplicationBenefitException(BENEFIT_NOT_FOUND));
        LoadCardView loadCardView = loadCardPort
                .load(loadPaymentView.cardId())
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
                loadPaymentView.paymentId(),
                Amount.create(
                        Currency.create(
                                loadPaymentView.currency(),
                                BigDecimal.ONE),
                        loadPaymentView.amount()),
                loadPaymentView.paymentDate(),
                loadPaymentView.paymentApprobationDate(),
                loadPaymentView.category(),
                CardId.create(loadPaymentView.cardId()),
                loadPaymentView.channelPayment()
        );

        card.cancelPayment(payment);

        this.savePaymentPort.save(payment).orElseThrow(() -> new ApplicationPaymentException(FAILED_TO_UPDATE_CONSUMPTION));
        this.saveBalancePort.save(balance).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_UPDATE_BALANCE));
        this.saveBenefitPort.save(benefit).orElseThrow(() -> new ApplicationBenefitException(FAILED_TO_UPDATE_BENEFIT));
    }
}
