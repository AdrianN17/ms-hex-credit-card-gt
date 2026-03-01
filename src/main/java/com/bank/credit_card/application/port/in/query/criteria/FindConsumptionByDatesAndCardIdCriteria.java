package com.bank.credit_card.application.port.in.query.criteria;

import java.time.LocalDate;

public record FindConsumptionByDatesAndCardIdCriteria(LocalDate start, LocalDate end, Long cardId) {

}
