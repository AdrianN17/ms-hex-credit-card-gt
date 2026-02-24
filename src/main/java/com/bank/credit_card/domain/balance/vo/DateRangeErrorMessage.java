package com.bank.credit_card.domain.balance.vo;

public interface DateRangeErrorMessage {
    String START_DATE_AFTER_END_DATE = "La fecha de inicio no puede ser posterior a la fecha de fin.";
    String END_DATE_BEFORE_START_DATE = "La fecha de fin no puede ser anterior a la fecha de inicio.";
    String DATE_NOT_WITHIN_RANGE = "La fecha no está dentro del rango especificado.";
}
