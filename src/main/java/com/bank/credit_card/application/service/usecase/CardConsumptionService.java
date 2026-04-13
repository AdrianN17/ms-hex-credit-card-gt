package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.benefit.ApplicationBenefitException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.error.consumption.ApplicationConsumptionException;
import com.bank.credit_card.application.port.in.command.CardProcessConsumptionCommand;
import com.bank.credit_card.application.port.in.usecase.CardProcessConsumptionUseCase;
import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.LoadBenefitPort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.consumption.Consumption;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.BALANCE_NOT_FOUND;
import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_UPDATE_BALANCE;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.BENEFIT_NOT_FOUND;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.FAILED_TO_UPDATE_BENEFIT;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_CURRENCY_NOT_FOUND;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_NOT_FOUND;
import static com.bank.credit_card.application.error.consumption.ConsumptionApplicationErrorMessage.CONSUMPTION_CURRENCY_NOT_FOUND;
import static com.bank.credit_card.application.error.consumption.ConsumptionApplicationErrorMessage.FAILED_TO_CREATE_CONSUMPTION;

public class CardConsumptionService implements CardProcessConsumptionUseCase {


    private final LoadCardPort loadCardPort;
    private final LoadBalancePort loadBalancePort;
    private final LoadBenefitPort loadBenefitPort;
    private final SaveBenefitPort saveBenefitPort;
    private final SaveBalancePort saveBalancePort;
    private final SaveConsumptionPort saveConsumptionPort;
    private final LoadCurrencyPort loadCurrencyPort;
    private final LoadCardCurrencyPort loadCardCurrencyPort;

    public CardConsumptionService(LoadCardPort loadCardPort,
                                  LoadBalancePort loadBalancePort, LoadBenefitPort loadBenefitPort,
                                  SaveBenefitPort saveBenefitPort,
                                  SaveBalancePort saveBalancePort,
                                  SaveConsumptionPort saveConsumptionPort,
                                  LoadCurrencyPort loadCurrencyPort,
                                  LoadCardCurrencyPort loadCardCurrencyPort) {
        this.loadCardPort = loadCardPort;
        this.loadBalancePort = loadBalancePort;
        this.loadBenefitPort = loadBenefitPort;
        this.saveBenefitPort = saveBenefitPort;
        this.saveBalancePort = saveBalancePort;
        this.saveConsumptionPort = saveConsumptionPort;
        this.loadCurrencyPort = loadCurrencyPort;
        this.loadCardCurrencyPort = loadCardCurrencyPort;
    }

    @Override
    public Consumption processConsumption(CardProcessConsumptionCommand cardProcessConsumptionCommand) {

        CurrencyEnum cardCurrencyEnum = loadCardCurrencyPort.load(cardProcessConsumptionCommand.cardId())
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        Currency cardCurrency = loadCurrencyPort.load(cardCurrencyEnum)
                .orElseThrow(() -> new ApplicationCardException(CARD_CURRENCY_NOT_FOUND));

        Currency consumptionCurrency = loadCurrencyPort.load(cardProcessConsumptionCommand.currency())
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_CURRENCY_NOT_FOUND));

        Card card = loadCardPort
                .load(cardProcessConsumptionCommand.cardId(), cardCurrency)
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        Balance balance = loadBalancePort
                .load(cardProcessConsumptionCommand.cardId(), cardCurrency)
                .orElseThrow(() -> new ApplicationBalanceException(BALANCE_NOT_FOUND));

        Benefit benefit = loadBenefitPort
                .load(cardProcessConsumptionCommand.cardId())
                .orElseThrow(() -> new ApplicationBenefitException(BENEFIT_NOT_FOUND));

        Consumption consumption = Consumption.create(
                Amount.create(
                        consumptionCurrency,
                        cardProcessConsumptionCommand.amount()),
                CardId.create(cardProcessConsumptionCommand.cardId()),
                cardProcessConsumptionCommand.sellerName()
        );

        card.consumption(balance, benefit, consumption);

        this.saveConsumptionPort.save(consumption).orElseThrow(() -> new ApplicationConsumptionException(FAILED_TO_CREATE_CONSUMPTION));
        this.saveBalancePort.save(balance).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_UPDATE_BALANCE));
        this.saveBenefitPort.save(benefit).orElseThrow(() -> new ApplicationBenefitException(FAILED_TO_UPDATE_BENEFIT));
        return consumption;
    }
}
