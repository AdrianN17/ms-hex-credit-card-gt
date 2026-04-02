package com.bank.credit_card.infraestructure.web.api.mapper.command;

import com.bank.credit_card.application.port.in.command.CardCloseCommand;
import com.bank.credit_card.application.port.in.command.CardCreateCommand;
import com.bank.credit_card.application.port.in.query.view.LoadCardBalanceBenefitView;
import com.bank.credit_card.infraestructure.web.api.schema.request.CardRequest;
import com.bank.credit_card.infraestructure.web.api.schema.response.CardResponse;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperCommandId;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperRequest;
import com.bank.credit_card.infraestructure.web.generic.mapper.GenericMapperResponse;

public interface CardApiMapperRequestCommand extends GenericMapperRequest<CardCreateCommand, CardRequest>,
        GenericMapperResponse<CardResponse, LoadCardBalanceBenefitView>,
        GenericMapperCommandId<CardCloseCommand, Long> {
}
