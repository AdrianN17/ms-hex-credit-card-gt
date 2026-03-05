package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.benefit.ApplicationBenefitException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.error.consumption.ApplicationConsumptionException;
import com.bank.credit_card.application.port.in.command.CardCancelConsumptionCommand;
import com.bank.credit_card.application.port.in.usecase.CardCancelConsumptionUseCase;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.consumption.query.LoadConsumptionCurrencyPort;
import com.bank.credit_card.application.port.out.consumption.usecase.LoadConsumptionPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.consumption.Consumption;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_UPDATE_BALANCE;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.FAILED_TO_UPDATE_BENEFIT;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_CURRENCY_NOT_FOUND;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_NOT_FOUND;
import static com.bank.credit_card.application.error.consumption.ConsumptionApplicationErrorMessage.*;

public class CardCancelConsumptionService implements CardCancelConsumptionUseCase {

    private final LoadCardPort loadCardPort;
    private final LoadConsumptionPort loadConsumptionPort;
    private final SaveBenefitPort saveBenefitPort;
    private final SaveBalancePort saveBalancePort;
    private final SaveConsumptionPort saveConsumptionPort;
    private final LoadCurrencyPort loadCurrencyPort;
    private final LoadCardCurrencyPort loadCardCurrencyPort;
    private final LoadConsumptionCurrencyPort loadConsumptionCurrencyPort;

    public CardCancelConsumptionService(LoadCardPort loadCardPort,
                                        LoadConsumptionPort loadConsumptionPort,
                                        SaveBenefitPort saveBenefitPort,
                                        SaveBalancePort saveBalancePort,
                                        SaveConsumptionPort saveConsumptionPort,
                                        LoadCurrencyPort loadCurrencyPort,
                                        LoadCardCurrencyPort loadCardCurrencyPort,
                                        LoadConsumptionCurrencyPort loadConsumptionCurrencyPort) {
        this.loadCardPort = loadCardPort;
        this.loadConsumptionPort = loadConsumptionPort;
        this.saveBenefitPort = saveBenefitPort;
        this.saveBalancePort = saveBalancePort;
        this.saveConsumptionPort = saveConsumptionPort;
        this.loadCurrencyPort = loadCurrencyPort;
        this.loadCardCurrencyPort = loadCardCurrencyPort;
        this.loadConsumptionCurrencyPort = loadConsumptionCurrencyPort;
    }

    @Override
    public void cancelConsumption(CardCancelConsumptionCommand cardCancelConsumptionCommand) {

        CurrencyEnum cardCurrencyEnum = loadCardCurrencyPort.load(cardCancelConsumptionCommand.cardId())
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        CurrencyEnum consumptionCurrencyEnum = loadConsumptionCurrencyPort.load(cardCancelConsumptionCommand.consumptionId())
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_NOT_FOUND));

        Currency cardCurrency = loadCurrencyPort.load(cardCurrencyEnum)
                .orElseThrow(() -> new ApplicationCardException(CARD_CURRENCY_NOT_FOUND));

        Currency consumptionCurrency = loadCurrencyPort.load(consumptionCurrencyEnum)
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_CURRENCY_NOT_FOUND));

        Consumption consumption = loadConsumptionPort
                .load(cardCancelConsumptionCommand.consumptionId(), consumptionCurrency)
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_NOT_FOUND));

        Card card = loadCardPort
                .load(cardCancelConsumptionCommand.cardId(), cardCurrency)
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        card.cancelConsumption(consumption);

        this.saveConsumptionPort.save(consumption).orElseThrow(() -> new ApplicationConsumptionException(FAILED_TO_UPDATE_CONSUMPTION));
        this.saveBalancePort.save(card.getBalance()).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_UPDATE_BALANCE));
        this.saveBenefitPort.save(card.getBenefit()).orElseThrow(() -> new ApplicationBenefitException(FAILED_TO_UPDATE_BENEFIT));
    }
}
