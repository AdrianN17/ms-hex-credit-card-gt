package com.bank.credit_card.application.service.usecase;

import com.bank.credit_card.application.error.balance.ApplicationBalanceException;
import com.bank.credit_card.application.error.benefit.ApplicationBenefitException;
import com.bank.credit_card.application.error.card.ApplicationCardException;
import com.bank.credit_card.application.error.consumption.ApplicationConsumptionException;
import com.bank.credit_card.application.port.in.usecase.CardCancelConsumptionUseCase;
import com.bank.credit_card.application.port.in.command.CardCancelConsumptionCommand;
import com.bank.credit_card.application.port.in.query.view.LoadBalanceView;
import com.bank.credit_card.application.port.in.query.view.LoadBenefitView;
import com.bank.credit_card.application.port.in.query.view.LoadCardView;
import com.bank.credit_card.application.port.in.query.view.LoadConsumptionView;
import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.application.port.out.benefit.LoadBenefitPort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.consumption.usecase.LoadConsumptionPort;
import com.bank.credit_card.application.port.out.consumption.usecase.SaveConsumptionPort;
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
import com.bank.credit_card.domain.consumption.Consumption;

import java.math.BigDecimal;

import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.BALANCE_NOT_FOUND;
import static com.bank.credit_card.application.error.balance.BalanceApplicationErrorMessage.FAILED_TO_UPDATE_BALANCE;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.BENEFIT_NOT_FOUND;
import static com.bank.credit_card.application.error.benefit.BenefitApplicationErrorMessage.FAILED_TO_UPDATE_BENEFIT;
import static com.bank.credit_card.application.error.card.CardApplicationErrorMessage.CARD_NOT_FOUND;
import static com.bank.credit_card.application.error.consumption.ConsumptionApplicationErrorMessage.CONSUMPTION_NOT_FOUND;
import static com.bank.credit_card.application.error.consumption.ConsumptionApplicationErrorMessage.FAILED_TO_UPDATE_CONSUMPTION;

public class CardCancelConsumptionService implements CardCancelConsumptionUseCase {

    private final LoadBalancePort loadBalancePort;
    private final LoadBenefitPort loadBenefitPort;
    private final LoadCardPort loadCardPort;
    private final LoadConsumptionPort loadConsumptionPort;
    private final SaveBenefitPort saveBenefitPort;
    private final SaveBalancePort saveBalancePort;
    private final SaveConsumptionPort saveConsumptionPort;

    public CardCancelConsumptionService(LoadBalancePort loadBalancePort, LoadBenefitPort loadBenefitPort, LoadCardPort loadCardPort, LoadConsumptionPort loadConsumptionPort, SaveBenefitPort saveBenefitPort, SaveBalancePort saveBalancePort, SaveConsumptionPort saveConsumptionPort) {
        this.loadBalancePort = loadBalancePort;
        this.loadBenefitPort = loadBenefitPort;
        this.loadCardPort = loadCardPort;
        this.loadConsumptionPort = loadConsumptionPort;
        this.saveBenefitPort = saveBenefitPort;
        this.saveBalancePort = saveBalancePort;
        this.saveConsumptionPort = saveConsumptionPort;
    }


    @Override
    public void cancelConsumption(CardCancelConsumptionCommand cardCancelConsumptionCommand) {
        LoadConsumptionView loadConsumptionView = loadConsumptionPort
                .load(cardCancelConsumptionCommand.consumptionId())
                .orElseThrow(() -> new ApplicationConsumptionException(CONSUMPTION_NOT_FOUND));
        LoadBalanceView balanceView = loadBalancePort
                .load(loadConsumptionView.cardId())
                .orElseThrow(() -> new ApplicationBalanceException(BALANCE_NOT_FOUND));
        LoadBenefitView benefitView = loadBenefitPort
                .load(loadConsumptionView.cardId())
                .orElseThrow(() -> new ApplicationBenefitException(BENEFIT_NOT_FOUND));
        LoadCardView loadCardView = loadCardPort
                .load(loadConsumptionView.cardId())
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

        Consumption consumption = Consumption.create(
                loadConsumptionView.consumptionId(),
                Amount.create(
                        Currency.create(
                                loadConsumptionView.currency(),
                                BigDecimal.ONE),
                        loadConsumptionView.amount()),
                loadConsumptionView.consumptionDate(),
                loadConsumptionView.consumptionApprobationDate(),
                CardId.create(loadConsumptionView.cardId()),
                loadConsumptionView.sellerName()
        );

        card.cancelConsumption(consumption);

        this.saveConsumptionPort.save(consumption).orElseThrow(() -> new ApplicationConsumptionException(FAILED_TO_UPDATE_CONSUMPTION));
        this.saveBalancePort.save(balance).orElseThrow(() -> new ApplicationBalanceException(FAILED_TO_UPDATE_BALANCE));
        this.saveBenefitPort.save(benefit).orElseThrow(() -> new ApplicationBenefitException(FAILED_TO_UPDATE_BENEFIT));
    }
}
