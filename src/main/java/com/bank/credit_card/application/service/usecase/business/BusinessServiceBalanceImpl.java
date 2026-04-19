package com.bank.credit_card.application.service.usecase.business;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.balance.BalanceId;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.BALANCE_NOT_FOUND;
import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_CREATE_BALANCE;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_CURRENCY_NOT_FOUND;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_NOT_FOUND;

public class BusinessServiceBalanceImpl implements BusinessServiceBalance {

    private final LoadBalancePort loadBalancePort;
    private final SaveBalancePort saveBalancePort;
    private final LoadCurrencyPort loadCurrencyPort;
    private final LoadCardCurrencyPort loadCardCurrencyPort;

    public BusinessServiceBalanceImpl(LoadBalancePort loadBalancePort, SaveBalancePort saveBalancePort, LoadCurrencyPort loadCurrencyPort, LoadCardCurrencyPort loadCardCurrencyPort) {
        this.loadBalancePort = loadBalancePort;
        this.saveBalancePort = saveBalancePort;
        this.loadCurrencyPort = loadCurrencyPort;
        this.loadCardCurrencyPort = loadCardCurrencyPort;
    }

    @Override
    public Balance get(Long cardId) {
        var cardCurrencyValue = loadCardCurrencyPort.load(cardId)
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        var cardCurrency = loadCurrencyPort.load(cardCurrencyValue)
                .orElseThrow(() -> new ApplicationCardException(CARD_CURRENCY_NOT_FOUND));

        return loadBalancePort
                .load(cardId, cardCurrency)
                .orElseThrow(() -> new ApplicationBalanceException(BALANCE_NOT_FOUND));
    }

    @Override
    public BalanceId save(Balance balance) {

        return saveBalancePort.save(balance)
                .map(BalanceId::create)
                .orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_CREATE_BALANCE));
    }
}
