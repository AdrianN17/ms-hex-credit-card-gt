package com.bank.credit_card.application.service.usecase.business;

import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.port.out.card.query.LoadCardCurrencyPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.card.usecase.SaveCardPort;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.card.vo.CardId;

import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.*;

public class BusinessServiceCardImpl implements BusinessServiceCard {

    private final LoadCardPort loadCardPort;
    private final SaveCardPort saveCardPort;
    private final LoadCurrencyPort loadCurrencyPort;
    private final LoadCardCurrencyPort loadCardCurrencyPort;

    public BusinessServiceCardImpl(LoadCardPort loadCardPort, SaveCardPort saveCardPort, LoadCurrencyPort loadCurrencyPort, LoadCardCurrencyPort loadCardCurrencyPort) {
        this.loadCardPort = loadCardPort;
        this.saveCardPort = saveCardPort;
        this.loadCurrencyPort = loadCurrencyPort;
        this.loadCardCurrencyPort = loadCardCurrencyPort;
    }

    @Override
    public Card get(Long cardId) {
        var cardCurrencyValue = loadCardCurrencyPort.load(cardId)
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        var cardCurrency = loadCurrencyPort.load(cardCurrencyValue)
                .orElseThrow(() -> new ApplicationCardException(CARD_CURRENCY_NOT_FOUND));

        return loadCardPort
                .load(cardId, cardCurrency)
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));
    }

    @Override
    public CardId save(Card card) {
        return saveCardPort.save(card)
                .map(CardId::create)
                .orElseThrow(() -> new ApplicationCardException(FAILED_TO_CREATE_CARD));
    }
}
