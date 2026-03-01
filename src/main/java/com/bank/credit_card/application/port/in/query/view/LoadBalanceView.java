package com.bank.credit_card.application.port.in.query.view;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoadBalanceView(
        Long balanceId,
        BigDecimal total,
        BigDecimal old,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal available,
        Long cardId) {

}
