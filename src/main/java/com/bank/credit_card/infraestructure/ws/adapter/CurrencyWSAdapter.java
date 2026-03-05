package com.bank.credit_card.infraestructure.ws.adapter;

import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.infraestructure.ws.mapper.MapperCurrency;
import com.bank.credit_card.infraestructure.ws.repository.CurrencyJsonServerWSRepository;

import java.util.Optional;

public class CurrencyWSAdapter implements LoadCurrencyPort {

    private final CurrencyJsonServerWSRepository currencyJsonServerWSRepository;
    private final MapperCurrency mapperCurrency;

    public CurrencyWSAdapter(CurrencyJsonServerWSRepository currencyJsonServerWSRepository, MapperCurrency mapperCurrency) {
        this.currencyJsonServerWSRepository = currencyJsonServerWSRepository;
        this.mapperCurrency = mapperCurrency;
    }

    @Override
    public Optional<Currency> load(CurrencyEnum currencyEnum) {
        return Optional.of(currencyJsonServerWSRepository.findByCurrency(currencyEnum))
                .map(mapperCurrency::toDomain);
    }
}
