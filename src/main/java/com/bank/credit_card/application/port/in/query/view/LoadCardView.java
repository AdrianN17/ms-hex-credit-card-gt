package com.bank.credit_card.application.port.in.query.view;

import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.card.CardStatusEnum;
import com.bank.credit_card.domain.card.CategoryCardEnum;
import com.bank.credit_card.domain.card.TypeCardEnum;

import java.math.BigDecimal;

public record LoadCardView(
        Long cardId,
        TypeCardEnum typeCard,
        CategoryCardEnum categoryCard,
        BigDecimal creditTotal,
        CurrencyEnum currency,
        BigDecimal debtTax,
        CardStatusEnum cardStatus,
        Long cardAccountId) {
}
