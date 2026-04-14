package com.bank.credit_card.application.port.in.query.view;

import com.bank.credit_card.domain.base.constants.CurrencyEnum;
import com.bank.credit_card.domain.card.CardStatusEnum;
import com.bank.credit_card.domain.card.CategoryCardEnum;
import com.bank.credit_card.domain.card.TypeCardEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoadCardBalanceBenefitView(
        TypeCardEnum typeCard,
        CategoryCardEnum categoryCard,
        BigDecimal creditTotal,
        CurrencyEnum currency,
        BigDecimal debtTax,
        CardStatusEnum cardStatus,
        Integer paymentDate,
        Integer totalPoint,
        Boolean hasDiscount,
        BigDecimal multiplierPoints,
        BigDecimal total,
        BigDecimal old,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal available) {
}
