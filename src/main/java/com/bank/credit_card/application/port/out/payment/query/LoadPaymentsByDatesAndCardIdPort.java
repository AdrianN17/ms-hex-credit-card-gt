package com.bank.credit_card.application.port.out.payment.query;

import com.bank.credit_card.application.port.in.query.criteria.FindPaymentByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;

import java.util.List;

@FunctionalInterface
public interface LoadPaymentsByDatesAndCardIdPort {
    List<LoadPaymentView> load(FindPaymentByDatesAndCardIdCriteria criteria);
}
