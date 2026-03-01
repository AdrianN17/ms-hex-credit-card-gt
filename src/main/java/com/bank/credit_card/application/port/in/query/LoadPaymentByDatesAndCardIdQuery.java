package com.bank.credit_card.application.port.in.query;

import com.bank.credit_card.application.port.in.query.criteria.FindPaymentByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;

import java.util.List;

@FunctionalInterface
public interface LoadPaymentByDatesAndCardIdQuery {
    List<LoadPaymentView> findByDatesAndCardId(FindPaymentByDatesAndCardIdCriteria criteria);
}
