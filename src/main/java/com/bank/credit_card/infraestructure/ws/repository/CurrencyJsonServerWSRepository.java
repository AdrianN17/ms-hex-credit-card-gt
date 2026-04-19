package com.bank.credit_card.infraestructure.ws.repository;

import com.bank.credit_card.domain.base.enums.CurrencyEnum;
import com.bank.credit_card.infraestructure.ws.dto.CurrencyDto;
import com.bank.credit_card.infraestructure.ws.exception.ConverterWSClientException;
import org.springframework.web.client.RestClient;

public class CurrencyJsonServerWSRepository {

    private final RestClient restClient;

    public CurrencyJsonServerWSRepository(RestClient restClient) {
        this.restClient = restClient;
    }

    public CurrencyDto findByCurrency(CurrencyEnum currency) {
        try {
            return restClient
                    .get()
                    .uri("/" + currency.getCode())
                    .retrieve()
                    .body(CurrencyDto.class);
        } catch (Exception e) {
            throw new ConverterWSClientException(e);
        }
    }
}