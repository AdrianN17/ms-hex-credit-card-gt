package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.error.generator.ApplicationGeneratorException;
import com.bank.credit_card.application.port.in.command.CardCreateCommand;
import com.bank.credit_card.application.port.in.usecase.CardCreateUseCase;
import com.bank.credit_card.application.port.out.currency.LoadCurrencyPort;
import com.bank.credit_card.application.port.out.generator.LoadIdPort;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBalance;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceBenefit;
import com.bank.credit_card.application.service.usecase.business.BusinessServiceCard;
import com.bank.credit_card.domain.balance.factory.BalanceFactory;
import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.card.vo.CardId;

import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_CURRENCY_NOT_FOUND;
import static com.bank.credit_card.application.error.generator.GeneratorApplicationErrorMessage.*;
import static com.bank.credit_card.domain.balance.factory.BalanceType.CONSUMPTION;

public class CreateCardService implements CardCreateUseCase {

    private final BusinessServiceCard businessServiceCard;
    private final BusinessServiceBalance businessServiceBalance;
    private final BusinessServiceBenefit businessServiceBenefit;
    private final LoadIdPort loadIdPort;
    private final LoadCurrencyPort loadCurrencyPort;
    private final BalanceFactory balanceFactory;

    public CreateCardService(BusinessServiceCard businessServiceCard, BusinessServiceBalance businessServiceBalance, BusinessServiceBenefit businessServiceBenefit, LoadIdPort loadIdPort, LoadCurrencyPort loadCurrencyPort, BalanceFactory balanceFactory) {
        this.businessServiceCard = businessServiceCard;
        this.businessServiceBalance = businessServiceBalance;
        this.businessServiceBenefit = businessServiceBenefit;
        this.loadIdPort = loadIdPort;
        this.loadCurrencyPort = loadCurrencyPort;
        this.balanceFactory = balanceFactory;
    }

    @Override
    public CardId createCard(CardCreateCommand cardCreateCommand) {

        Long idCard = loadIdPort.load().orElseThrow(() -> new ApplicationGeneratorException(CARD_ID_GENERATION_ERROR));
        Long idCardAccount = loadIdPort.load().orElseThrow(() -> new ApplicationGeneratorException(CARD_ACCOUNT_ID_GENERATION_ERROR));
        Long idBenefit = loadIdPort.load().orElseThrow(() -> new ApplicationGeneratorException(BENEFIT_ID_GENERATION_ERROR));
        Long idBalance = loadIdPort.load().orElseThrow(() -> new ApplicationGeneratorException(BALANCE_ID_GENERATION_ERROR));

        var currencyResponse = loadCurrencyPort.load(cardCreateCommand.currency())
                .orElseThrow(() -> new ApplicationCardException(CARD_CURRENCY_NOT_FOUND));

        var card = Card.builder()
                .cardId(idCard)
                .typeCard(cardCreateCommand.typeCard())
                .categoryCard(cardCreateCommand.categoryCard())
                .cardAccountId(idCardAccount)
                .paymentDay(cardCreateCommand.paymentDay())
                .credit(cardCreateCommand.creditTotal(),
                        cardCreateCommand.debtTax(),
                        currencyResponse)
                .build();

        var balance = balanceFactory.create(idBalance,
                currencyResponse,
                idCard,
                cardCreateCommand.creditTotal(),
                cardCreateCommand.paymentDay(),
                CONSUMPTION);

        var benefit = Benefit.builder()
                .benefitId(idBenefit)
                .cardId(idCard)
                .discountPolicy(cardCreateCommand.hasDiscount(), cardCreateCommand.multiplierPoints())
                .build();

        var id = businessServiceCard.save(card);
        businessServiceBalance.save(balance);
        businessServiceBenefit.save(benefit);

        return id;
    }
}
