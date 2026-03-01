package com.bank.credit_card.application.service.query;

import com.bank.credit_card.application.port.in.query.LoadConsumptionByDatesAndCardIdQuery;
import com.bank.credit_card.application.port.in.query.criteria.FindConsumptionByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionsByDatesAndCardIdPort;

import java.util.List;

public class LoadConsumptionByDatesAndCardIdService implements LoadConsumptionByDatesAndCardIdQuery {

    private final LoadConsumptionsByDatesAndCardIdPort loadConsumptionsByDatesAndCardIdPort;

    public LoadConsumptionByDatesAndCardIdService(LoadConsumptionsByDatesAndCardIdPort loadConsumptionsByDatesAndCardIdPort) {
        this.loadConsumptionsByDatesAndCardIdPort = loadConsumptionsByDatesAndCardIdPort;
    }

    @Override
    public List<LoadConsumptionView> findByDatesAndCardId(FindConsumptionByDatesAndCardIdCriteria criteria) {
        return loadConsumptionsByDatesAndCardIdPort.load(criteria);
    }
}
