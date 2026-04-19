package com.bank.credit_card.application.service.usecase.business;

import com.bank.credit_card.application.error.consumption.ApplicationConsumptionException;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionCurrencyPort;
import com.bank.credit_card.application.port.out.consumption.usecase.LoadConsumptionPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.domain.consumption.vo.ConsumptionId;

import java.util.UUID;

import static com.bank.credit_card.application.error.consumption.ConsumptionApplicationErrorMessage.*;

public class BusinessServiceConsumptionImpl implements BusinessServiceConsumption {

    private final LoadCurrencyPort loadCurrencyPort;
    private final LoadConsumptionPort loadConsumptionPort;
    private final SaveConsumptionPort saveConsumptionPort;
    private final LoadConsumptionCurrencyPort loadConsumptionCurrencyPort;

    public BusinessServiceConsumptionImpl(LoadCurrencyPort loadCurrencyPort, LoadConsumptionPort loadConsumptionPort, SaveConsumptionPort saveConsumptionPort, LoadConsumptionCurrencyPort loadConsumptionCurrencyPort) {
        this.loadCurrencyPort = loadCurrencyPort;
        this.loadConsumptionPort = loadConsumptionPort;
        this.saveConsumptionPort = saveConsumptionPort;
        this.loadConsumptionCurrencyPort = loadConsumptionCurrencyPort;
    }

    @Override
    public Consumption get(Long cardId, UUID consumptionId) {
        var consumptionCurrencyValue = loadConsumptionCurrencyPort
                .load(consumptionId, cardId.toString())
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_NOT_FOUND));

        var consumptionCurrency = loadCurrencyPort.load(consumptionCurrencyValue)
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_CURRENCY_NOT_FOUND));

        return loadConsumptionPort
                .load(consumptionId, cardId.toString(), consumptionCurrency)
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_NOT_FOUND));
    }

    @Override
    public ConsumptionId save(Consumption consumption) {
        return this.saveConsumptionPort.save(consumption)
                .map(ConsumptionId::create)
                .orElseThrow(() ->
                        new ApplicationConsumptionException(FAILED_TO_CREATE_CONSUMPTION));
    }
}
