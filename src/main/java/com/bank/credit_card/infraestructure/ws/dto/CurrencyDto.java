package com.bank.credit_card.infraestructure.ws.dto;

import java.math.BigDecimal;

public record CurrencyDto(BigDecimal value, String currency) {
}
