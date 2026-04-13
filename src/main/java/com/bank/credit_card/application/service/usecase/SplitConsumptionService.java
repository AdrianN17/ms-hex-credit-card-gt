package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.error.consumption.ApplicationConsumptionException;
import com.bank.credit_card.application.port.in.command.CardSplitConsumptionCommand;
import com.bank.credit_card.application.port.in.usecase.CardSplitConsumptionUseCase;
import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionCurrencyPort;
import com.bank.credit_card.application.port.out.consumption.usecase.LoadConsumptionPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.consumption.Consumption;

import java.util.List;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.BALANCE_NOT_FOUND;
import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_UPDATE_BALANCE;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_CURRENCY_NOT_FOUND;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_NOT_FOUND;
import static com.bank.credit_card.application.error.consumption.ConsumptionApplicationErrorMessage.*;

public class SplitConsumptionService implements CardSplitConsumptionUseCase {

    private final LoadCardPort loadCardPort;
    private final LoadBalancePort loadBalancePort;
    private final SaveBalancePort saveBalancePort;
    private final SaveConsumptionPort saveConsumptionPort;
    private final LoadConsumptionPort loadConsumptionPort;
    private final LoadCurrencyPort loadCurrencyPort;
    private final LoadCardCurrencyPort loadCardCurrencyPort;
    private final LoadConsumptionCurrencyPort loadConsumptionCurrencyPort;

    public SplitConsumptionService(LoadCardPort loadCardPort,
                                   LoadBalancePort loadBalancePort,
                                   SaveBalancePort saveBalancePort,
                                   SaveConsumptionPort saveConsumptionPort,
                                   LoadConsumptionPort loadConsumptionPort,
                                   LoadCurrencyPort loadCurrencyPort,
                                   LoadCardCurrencyPort loadCardCurrencyPort,
                                   LoadConsumptionCurrencyPort loadConsumptionCurrencyPort) {
        this.loadCardPort = loadCardPort;
        this.loadBalancePort = loadBalancePort;
        this.saveBalancePort = saveBalancePort;
        this.saveConsumptionPort = saveConsumptionPort;
        this.loadConsumptionPort = loadConsumptionPort;
        this.loadCurrencyPort = loadCurrencyPort;
        this.loadCardCurrencyPort = loadCardCurrencyPort;
        this.loadConsumptionCurrencyPort = loadConsumptionCurrencyPort;
    }

    @Override
    public List<Consumption> splitConsumption(CardSplitConsumptionCommand cardSplitConsumptionCommand) {

        CurrencyEnum cardCurrencyEnum = loadCardCurrencyPort.load(cardSplitConsumptionCommand.cardId())
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        CurrencyEnum consumptionCurrencyEnum = loadConsumptionCurrencyPort.load(cardSplitConsumptionCommand.consumptionId(), cardSplitConsumptionCommand.cardId().toString())
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_NOT_FOUND));

        Currency cardCurrency = loadCurrencyPort.load(cardCurrencyEnum)
                .orElseThrow(() -> new ApplicationCardException(CARD_CURRENCY_NOT_FOUND));

        Currency consumptionCurrency = loadCurrencyPort.load(consumptionCurrencyEnum)
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_CURRENCY_NOT_FOUND));

        Consumption consumption = loadConsumptionPort
                .load(cardSplitConsumptionCommand.consumptionId(), cardSplitConsumptionCommand.cardId().toString(), consumptionCurrency)
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_NOT_FOUND));
        Card card = loadCardPort
                .load(cardSplitConsumptionCommand.cardId(), cardCurrency)
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        Balance balance = loadBalancePort
                .load(cardSplitConsumptionCommand.cardId(), cardCurrency)
                .orElseThrow(() -> new ApplicationBalanceException(BALANCE_NOT_FOUND));

        List<Consumption> consumptions = card.split(balance, consumption, cardSplitConsumptionCommand.installments());

        consumptions.stream()
                .map(saveConsumptionPort::save)
                .forEach(consumptionOpt ->
                        consumptionOpt
                                .orElseThrow(() ->
                                        new ApplicationConsumptionException(FAILED_TO_UPDATE_CONSUMPTION)));

        this.saveConsumptionPort.save(consumption).orElseThrow(() -> new ApplicationConsumptionException(FAILED_TO_UPDATE_CONSUMPTION));
        this.saveBalancePort.save(balance).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_UPDATE_BALANCE));

        return consumptions;
    }
}
