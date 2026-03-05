package com.bank.credit_card.application.port.in.command;

import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.card.CategoryCardEnum;
import com.bank.credit_card.domain.card.TypeCardEnum;

import java.math.BigDecimal;

public record CardCreateCommand(TypeCardEnum typeCard,
                                CategoryCardEnum categoryCard,
                                BigDecimal creditTotal,
                                BigDecimal debtTax,
                                Boolean hasDiscount,
                                BigDecimal multiplierPoints,
                                Short paymentDay,
                                CurrencyEnum currency) {
}
