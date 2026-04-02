package com.bank.credit_card.domain.card;

import com.bank.credit_card.domain.base.StatusEnum;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.domain.benefit.Point;
import com.bank.credit_card.domain.card.vo.CardAccountId;
import com.bank.credit_card.domain.card.vo.Credit;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.domain.consumption.ConsumptionException;
import com.bank.credit_card.domain.generic.GenericDomain;
import com.bank.credit_card.domain.payment.Payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.bank.credit_card.domain.base.StatusEnum.ACTIVE;
import static com.bank.credit_card.domain.card.BalanceErrorMessage.*;
import static com.bank.credit_card.domain.card.CardErrorMessage.*;
import static com.bank.credit_card.domain.card.CardErrorMessage.ID_CANNOT_BE_NULL;
import static com.bank.credit_card.domain.card.CardStatusEnum.IN_DEBT;
import static com.bank.credit_card.domain.card.CardStatusEnum.OPERATIVE;
import static com.bank.credit_card.domain.card.CategoryPaymentEnum.ADELANTADO;
import static com.bank.credit_card.domain.card.vo.CardAccountIdErrorMessage.CARD_ACCOUNTID_CANNOT_BE_NULL;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Card extends GenericDomain<Long> {

    private final TypeCardEnum typeCard;
    private final CategoryCardEnum categoryCard;
    private final Credit credit;
    private CardStatusEnum cardStatus;
    private Balance balance;
    private Benefit benefit;
    private final CardAccountId cardAccountId;
    private final Short paymentDay;

    //agregate root
    private Card(
            Long id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            TypeCardEnum typeCard,
            CategoryCardEnum categoryCard,
            Credit credit,
            CardStatusEnum cardStatus,
            Balance balance,
            Benefit benefit,
            CardAccountId cardAccountId,
            Short paymentDay) {

        super(id, status, createdDate, updatedDate);
        this.typeCard = typeCard;
        this.categoryCard = categoryCard;
        this.credit = credit;
        this.cardStatus = cardStatus;
        this.balance = balance;
        this.benefit = benefit;
        this.cardAccountId = cardAccountId;
        this.paymentDay = paymentDay;
    }

    public static Card create(
            Long id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            TypeCardEnum typeCard,
            CategoryCardEnum categoryCard,
            Credit credit,
            CardStatusEnum cardStatus,
            Balance balance,
            Benefit benefit,
            CardAccountId cardAccountId,
            Short paymentDay) {

        isNotNull(typeCard, new CardException(TYPE_CARD_CANNOT_BE_NULL));
        isNotNull(categoryCard, new CardException(CATEGORY_CARD_CANNOT_BE_NULL));
        isNotNull(credit, new CardException(CREDIT_CANNOT_BE_NULL));
        isNotNull(cardStatus, new CardException(TYPE_CARD_CANNOT_BE_NULL));
        isNotNull(balance, new CardException(BALANCE_CANNOT_BE_NULL));
        isNotNull(benefit, new CardException(BENEFIT_CANNOT_BE_NULL));
        isNotNull(cardAccountId, new CardException(CARD_ACCOUNTID_CANNOT_BE_NULL));
        isNotNull(paymentDay, new CardException(PAYMENT_DAY_CANNOT_BE_NULL));

        return new Card(
                id,
                status,
                createdDate,
                updatedDate,
                typeCard,
                categoryCard,
                credit,
                cardStatus,
                balance,
                benefit,
                cardAccountId,
                paymentDay);
    }

    public static Card create(
            Long id,
            CardAccountId cardAccountId,
            TypeCardEnum typeCard,
            CategoryCardEnum categoryCard,
            Credit credit,
            Short paymentDay) {

        isNotNull(id, new CardException(ID_CANNOT_BE_NULL));
        isNotNull(cardAccountId, new CardException(CARD_ACCOUNTID_CANNOT_BE_NULL));
        isNotNull(typeCard, new CardException(TYPE_CARD_CANNOT_BE_NULL));
        isNotNull(categoryCard, new CardException(CATEGORY_CARD_CANNOT_BE_NULL));
        isNotNull(credit, new CardException(CREDIT_CANNOT_BE_NULL));
        isNotNull(paymentDay, new CardException(PAYMENT_DAY_CANNOT_BE_NULL));

        return new Card(
                id,
                ACTIVE,
                LocalDateTime.now(),
                null,
                typeCard,
                categoryCard,
                credit,
                OPERATIVE,
                null,
                null,
                cardAccountId,
                paymentDay);
    }

    public TypeCardEnum getTypeCard() {
        return typeCard;
    }

    public CategoryCardEnum getCategoryCard() {
        return categoryCard;
    }

    public Credit getCredit() {
        return credit;
    }

    public Balance getBalance() {
        return balance;
    }

    public CardStatusEnum getCardStatus() {
        return cardStatus;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public CardAccountId getCardAccountId() {
        return cardAccountId;
    }

    public Short getPaymentDay() {
        return paymentDay;
    }

    public void pay(Payment payment) {

        isNotNull(getBalance(), new CardException(BALANCE_CANNOT_BE_NULL));

        isNotNull(payment, new CardException(PAYMENT_CANNOT_BE_NULL));

        if (Objects.equals(payment.getCategory(), ADELANTADO))
            getBalance().prePay(payment);
        else
            getBalance().pay(payment);

        updateStatus();

    }

    public void pay(Payment payment, Point point) {

        isNotNull(getBalance(), new CardException(BALANCE_CANNOT_BE_NULL));
        isNotNull(getBenefit(), new CardException(BENEFIT_CANNOT_BE_NULL));

        isNotNull(payment, new CardException(PAYMENT_CANNOT_BE_NULL));
        isNotNull(point, new CardException(POINT_CANNOT_BE_NULL));

        isNotConditional(Objects.equals(payment.getCategory(), ADELANTADO), new CardException(POINTS_CANNOT_USED_WITH_PREPAY));

        getBalance().pay(getBenefit().discount(payment, point));

        updateStatus();
    }

    private void updateStatus() {
        if (getBalance().isOvercharged())
            this.cardStatus = CardStatusEnum.OVERCHARGE;
        else
            this.cardStatus = OPERATIVE;
    }

    public void consumption(Consumption consumption) {

        isNotNull(getBalance(), new CardException(BALANCE_CANNOT_BE_NULL));
        isNotNull(getBenefit(), new CardException(BENEFIT_CANNOT_BE_NULL));

        isNotNull(consumption, new CardException(CONSUMPTION_CANNOT_BE_NULL));

        isNotConditional(Objects.equals(getCardStatus(), IN_DEBT), new CardException(IN_DEBT_CARD));

        Amount totalAvailable = getBalance().calculateConsumption(consumption.getConsumptionAmount());

        isNotConditional(totalAvailable.estaFaltando(getBalance().getTotal()), new ConsumptionException(AMOUNT_EXCEED_CREDIT_LIMIT));

        getBalance().applyConsumption(consumption.getConsumptionAmount());

        getBenefit().accumulate(consumption.getConsumptionAmount(), getCategoryCard());
    }

    public List<Consumption> split(Consumption consumption, Integer quantity) {
        isNotNull(getBalance(), new CardException(BALANCE_CANNOT_BE_NULL));

        consumption.validateIfConsumptionIsInApprobation();
        List<Consumption> consumptionsSplit = consumption.split(quantity, credit.getDebtTax());
        getBalance().cancelConsumption(consumption);
        getBalance().consumptionSplitted(consumptionsSplit);
        return consumptionsSplit;
    }

    public void cancelConsumption(Consumption consumption) {

        isNotNull(getBalance(), new CardException(BALANCE_CANNOT_BE_NULL));

        isNotNull(consumption, new CardException(CONSUMPTION_CANNOT_BE_NULL));
        consumption.validateIfConsumptionIsInApprobation();
        getBalance().cancelConsumption(consumption);
    }

    public void cancelPayment(Payment payment) {

        isNotNull(getBalance(), new CardException(BALANCE_CANNOT_BE_NULL));

        isNotNull(payment, new CardException(PAYMENT_CANNOT_BE_NULL));
        payment.validateIfPaymentIsInApprobation();
        getBalance().cancelPayment(payment);
    }

    public void close() {
        softDelete();
        getBalance().close();
        getBenefit().close();
    }

}
