package com.bank.credit_card.infraestructure.web.api.mapper;

import com.bank.credit_card.application.port.in.command.CardCancelPaymentCommand;
import com.bank.credit_card.application.port.in.command.CardProcessPaymentCommand;
import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.infraestructure.web.api.schema.request.PaymentRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.PaymentResponse;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperCommandId;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperResponse;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperRequestId;

import java.util.UUID;

public interface PaymentApiMapperCommand extends GenericMapperRequestId<CardProcessPaymentCommand, Long, PaymentRequest>,
        GenericMapperResponse<PaymentResponse, LoadPaymentView>,
        GenericMapperCommandId<CardCancelPaymentCommand, UUID> {
}
