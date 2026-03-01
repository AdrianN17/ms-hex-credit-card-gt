package com.bank.credit_card.application.port.in.query.view;

import java.math.BigDecimal;

public record LoadBenefitView(
        Long benefitId,
        Integer totalPoint,
        Boolean hasDiscount,
        BigDecimal multiplierPoints,
        Long cardId) {
}
