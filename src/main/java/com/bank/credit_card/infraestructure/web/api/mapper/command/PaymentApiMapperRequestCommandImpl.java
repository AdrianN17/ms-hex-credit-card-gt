package com.bank.credit_card.infraestructure.web.api.mapper.command;

import com.bank.credit_card.application.port.in.command.CardCancelPaymentCommand;
import com.bank.credit_card.application.port.in.command.CardProcessPaymentCommand;
import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.domain.base.enums.CurrencyEnum;
import com.bank.credit_card.domain.payment.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.ChannelPaymentEnum;
import com.bank.credit_card.infraestructure.web.api.schema.request.PaymentRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.PaymentResponse;

import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

import static com.bank.credit_card.infraestructure.web.api.contants.PaymentMapperCommandMessageConstants.PAYMENT_APPROBATION_DATE_NOT_NULL;
import static com.bank.credit_card.infraestructure.web.api.contants.PaymentMapperCommandMessageConstants.PAYMENT_DATE_NOT_NULL;

public class PaymentApiMapperRequestCommandImpl implements PaymentApiMapperRequestCommand {

    @Override
    public CardProcessPaymentCommand toCommand(PaymentRequest request, Long cardId) {
        return new CardProcessPaymentCommand(
                request.getAmount(),
                CurrencyEnum.valueOf(request.getCurrency()),
                CategoryPaymentEnum.valueOf(request.getCategory()),
                cardId,
                ChannelPaymentEnum.valueOf(request.getChannel()),
                request.getPointsUsed()
        );
    }

    @Override
    public CardCancelPaymentCommand toCommandId(UUID uuid, Long cardId) {
        return new CardCancelPaymentCommand(uuid, cardId);
    }

    @Override
    public PaymentResponse toResponse(LoadPaymentView view) {
        return new PaymentResponse(
                view.channelPayment().name(),
                view.currency().name(),
                view.amount(),
                view.category().name(),
                Objects.requireNonNull(view.paymentDate(), PAYMENT_DATE_NOT_NULL).atOffset(ZoneOffset.UTC),
                Objects.requireNonNull(view.paymentApprobationDate(), PAYMENT_APPROBATION_DATE_NOT_NULL).atOffset(ZoneOffset.UTC)
        );
    }
}
