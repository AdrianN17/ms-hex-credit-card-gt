package com.bank.credit_card.infraestructure.ws.mapper;

import com.bank.credit_card.domain.base.constants.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.infraestructure.ws.dto.CurrencyDto;

public class MapperCurrencyImpl implements MapperCurrency {

    @Override
    public Currency toDomain(CurrencyDto currencyDto) {
        return Currency.create(CurrencyEnum.valueOf(currencyDto.currency()),
                currencyDto.value());
    }

}
