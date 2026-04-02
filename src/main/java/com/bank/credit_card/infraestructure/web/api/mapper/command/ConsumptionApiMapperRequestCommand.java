package com.bank.credit_card.infraestructure.web.api.mapper.command;

import com.bank.credit_card.application.port.in.command.CardCancelConsumptionCommand;
import com.bank.credit_card.application.port.in.command.CardProcessConsumptionCommand;
import com.bank.credit_card.application.port.in.command.CardSplitConsumptionCommand;
import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.infraestructure.web.api.schema.request.ConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.ConsumptionResponse;
import com.bank.credit_card.infraestructure.web.api.schema.response.ExchangeConsumptionRequestData;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperCommandIds;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperCommandIdsR;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperRequestId;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperResponse;

import java.util.UUID;

public interface ConsumptionApiMapperRequestCommand extends GenericMapperRequestId<CardProcessConsumptionCommand, Long, ConsumptionRequest>,
        GenericMapperResponse<ConsumptionResponse, LoadConsumptionView>,
        GenericMapperCommandIds<CardCancelConsumptionCommand, UUID, Long>,
        GenericMapperCommandIdsR<CardSplitConsumptionCommand, UUID, Long, ExchangeConsumptionRequestData> {
}
