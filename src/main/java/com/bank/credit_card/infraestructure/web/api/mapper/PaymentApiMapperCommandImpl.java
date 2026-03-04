package com.bank.credit_card.infraestructure.web.api.mapper;

import com.bank.credit_card.application.port.in.command.CardCancelPaymentCommand;
import com.bank.credit_card.application.port.in.command.CardProcessPaymentCommand;
import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.card.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.ChannelPaymentEnum;
import com.bank.credit_card.infraestructure.web.api.schema.request.PaymentRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.PaymentResponse;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.util.UUID;

public class PaymentApiMapperCommandImpl implements PaymentApiMapperCommand {

    @Override
    public CardProcessPaymentCommand toCommand(PaymentRequest request, Long cardId) {
        return new CardProcessPaymentCommand(
                request.getAmount(),
                CurrencyEnum.valueOf(request.getCurrency()),
                CategoryPaymentEnum.valueOf(request.getCategory()),
                cardId,
                ChannelPaymentEnum.valueOf(request.getChannel()),
                request.getPointsUsed(),
                BigDecimal.ONE
        );
    }

    @Override
    public CardCancelPaymentCommand toCommandId(UUID uuid) {
        return new CardCancelPaymentCommand(uuid, 1L);
    }

    @Override
    public PaymentResponse toResponse(LoadPaymentView view) {
        return new PaymentResponse(
                view.channelPayment().name(),
                view.currency().name(),
                view.amount(),
                view.category().name(),
                view.paymentDate().atOffset(ZoneOffset.UTC),
                view.paymentApprobationDate().atOffset(ZoneOffset.UTC)
        );
    }
}
