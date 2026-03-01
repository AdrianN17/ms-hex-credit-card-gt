package com.bank.credit_card.application.port.in.query;

import com.bank.credit_card.application.port.in.query.criteria.FindConsumptionByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;

import java.util.List;

@FunctionalInterface
public interface LoadConsumptionByDatesAndCardIdQuery {
    List<LoadConsumptionView> findByDatesAndCardId(FindConsumptionByDatesAndCardIdCriteria criteria);
}
