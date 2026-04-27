package com.bank.credit_card.infraestructure.web.api.mapper.command;

import com.bank.credit_card.application.port.in.command.CardCancelConsumptionCommand;
import com.bank.credit_card.application.port.in.command.CardProcessConsumptionCommand;
import com.bank.credit_card.application.port.in.command.CardSplitConsumptionCommand;
import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.domain.base.enums.CurrencyEnum;
import com.bank.credit_card.infraestructure.web.api.schema.request.ConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.request.ExchangeConsumptionRequestData;
import com.bank.credit_card.infraestructure.web.api.schema.response.ConsumptionResponse;

import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

import static com.bank.credit_card.infraestructure.web.api.contants.ConsumptionMapperCommandMessageConstants.CONSUMPTION_APPROBATION_DATE_NOT_NULL;
import static com.bank.credit_card.infraestructure.web.api.contants.ConsumptionMapperCommandMessageConstants.CONSUMPTION_DATE_NOT_NULL;

public class ConsumptionApiMapperRequestCommandImpl implements ConsumptionApiMapperRequestCommand {

    @Override
    public CardProcessConsumptionCommand toCommand(ConsumptionRequest request, Long cardId) {
        return new CardProcessConsumptionCommand(request.getAmount(),
                CurrencyEnum.valueOf(request.getCurrency()),
                cardId,
                request.getSellerName());
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
                Objects.requireNonNull(view.consumptionDate(), CONSUMPTION_DATE_NOT_NULL).atOffset(ZoneOffset.UTC),
                Objects.requireNonNull(view.consumptionApprobationDate(), CONSUMPTION_APPROBATION_DATE_NOT_NULL).atOffset(ZoneOffset.UTC)
        );
    }


    @Override
    public CardSplitConsumptionCommand toCommandIdR(UUID uuid, Long subId, ExchangeConsumptionRequestData re) {
        return new CardSplitConsumptionCommand(
                uuid,
                re.getInstallments(),
                subId
        );
    }
}
