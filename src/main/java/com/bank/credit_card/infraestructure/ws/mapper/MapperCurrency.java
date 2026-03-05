package com.bank.credit_card.infraestructure.ws.mapper;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.infraestructure.ws.dto.CurrencyDto;

@FunctionalInterface
public interface MapperCurrency {

    Currency toDomain(CurrencyDto currencyDto);
}
