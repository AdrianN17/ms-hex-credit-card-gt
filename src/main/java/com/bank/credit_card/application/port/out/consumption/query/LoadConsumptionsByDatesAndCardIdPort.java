package com.bank.credit_card.application.port.out.consumption.query;

import com.bank.credit_card.application.port.in.query.criteria.FindConsumptionByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;

import java.util.List;

@FunctionalInterface
public interface LoadConsumptionsByDatesAndCardIdPort {
    List<LoadConsumptionView> load(FindConsumptionByDatesAndCardIdCriteria criteria);
}
