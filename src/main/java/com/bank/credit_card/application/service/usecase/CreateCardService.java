package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.benefit.ApplicationBenefitException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.port.in.usecase.CardCreateUseCase;
import com.bank.credit_card.application.port.in.command.CardCreateCommand;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.usecase.SaveCardPort;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.base.vo.DateRange;
import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.domain.benefit.vo.DiscountPolicy;
import com.bank.credit_card.domain.card.Balance;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.card.vo.Credit;

import java.math.BigDecimal;
import java.util.Optional;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_CREATE_BALANCE;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.FAILED_TO_CREATE_BENEFIT;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.FAILED_TO_CREATE_CARD;

public class CreateCardService implements CardCreateUseCase {

    private final SaveCardPort saveCardPort;
    private final SaveBalancePort saveBalancePort;
    private final SaveBenefitPort saveBenefitPort;

    public CreateCardService(SaveCardPort saveCardPort, SaveBalancePort saveBalancePort, SaveBenefitPort saveBenefitPort) {
        this.saveCardPort = saveCardPort;
        this.saveBalancePort = saveBalancePort;
        this.saveBenefitPort = saveBenefitPort;
    }

    @Override
    public Card createCard(CardCreateCommand cardCreateCommand) {

        Currency currency = Currency.create(cardCreateCommand.currency(),
                BigDecimal.ONE);

        //corregir currency

        Card card = Card.create(cardCreateCommand.typeCard(),
                cardCreateCommand.categoryCard(),
                Credit.create(
                        Amount.create(
                                currency,
                                cardCreateCommand.creditTotal()),
                        cardCreateCommand.debtTax()),
                cardCreateCommand.cardStatus());

        Optional<Long> cardIdOpt = this.saveCardPort.save(card);

        CardId cardId = cardIdOpt.map(CardId::create)
                .orElseThrow(() -> new ApplicationCardException(FAILED_TO_CREATE_CARD));

        Balance balance = Balance.create(
                Amount.create(
                        currency,
                        cardCreateCommand.total()),
                DateRange.create(cardCreateCommand.day()), cardId);

        Benefit benefit = Benefit.create(
                DiscountPolicy.create(cardCreateCommand.hasDiscount(),
                        cardCreateCommand.multiplierPoints()),
                cardId);

        this.saveBalancePort.save(balance).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_CREATE_BALANCE));
        this.saveBenefitPort.save(benefit).orElseThrow(() -> new ApplicationBenefitException(FAILED_TO_CREATE_BENEFIT));

        return card;
    }
}
