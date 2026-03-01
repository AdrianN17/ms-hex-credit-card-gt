package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.benefit.ApplicationBenefitException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.port.in.usecase.CardCloseUseCase;
import com.bank.credit_card.application.port.in.command.CardCloseCommand;
import com.bank.credit_card.application.port.in.query.view.LoadBalanceView;
import com.bank.credit_card.application.port.in.query.view.LoadBenefitView;
import com.bank.credit_card.application.port.in.query.view.LoadCardView;
import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.LoadBenefitPort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.card.usecase.SaveCardPort;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.base.vo.DateRange;
import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.domain.benefit.Point;
import com.bank.credit_card.domain.benefit.vo.DiscountPolicy;
import com.bank.credit_card.domain.card.Balance;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.card.vo.CardAccountId;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.card.vo.Credit;

import java.math.BigDecimal;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.BALANCE_NOT_FOUND;
import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_UPDATE_BALANCE;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.BENEFIT_NOT_FOUND;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.FAILED_TO_UPDATE_BENEFIT;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_NOT_FOUND;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.FAILED_TO_UPDATE_CARD;

public class CardCloseService implements CardCloseUseCase {

    private final LoadBalancePort loadBalancePort;
    private final LoadBenefitPort loadBenefitPort;
    private final LoadCardPort loadCardPort;
    private final SaveBenefitPort saveBenefitPort;
    private final SaveBalancePort saveBalancePort;
    private final SaveCardPort saveCardPort;

    public CardCloseService(LoadBalancePort loadBalancePort, LoadBenefitPort loadBenefitPort, LoadCardPort loadCardPort, SaveBenefitPort saveBenefitPort, SaveBalancePort saveBalancePort, SaveCardPort saveCardPort) {
        this.loadBalancePort = loadBalancePort;
        this.loadBenefitPort = loadBenefitPort;
        this.loadCardPort = loadCardPort;
        this.saveBenefitPort = saveBenefitPort;
        this.saveBalancePort = saveBalancePort;
        this.saveCardPort = saveCardPort;
    }


    @Override
    public void closeCard(CardCloseCommand cardCloseCommand) {
        LoadBalanceView balanceView = loadBalancePort
                .load(cardCloseCommand.cardId())
                .orElseThrow(() -> new ApplicationBalanceException(BALANCE_NOT_FOUND));
        LoadBenefitView benefitView = loadBenefitPort
                .load(cardCloseCommand.cardId())
                .orElseThrow(() -> new ApplicationBenefitException(BENEFIT_NOT_FOUND));
        LoadCardView loadCardView = loadCardPort
                .load(cardCloseCommand.cardId())
                .orElseThrow(() -> new ApplicationCardException(CARD_NOT_FOUND));

        Currency currency = Currency.create(loadCardView.currency(),
                BigDecimal.ONE);
        //corregir currency

        Balance balance = Balance.create(balanceView.balanceId(),
                Amount.create(currency,
                        balanceView.total()),
                Amount.create(currency,
                        balanceView.old()),
                DateRange.create(balanceView.startDate(),
                        balanceView.endDate()),
                Amount.create(currency,
                        balanceView.available()),
                CardId.create(balanceView.cardId()));


        Benefit benefit = Benefit.create(benefitView.benefitId(),
                Point.create(benefitView.totalPoint()),
                DiscountPolicy.create(benefitView.hasDiscount(),
                        benefitView.multiplierPoints()),
                CardId.create(benefitView.cardId()));

        Card card = Card.create(loadCardView.cardId(),
                loadCardView.typeCard(),
                loadCardView.categoryCard(),
                Credit.create(
                        Amount.create(
                                currency,
                                loadCardView.creditTotal()),
                        loadCardView.debtTax()),
                loadCardView.cardStatus(),
                balance, benefit,
                CardAccountId.create(loadCardView.cardAccountId()));


        card.close();
        this.saveCardPort.save(card).orElseThrow(() -> new ApplicationCardException(FAILED_TO_UPDATE_CARD));
        this.saveBalancePort.save(balance).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_UPDATE_BALANCE));
        this.saveBenefitPort.save(benefit).orElseThrow(() -> new ApplicationBenefitException(FAILED_TO_UPDATE_BENEFIT));

    }
}
