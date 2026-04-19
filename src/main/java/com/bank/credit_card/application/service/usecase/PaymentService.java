package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.payment.ApplicationPaymentException;
import com.bank.credit_card.application.port.in.command.CardProcessPaymentCommand;
import com.bank.credit_card.application.port.in.usecase.ProcessPaymentUseCase;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBenefit;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.application.service.usecase.business.BusinessServicePayment;
import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.balance.BalanceOperable;
import com.bank.credit_card.domain.balance.BalancePago;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.benefit.Point;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.payment.factory.PaymentFactory;
import com.bank.credit_card.domain.payment.vo.PaymentId;

import static com.bank.credit_card.application.error.payment.PaymentApplicationErrorMessage.PAYMENT_CURRENCY_NOT_FOUND;
import static java.util.Objects.isNull;

public class PaymentService implements ProcessPaymentUseCase {

    private final BusinessServiceCard businessServiceCard;
    private final BusinessServiceBalance businessServiceBalance;
    private final BusinessServiceBenefit businessServiceBenefit;
    private final BusinessServicePayment businessServicePayment;
    private final LoadCurrencyPort loadCurrencyPort;
    private final PaymentFactory paymentFactory;

    public PaymentService(BusinessServiceCard businessServiceCard, BusinessServiceBalance businessServiceBalance, BusinessServiceBenefit businessServiceBenefit, BusinessServicePayment businessServicePayment, LoadCurrencyPort loadCurrencyPort, PaymentFactory paymentFactory) {
        this.businessServiceCard = businessServiceCard;
        this.businessServiceBalance = businessServiceBalance;
        this.businessServiceBenefit = businessServiceBenefit;
        this.businessServicePayment = businessServicePayment;
        this.loadCurrencyPort = loadCurrencyPort;
        this.paymentFactory = paymentFactory;
    }

    @Override
    public PaymentId processPayment(CardProcessPaymentCommand cardProcessPaymentCommand) {

        var card = businessServiceCard.get(cardProcessPaymentCommand.cardId());
        var balance = BalancePago.from(businessServiceBalance.get(cardProcessPaymentCommand.cardId()));

        var paymentCurrency = loadCurrencyPort.load(cardProcessPaymentCommand.currency())
                .orElseThrow(() -> new ApplicationPaymentException(PAYMENT_CURRENCY_NOT_FOUND));

        var paymentAmount = Amount.create(
                paymentCurrency,
                cardProcessPaymentCommand.amount());

        if (!isNull(cardProcessPaymentCommand.pointsUsed()))
            return paymentProcessWithBenefit(cardProcessPaymentCommand, paymentAmount, card, balance);
        else
            return paymentProcessNoBenefit(cardProcessPaymentCommand, paymentAmount, card, balance);
    }

    private PaymentId paymentProcessWithBenefit(CardProcessPaymentCommand cardProcessPaymentCommand,
                                                Amount paymentAmount,
                                                Card card,
                                                BalanceOperable balance) {

        var benefit = businessServiceBenefit.get(cardProcessPaymentCommand.cardId());

        var point = Point.create(cardProcessPaymentCommand.pointsUsed());
        paymentAmount = benefit.discount(paymentAmount, point);

        var payment = paymentFactory.create(
                paymentAmount.getCurrency(),
                paymentAmount.getAmount(),
                cardProcessPaymentCommand.category(),
                cardProcessPaymentCommand.cardId(),
                cardProcessPaymentCommand.channelPayment());

        payment.validateIfPaymentIsPossible(balance.getAvailable(), balance.getTotal(), balance.getDateRange());
        balance.apply(paymentAmount);
        card.updateStatus(balance.isOvercharged());

        var id = businessServicePayment.save(payment);
        businessServiceBalance.save(balance);
        businessServiceCard.save(card);
        businessServiceBenefit.save(benefit);

        return id;
    }

    private PaymentId paymentProcessNoBenefit(CardProcessPaymentCommand cardProcessPaymentCommand,
                                              Amount paymentAmount,
                                              Card card,
                                              BalanceOperable balance) {

        var payment = paymentFactory.create(
                paymentAmount.getCurrency(),
                paymentAmount.getAmount(),
                cardProcessPaymentCommand.category(),
                cardProcessPaymentCommand.cardId(),
                cardProcessPaymentCommand.channelPayment());

        payment.validateIfPaymentIsPossible(balance.getAvailable(), balance.getTotal(), balance.getDateRange());
        balance.apply(paymentAmount);
        card.updateStatus(balance.isOvercharged());

        var id = businessServicePayment.save(payment);
        businessServiceBalance.save(balance);
        businessServiceCard.save(card);

        return id;
    }
}
