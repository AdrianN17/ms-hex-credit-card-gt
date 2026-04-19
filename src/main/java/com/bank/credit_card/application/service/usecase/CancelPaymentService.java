package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.port.in.command.CardCancelPaymentCommand;
import com.bank.credit_card.application.port.in.usecase.CancelPaymentUseCase;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.application.service.usecase.business.BusinessServicePayment;
import com.bank.credit_card.domain.balance.BalancePago;
import com.bank.credit_card.domain.payment.vo.PaymentId;

public class CancelPaymentService implements CancelPaymentUseCase {

    private final BusinessServiceCard businessServiceCard;
    private final BusinessServiceBalance businessServiceBalance;
    private final BusinessServicePayment businessServicePayment;

    public CancelPaymentService(BusinessServiceCard businessServiceCard, BusinessServiceBalance businessServiceBalance, BusinessServicePayment businessServicePayment) {
        this.businessServiceCard = businessServiceCard;
        this.businessServiceBalance = businessServiceBalance;
        this.businessServicePayment = businessServicePayment;
    }

    @Override
    public PaymentId cancelPayment(CardCancelPaymentCommand cardCancelPaymentCommand) {

        var card = businessServiceCard.get(cardCancelPaymentCommand.cardId());
        var balance = BalancePago.from(businessServiceBalance.get(cardCancelPaymentCommand.cardId()));
        var payment = businessServicePayment.get(cardCancelPaymentCommand.cardId(), cardCancelPaymentCommand.paymentId());

        balance.cancel(payment.getPaymentAmount());
        card.updateStatus(balance.isOvercharged());
        payment.close();

        var id = businessServicePayment.save(payment);
        businessServiceBalance.save(balance);
        businessServiceCard.save(card);

        return id;
    }
}
