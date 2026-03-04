package com.bank.credit_card.infraestructure.web.api.mapper;

import com.bank.credit_card.application.port.in.command.CardCancelConsumptionCommand;
import com.bank.credit_card.application.port.in.command.CardProcessConsumptionCommand;
import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.infraestructure.web.api.schema.request.ConsumptionRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.ConsumptionResponse;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperCommandId;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperResponse;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperRequestId;

import java.util.UUID;

public interface ConsumptionApiMapperCommand extends GenericMapperRequestId<CardProcessConsumptionCommand, Long, ConsumptionRequest>,
        GenericMapperResponse<ConsumptionResponse, LoadConsumptionView>,
        GenericMapperCommandId<CardCancelConsumptionCommand, UUID> {
}
