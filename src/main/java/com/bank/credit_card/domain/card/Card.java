package com.bank.credit_card.domain.card;

import com.bank.credit_card.domain.base.GenericDomain;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.domain.benefit.Point;
import com.bank.credit_card.domain.card.vo.Credit;
import com.bank.credit_card.domain.card.vo.IdentifierId;
import com.bank.credit_card.domain.consumption.Consumption;
import com.bank.credit_card.domain.consumption.ConsumptionException;
import com.bank.credit_card.domain.exception.DomainException;
import com.bank.credit_card.domain.payment.Payment;

import java.util.List;
import java.util.Objects;

import static com.bank.credit_card.domain.card.BalanceErrorMessage.*;
import static com.bank.credit_card.domain.card.CardErrorMessage.*;
import static com.bank.credit_card.domain.card.CardErrorMessage.IDENTIFIER_ID_CANNOT_BE_NULL;
import static com.bank.credit_card.domain.card.CardStatusEnum.IN_DEBT;
import static com.bank.credit_card.domain.card.CategoryPaymentEnum.ADELANTADO;
import static com.bank.credit_card.domain.util.Validation.isConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Card extends GenericDomain<Long> {

    private final TypeCardEnum typeCard;
    private final IdentifierId identifierId;
    private final CategoryCardEnum categoryCard;
    private final Credit credit;
    private CardStatusEnum cardStatus;
    private Balance balance;
    private Benefit benefit;

    //agregate root
    private Card(Long id,
                 TypeCardEnum typeCard,
                 IdentifierId identifierId,
                 CategoryCardEnum categoryCard,
                 Credit credit,
                 CardStatusEnum cardStatus,
                 Balance balance,
                 Benefit benefit) throws DomainException {
        super(id);
        this.typeCard = typeCard;
        this.identifierId = identifierId;
        this.categoryCard = categoryCard;
        this.credit = credit;
        this.cardStatus = cardStatus;
        this.balance = balance;
        this.benefit = benefit;
    }

    public static Card create(Long id,
                              TypeCardEnum typeCard,
                              CategoryCardEnum categoryCard,
                              Credit credit,
                              CardStatusEnum cardStatus,
                              Balance balance,
                              Benefit benefit) {

        isNotNull(typeCard, new CardException(TYPE_CARD_CANNOT_BE_NULL));
        isNotNull(categoryCard, new CardException(CATEGORY_CARD_CANNOT_BE_NULL));
        isNotNull(credit, new CardException(CREDIT_CANNOT_BE_NULL));
        isNotNull(cardStatus, new CardException(TYPE_CARD_CANNOT_BE_NULL));
        isNotNull(balance, new CardException(BALANCE_CANNOT_BE_NULL));
        isNotNull(benefit, new CardException(BENEFIT_CANNOT_BE_NULL));

        return new Card(
                id,
                typeCard,
                IdentifierId.create(),
                categoryCard,
                credit,
                cardStatus,
                balance,
                benefit);
    }

    public static Card create(Long id,
                              TypeCardEnum typeCard,
                              IdentifierId identifierId,
                              CategoryCardEnum categoryCard,
                              Credit credit,
                              CardStatusEnum cardStatus,
                              Balance balance,
                              Benefit benefit) {

        isNotNull(typeCard, new CardException(TYPE_CARD_CANNOT_BE_NULL));
        isNotNull(identifierId, new CardException(IDENTIFIER_ID_CANNOT_BE_NULL));
        isNotNull(categoryCard, new CardException(CATEGORY_CARD_CANNOT_BE_NULL));
        isNotNull(credit, new CardException(CREDIT_CANNOT_BE_NULL));
        isNotNull(cardStatus, new CardException(TYPE_CARD_CANNOT_BE_NULL));
        isNotNull(balance, new CardException(BALANCE_CANNOT_BE_NULL));
        isNotNull(benefit, new CardException(BENEFIT_CANNOT_BE_NULL));

        return new Card(
                id,
                typeCard,
                identifierId,
                categoryCard,
                credit,
                cardStatus,
                balance,
                benefit);
    }

    public static Card create(TypeCardEnum typeCard,
                              IdentifierId identifierId,
                              CategoryCardEnum categoryCard,
                              Credit credit,
                              CardStatusEnum cardStatus,
                              Balance balance,
                              Benefit benefit) {

        isNotNull(typeCard, new CardException(TYPE_CARD_CANNOT_BE_NULL));
        isNotNull(identifierId, new CardException(IDENTIFIER_ID_CANNOT_BE_NULL));
        isNotNull(categoryCard, new CardException(CATEGORY_CARD_CANNOT_BE_NULL));
        isNotNull(credit, new CardException(CREDIT_CANNOT_BE_NULL));
        isNotNull(cardStatus, new CardException(TYPE_CARD_CANNOT_BE_NULL));
        isNotNull(balance, new CardException(BALANCE_CANNOT_BE_NULL));
        isNotNull(benefit, new CardException(BENEFIT_CANNOT_BE_NULL));

        return new Card(
                -1L,
                typeCard,
                identifierId,
                categoryCard,
                credit,
                cardStatus,
                balance,
                benefit);
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

    public IdentifierId getIdentifierId() {
        return identifierId;
    }

    public Balance pagar(Payment pago) {

        isNotNull(pago, new CardException(PAYMENT_CANNOT_BE_NULL));

        if (Objects.equals(pago.getCategory(), ADELANTADO))
            getBalance().pagoAdelantado(pago);
        else
            getBalance().pago(pago);

        actualizarEstado();

        if (Objects.equals(pago.getCategory(), ADELANTADO))
            return getBalance();
        else
            return getBalance().nuevo();
    }

    public Balance pagar(Payment pago, Point point) {

        isNotNull(pago, new CardException(PAYMENT_CANNOT_BE_NULL));
        isNotNull(point, new CardException(POINT_CANNOT_BE_NULL));

        isConditional(Objects.equals(pago.getCategory(), ADELANTADO), new CardException(POINTS_CANNOT_USED_WITH_PREPAY));

        getBalance().pago(benefit.descontar(pago, point));

        actualizarEstado();

        return getBalance().nuevo();
    }

    private void actualizarEstado() {
        if (getBalance().estaSobregirado())
            this.cardStatus = CardStatusEnum.OVERCHARGE;
        else
            this.cardStatus = CardStatusEnum.OPERATIVE;
    }

    public void consumir(Amount consumo) {

        isNotNull(consumo, new CardException(CONSUMPTION_CANNOT_BE_NULL));

        isConditional(Objects.equals(getCardStatus(), IN_DEBT), new CardException(IN_DEBT_CARD));

        Amount totalDisponible = getBalance().calcularConsumo(consumo);

        isConditional(totalDisponible.estaFaltando(getBalance().getTotal()), new ConsumptionException(AMOUNT_EXCEED_CREDIT_LIMIT));

        getBalance().aplicarConsumo(consumo);

        getBenefit().acumular(consumo, getCategoryCard());
    }

    public List<Consumption> fraccionar(Consumption consumption, Integer quantity) {
        return consumption.fraccionado(quantity, credit.getDebtTax());
    }

    public void anularConsumo(Consumption consumption) {
        isNotNull(consumption, new CardException(CONSUMPTION_CANNOT_BE_NULL));

        getBalance().anularConsumo(consumption);
    }

    public void anularPago(Payment pago) {
        isNotNull(pago, new CardException(PAYMENT_CANNOT_BE_NULL));

        getBalance().anularPago(pago);
    }

    public Card clonar() {
        return Card.create(
                getTypeCard(),
                getIdentifierId(),
                getCategoryCard(),
                getCredit(),
                getCardStatus(),
                getBalance(),
                getBenefit());
    }

    public void cerrar() {
        softDelete();
        getBalance().cerrar();
        getBenefit().cerrar();
    }

}
