package com.bank.credit_card.domain.card;

import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.base.constants.StatusEnum;
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

import static com.bank.credit_card.domain.balance.BalanceErrorMessage.*;
import static com.bank.credit_card.domain.base.constants.StatusEnum.ACTIVE;
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
            CardAccountId cardAccountId,
            Short paymentDay) {

        super(id, status, createdDate, updatedDate);
        this.typeCard = typeCard;
        this.categoryCard = categoryCard;
        this.credit = credit;
        this.cardStatus = cardStatus;
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
            CardAccountId cardAccountId,
            Short paymentDay) {

        isNotNull(typeCard, new CardException(TYPE_CARD_CANNOT_BE_NULL));
        isNotNull(categoryCard, new CardException(CATEGORY_CARD_CANNOT_BE_NULL));
        isNotNull(credit, new CardException(CREDIT_CANNOT_BE_NULL));
        isNotNull(cardStatus, new CardException(TYPE_CARD_CANNOT_BE_NULL));
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

    public CardStatusEnum getCardStatus() {
        return cardStatus;
    }

    public CardAccountId getCardAccountId() {
        return cardAccountId;
    }

    public Short getPaymentDay() {
        return paymentDay;
    }

    public void pay(Balance balance, Payment payment) {

        isNotNull(balance, new CardException(BALANCE_CANNOT_BE_NULL));

        isNotNull(payment, new CardException(PAYMENT_CANNOT_BE_NULL));

        if (Objects.equals(payment.getCategory(), ADELANTADO))
            balance.prePay(payment);
        else
            balance.pay(payment);

        updateStatus(balance);

    }

    public void pay(Balance balance, Benefit benefit, Payment payment, Point point) {

        isNotNull(balance, new CardException(BALANCE_CANNOT_BE_NULL));
        isNotNull(benefit, new CardException(BENEFIT_CANNOT_BE_NULL));

        isNotNull(payment, new CardException(PAYMENT_CANNOT_BE_NULL));
        isNotNull(point, new CardException(POINT_CANNOT_BE_NULL));

        isNotConditional(Objects.equals(payment.getCategory(), ADELANTADO), new CardException(POINTS_CANNOT_USED_WITH_PREPAY));

        balance.pay(benefit.discount(payment, point));

        updateStatus(balance);
    }

    private void updateStatus(Balance balance) {
        if (balance.isOvercharged())
            this.cardStatus = CardStatusEnum.OVERCHARGE;
        else
            this.cardStatus = OPERATIVE;
    }

    public void consumption(Balance balance, Benefit benefit, Consumption consumption) {

        isNotNull(balance, new CardException(BALANCE_CANNOT_BE_NULL));
        isNotNull(benefit, new CardException(BENEFIT_CANNOT_BE_NULL));

        isNotNull(consumption, new CardException(CONSUMPTION_CANNOT_BE_NULL));

        isNotConditional(Objects.equals(getCardStatus(), IN_DEBT), new CardException(IN_DEBT_CARD));

        Amount totalAvailable = balance.calculateConsumption(consumption.getConsumptionAmount());

        isNotConditional(totalAvailable.estaFaltando(balance.getTotal()), new ConsumptionException(AMOUNT_EXCEED_CREDIT_LIMIT));

        balance.applyConsumption(consumption.getConsumptionAmount());

        benefit.accumulate(consumption.getConsumptionAmount(), getCategoryCard());
    }

    public List<Consumption> split(Balance balance, Consumption consumption, Integer quantity) {
        isNotNull(balance, new CardException(BALANCE_CANNOT_BE_NULL));

        consumption.validateIfConsumptionIsInApprobation();
        List<Consumption> consumptionsSplit = consumption.split(quantity, credit.getDebtTax());
        balance.cancelConsumption(consumption);
        balance.consumptionSplitted(consumptionsSplit);
        return consumptionsSplit;
    }

    public void cancelConsumption(Balance balance, Consumption consumption) {

        isNotNull(balance, new CardException(BALANCE_CANNOT_BE_NULL));

        isNotNull(consumption, new CardException(CONSUMPTION_CANNOT_BE_NULL));
        consumption.validateIfConsumptionIsInApprobation();
        balance.cancelConsumption(consumption);
    }

    public void cancelPayment(Balance balance, Payment payment) {

        isNotNull(balance, new CardException(BALANCE_CANNOT_BE_NULL));

        isNotNull(payment, new CardException(PAYMENT_CANNOT_BE_NULL));
        payment.validateIfPaymentIsInApprobation();
        balance.cancelPayment(payment);
    }

    public void close() {
        softDelete();
    }

}
