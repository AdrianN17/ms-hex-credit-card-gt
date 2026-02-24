package com.bank.credit_card.domain.card;

import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.point.Point;
import com.bank.credit_card.domain.balance.vo.Amount;
import com.bank.credit_card.domain.balance.vo.Currency;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.GenericDomain;
import com.bank.credit_card.domain.card.vo.Credit;
import com.bank.credit_card.domain.consumption.ConsumptionException;
import com.bank.credit_card.domain.exception.DomainException;
import com.bank.credit_card.domain.payment.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.PaymentException;

import java.math.BigDecimal;
import java.util.Objects;

import static com.bank.credit_card.domain.card.CardConstant.OVERCHARGE_LIMIT;
import static com.bank.credit_card.domain.card.CardErrorMessage.*;
import static com.bank.credit_card.domain.card.CardStatusEnum.IN_DEBT;

public class Card extends GenericDomain {

    private TypeCardEnum typeCard;
    private CategoryCardEnum categoryCard;
    private Credit crediticialTotal;
    private CardStatusEnum cardStatus;
    private Balance balance;

    private Card(Long id,
                 TypeCardEnum typeCard,
                 CategoryCardEnum categoryCard,
                 Credit crediticialTotal,
                 CardStatusEnum cardStatus,
                 Balance balance) throws DomainException {
        super(id);
        this.typeCard = typeCard;
        this.categoryCard = categoryCard;
        this.crediticialTotal = crediticialTotal;
        this.cardStatus = cardStatus;
        this.balance = null;
    }

    private Card(TypeCardEnum typeCard,
                 CategoryCardEnum categoryCard,
                 Credit crediticialTotal,
                 CardStatusEnum cardStatus,
                 Balance balance) throws DomainException {
        super(null);
        this.typeCard = typeCard;
        this.categoryCard = categoryCard;
        this.crediticialTotal = crediticialTotal;
        this.cardStatus = cardStatus;
        this.balance = balance;
    }

    public static Card create(TypeCardEnum typeCard,
                              CategoryCardEnum categoryCard,
                              CurrencyEnum currency,
                              BigDecimal crediticialTotalAmount,
                              BigDecimal debtTax,
                              BigDecimal exchangeRate,
                              CardStatusEnum cardStatus,
                              Balance balance) {

        return new Card(
                typeCard,
                categoryCard,
                Credit.create(Amount.create(Currency.create(currency, exchangeRate), crediticialTotalAmount), debtTax),
                cardStatus,
                balance);
    }

    public static Card create(Long id,
                              TypeCardEnum typeCard,
                              CategoryCardEnum categoryCard,
                              CurrencyEnum currency,
                              BigDecimal crediticialTotalAmount,
                              BigDecimal debtTax,
                              BigDecimal exchangeRate,
                              CardStatusEnum cardStatus,
                              Balance balance) {

        return new Card(
                id,
                typeCard,
                categoryCard,
                Credit.create(Amount.create(Currency.create(currency, exchangeRate), crediticialTotalAmount), debtTax),
                cardStatus,
                balance);
    }

    public TypeCardEnum getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(TypeCardEnum typeCard) {
        this.typeCard = typeCard;
    }

    public CategoryCardEnum getCategoryCard() {
        return categoryCard;
    }

    public void setCategoryCard(CategoryCardEnum categoryCard) {
        this.categoryCard = categoryCard;
    }

    public Credit getCrediticialTotal() {
        return crediticialTotal;
    }

    public void setCrediticialTotal(Credit crediticialTotal) {
        this.crediticialTotal = crediticialTotal;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public CardStatusEnum getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatusEnum cardStatus) {
        this.cardStatus = cardStatus;
    }

    @Override
    public boolean valid() throws DomainException {
        return false;
    }

    public void validarBloqueo() throws DomainException {
        if (Objects.equals(getCardStatus(), IN_DEBT)) {
            throw new CardException(IN_DEBT_CARD);
        }
    }

    public void pagar(Amount amount, CategoryPaymentEnum category) {

        Amount totalDisponible = getBalance().getAvailable().mas(amount);
        Amount totalBalance = getBalance().getTotal();

        if (totalBalance.esIgual(totalDisponible) || !Objects.equals(category, CategoryPaymentEnum.TOTAL)) {
            throw new PaymentException(PAYMENT_CATEGORY_NOT_SAME_AS_PAYMENT);
        }

        if (totalDisponible.estaSobrando(totalBalance)) {
            Amount excedente = totalDisponible.menos(totalBalance);
            throw new PaymentException(PAYMENT_CATEGORY_EXCEED_LIKE + excedente.toString());
        }
        balance.setAvailable(balance.getAvailable().mas(amount));

        if (estaSobregirado(balance.getTotal(), balance.getAvailable())) {
            setCardStatus(CardStatusEnum.OVERCHARGE);
        } else {
            setCardStatus(CardStatusEnum.OPERATIVE);
        }
    }

    public Point consumir(Amount amount) {
        validarBloqueo();

        Amount totalDisponible = getBalance().getAvailable().menos(amount);

        if (totalDisponible.estaFaltando(getBalance().getTotal())) {
            throw new ConsumptionException(AMOUNT_EXCEED_CREDIT_LIMIT);
        }

        balance.setAvailable(balance.getAvailable().menos(amount));

        return Point.create(amount, getCategoryCard());
    }


    public Boolean estaSobregirado(Amount limiteCredito, Amount disponible) {
        BigDecimal limiteSobregiro = limiteCredito.getAmount().multiply(OVERCHARGE_LIMIT);
        BigDecimal limiteTotal = limiteCredito.getAmount().add(limiteSobregiro);

        return disponible.getAmount().compareTo(limiteTotal) > 0;
    }
}
