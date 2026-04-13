package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.benefit.ApplicationBenefitException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.port.in.command.CardCloseCommand;
import com.bank.credit_card.application.port.in.usecase.CardCloseUseCase;
import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.LoadBenefitPort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.card.usecase.SaveCardPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.domain.card.Card;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.BALANCE_NOT_FOUND;
import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_UPDATE_BALANCE;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.BENEFIT_NOT_FOUND;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.FAILED_TO_UPDATE_BENEFIT;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.*;

public class CardCloseService implements CardCloseUseCase {

    private final LoadCardPort loadCardPort;
    private final LoadBalancePort loadBalancePort;
    private final LoadBenefitPort loadBenefitPort;
    private final SaveBenefitPort saveBenefitPort;
    private final SaveBalancePort saveBalancePort;
    private final SaveCardPort saveCardPort;
    private final LoadCurrencyPort loadCurrencyPort;
    private final LoadCardCurrencyPort loadCardCurrencyPort;

    public CardCloseService(LoadCardPort loadCardPort, LoadBalancePort loadBalancePort, LoadBenefitPort loadBenefitPort, SaveBenefitPort saveBenefitPort, SaveBalancePort saveBalancePort, SaveCardPort saveCardPort, LoadCurrencyPort loadCurrencyPort, LoadCardCurrencyPort loadCardCurrencyPort) {
        this.loadCardPort = loadCardPort;
        this.loadBalancePort = loadBalancePort;
        this.loadBenefitPort = loadBenefitPort;
        this.saveBenefitPort = saveBenefitPort;
        this.saveBalancePort = saveBalancePort;
        this.saveCardPort = saveCardPort;
        this.loadCurrencyPort = loadCurrencyPort;
        this.loadCardCurrencyPort = loadCardCurrencyPort;
    }

    @Override
    public Long closeCard(CardCloseCommand cardCloseCommand) {

        CurrencyEnum cardCurrencyEnum = loadCardCurrencyPort.load(cardCloseCommand.cardId())
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        Currency cardCurrency = loadCurrencyPort.load(cardCurrencyEnum)
                .orElseThrow(() -> new ApplicationCardException(CARD_CURRENCY_NOT_FOUND));

        Card card = loadCardPort
                .load(cardCloseCommand.cardId(), cardCurrency)
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        Balance balance = loadBalancePort
                .load(cardCloseCommand.cardId(), cardCurrency)
                .orElseThrow(() -> new ApplicationBalanceException(BALANCE_NOT_FOUND));

        Benefit benefit = loadBenefitPort
                .load(cardCloseCommand.cardId())
                .orElseThrow(() -> new ApplicationBenefitException(BENEFIT_NOT_FOUND));

        card.close();
        balance.close();
        benefit.close();

        this.saveCardPort.save(card).orElseThrow(() -> new ApplicationCardException(FAILED_TO_UPDATE_CARD));
        this.saveBalancePort.save(balance).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_UPDATE_BALANCE));
        this.saveBenefitPort.save(benefit).orElseThrow(() -> new ApplicationBenefitException(FAILED_TO_UPDATE_BENEFIT));

        return card.getId();
    }
}
