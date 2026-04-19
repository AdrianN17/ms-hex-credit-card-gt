package com.bank.credit_card.application.service.usecase.business;

import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.domain.consumption.vo.ConsumptionId;

import java.util.UUID;

public interface BusinessServiceConsumption {
    Consumption get(Long cardId, UUID consumptionId);

    ConsumptionId save(Consumption consumption);
}
