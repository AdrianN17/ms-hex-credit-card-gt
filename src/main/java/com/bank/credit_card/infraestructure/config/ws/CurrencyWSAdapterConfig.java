package com.bank.credit_card.infraestructure.config.ws;

import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.infraestructure.ws.adapter.CurrencyWSAdapter;
import com.bank.credit_card.infraestructure.ws.mapper.MapperCurrency;
import com.bank.credit_card.infraestructure.ws.mapper.MapperCurrencyImpl;
import com.bank.credit_card.infraestructure.ws.repository.CurrencyJsonServerWSRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class CurrencyWSAdapterConfig {

    @Bean
    MapperCurrency mapperCurrency(){
        return new MapperCurrencyImpl();
    };

    @Bean
    CurrencyWSAdapter currencyWSAdapter(
            CurrencyJsonServerWSRepository currencyJsonServerWSRepository,
            MapperCurrency doctorMapper){
        return new CurrencyWSAdapter(
                currencyJsonServerWSRepository,
                doctorMapper);
    }

    @Bean
    CurrencyJsonServerWSRepository currencyJsonServerWSRepository(RestClient restClient){
        return new CurrencyJsonServerWSRepository(restClient);
    }
}
