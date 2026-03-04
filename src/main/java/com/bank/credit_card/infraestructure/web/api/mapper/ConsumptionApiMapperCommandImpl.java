package com.bank.credit_card.infraestructure.web.api.mapper;

import com.bank.credit_card.application.port.in.command.CardCancelConsumptionCommand;
import com.bank.credit_card.application.port.in.command.CardProcessConsumptionCommand;
import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.infraestructure.web.api.schema.request.ConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.ConsumptionResponse;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.util.UUID;

public class ConsumptionApiMapperCommandImpl implements ConsumptionApiMapperCommand {

    @Override
    public CardProcessConsumptionCommand toCommand(ConsumptionRequest request, Long cardId) {
        return new CardProcessConsumptionCommand(request.getAmount(),
                CurrencyEnum.valueOf(request.getCurrency()),
                cardId,
                request.getSellerName(),
                BigDecimal.ONE);
    }

    @Override
    public CardCancelConsumptionCommand toCommandId(UUID uuid, Long cardId) {
        return new CardCancelConsumptionCommand(uuid, cardId);
    }

    @Override
    public ConsumptionResponse toResponse(LoadConsumptionView view) {
        return new ConsumptionResponse(
                view.sellerName(),
                view.currency().name(),
                view.amount(),
                view.consumptionDate().atOffset(ZoneOffset.UTC),
                view.consumptionApprobationDate().atOffset(ZoneOffset.UTC)
        );
    }
}
