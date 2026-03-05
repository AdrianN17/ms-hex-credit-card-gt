package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.projection;

import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.card.CardStatusEnum;
import com.bank.credit_card.domain.card.CategoryCardEnum;
import com.bank.credit_card.domain.card.TypeCardEnum;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception.CardPersistanceException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception.CardErrorMessage.*;

public interface CardSumaryProjection {

    Integer getTypeCard();

    Integer getCategoryCard();

    BigDecimal getCreditTotal();

    BigDecimal getDebtTax();

    Integer getCurrency();

    Integer getPaymentDate();

    Integer getCardStatus();

    Boolean getHasDiscount();

    Integer getTotalPoints();

    BigDecimal getMultiplierPoints();

    BigDecimal getTotalAmount();

    BigDecimal getAvailableAmount();

    BigDecimal getOldAmount();

    LocalDate getStartDate();

    LocalDate getEndDate();

    default TypeCardEnum getTypeCardEnum() {
        return TypeCardEnum.ofValue(getTypeCard())
                .orElseThrow(() -> new CardPersistanceException(INCORRECT_TYPE_CARD_VALUE));
    }

    default CategoryCardEnum getCategoryCardEnum() {
        return CategoryCardEnum.ofValue(getCategoryCard())
                .orElseThrow(() -> new CardPersistanceException(INCORRECT_CATEGORY_VALUE));
    }

    default CurrencyEnum getCurrencyEnum() {
        return CurrencyEnum.ofValue(getCurrency())
                .orElseThrow(() -> new CardPersistanceException(INCORRECT_CURRENCY_VALUE));
    }

    default CardStatusEnum getCardStatusEnum() {
        return CardStatusEnum.ofValue(getCardStatus())
                .orElseThrow(() -> new CardPersistanceException(INCORRECT_CARD_STATUS_VALUE));
    }
}
