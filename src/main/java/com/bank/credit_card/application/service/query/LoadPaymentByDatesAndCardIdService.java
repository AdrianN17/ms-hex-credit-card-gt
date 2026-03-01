package com.bank.credit_card.application.service.query;

import com.bank.credit_card.application.port.in.query.LoadPaymentByDatesAndCardIdQuery;
import com.bank.credit_card.application.port.in.query.criteria.FindPaymentByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.application.port.out.payment.query.LoadPaymentsByDatesAndCardIdPort;

import java.util.List;

public class LoadPaymentByDatesAndCardIdService implements LoadPaymentByDatesAndCardIdQuery {
    private final LoadPaymentsByDatesAndCardIdPort loadPaymentsByDatesAndCardIdPort;

    public LoadPaymentByDatesAndCardIdService(LoadPaymentsByDatesAndCardIdPort loadPaymentsByDatesAndCardIdPort) {
        this.loadPaymentsByDatesAndCardIdPort = loadPaymentsByDatesAndCardIdPort;
    }

    @Override
    public List<LoadPaymentView> findByDatesAndCardId(FindPaymentByDatesAndCardIdCriteria criteria) {
        return this.loadPaymentsByDatesAndCardIdPort.load(criteria);
    }
}
