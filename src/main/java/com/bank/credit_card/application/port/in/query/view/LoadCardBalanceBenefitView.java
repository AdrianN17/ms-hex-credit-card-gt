package com.bank.credit_card.application.port.in.query.view;

import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.CardStatusEnum;
import com.bank.credit_card.domain.card.CategoryCardEnum;
import com.bank.credit_card.domain.card.TypeCardEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoadCardBalanceBenefitView(
        Long cardId,
        TypeCardEnum typeCard,
        CategoryCardEnum categoryCard,
        BigDecimal creditTotal,
        CurrencyEnum currency,
        BigDecimal debtTax,
        CardStatusEnum cardStatus,
        Long cardAccountId,
        Long benefitId,
        Integer totalPoint,
        Boolean hasDiscount,
        BigDecimal multiplierPoints,
        Long balanceId,
        BigDecimal total,
        BigDecimal old,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal available) {
}
