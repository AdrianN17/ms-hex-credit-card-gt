package com.bank.credit_card.domain.consumption;

import com.bank.credit_card.domain.base.constants.StatusEnum;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.generic.GenericDomain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.bank.credit_card.domain.base.constants.StatusEnum.ACTIVE;
import static com.bank.credit_card.domain.consumption.ConsumptionConstant.CONSUMPTION_SPLIT;
import static com.bank.credit_card.domain.consumption.ConsumptionErrorMessage.*;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;
import static java.util.Objects.isNull;

public class Consumption extends GenericDomain<UUID> {
    private final Amount consumptionAmount;
    private final LocalDateTime consumptionDate;
    private final LocalDateTime consumptionApprobationDate;
    private final CardId cardId;
    private final String sellerName;

    private Consumption(
            UUID id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Amount consumptionAmount,
            LocalDateTime consumptionDate,
            LocalDateTime consumptionApprobationDate,
            CardId cardId,
            String sellerName) {
        super(id, status, createdDate, updatedDate);
        this.consumptionAmount = consumptionAmount;
        this.consumptionDate = consumptionDate;
        this.consumptionApprobationDate = consumptionApprobationDate;
        this.cardId = cardId;
        this.sellerName = sellerName;
    }

    public static Consumption create(
            UUID id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Amount consumptionAmount,
            LocalDateTime consumptionDate,
            LocalDateTime consumptionApprobationDate,
            CardId cardId,
            String sellerName) {
        isNotNull(consumptionAmount, new ConsumptionException(CONSUMPTION_AMOUNT_CANNOT_BE_NULL));
        isNotNull(consumptionDate, new ConsumptionException(CONSUMPTION_DATE_CANNOT_BE_NULL));
        isNotNull(cardId, new ConsumptionException(CARD_ID_NOT_NULL));
        isNotNull(sellerName, new ConsumptionException(SELLER_NAME_CANNOT_BE_NULL));
        isNotConditional(sellerName.isEmpty(), new ConsumptionException(SELLER_NAME_CANNOT_BE_EMPTY));

        return new Consumption(
                id,
                status,
                createdDate,
                updatedDate,
                consumptionAmount,
                consumptionDate,
                consumptionApprobationDate,
                cardId,
                sellerName);
    }

    public static Consumption create(
            Amount consumptionAmount,
            LocalDateTime consumptionDate,
            CardId cardId,
            String sellerName) {

        isNotNull(consumptionAmount, new ConsumptionException(CONSUMPTION_AMOUNT_CANNOT_BE_NULL));
        isNotNull(consumptionDate, new ConsumptionException(CONSUMPTION_DATE_CANNOT_BE_NULL));
        isNotNull(cardId, new ConsumptionException(CARD_ID_NOT_NULL));
        isNotNull(sellerName, new ConsumptionException(SELLER_NAME_CANNOT_BE_NULL));
        isNotConditional(sellerName.isEmpty(), new ConsumptionException(SELLER_NAME_CANNOT_BE_EMPTY));

        return new Consumption(UUID.randomUUID(),
                ACTIVE,
                LocalDateTime.now(),
                null,
                consumptionAmount,
                consumptionDate,
                null,
                cardId,
                sellerName);
    }

    public static Consumption create(Amount consumptionAmount,
                                     CardId cardId,
                                     String sellerName) {
        isNotNull(consumptionAmount, new ConsumptionException(CONSUMPTION_AMOUNT_CANNOT_BE_NULL));
        isNotNull(cardId, new ConsumptionException(CARD_ID_NOT_NULL));
        isNotNull(sellerName, new ConsumptionException(SELLER_NAME_CANNOT_BE_NULL));
        isNotConditional(sellerName.isEmpty(), new ConsumptionException(SELLER_NAME_CANNOT_BE_EMPTY));

        return new Consumption(UUID.randomUUID(),
                ACTIVE,
                LocalDateTime.now(),
                null,
                consumptionAmount,
                LocalDateTime.now(),
                null,
                cardId,
                sellerName);
    }

    public Amount getConsumptionAmount() {
        return consumptionAmount;
    }

    public LocalDateTime getConsumptionDate() {
        return consumptionDate;
    }

    public CardId getCardId() {
        return cardId;
    }

    public LocalDateTime getConsumptionApprobationDate() {
        return consumptionApprobationDate;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getSplitSellerName(int count) {
        return getSellerName() + CONSUMPTION_SPLIT + " " + count;
    }

    public List<Consumption> split(Integer quantity,
                                   BigDecimal tax) {

        isNotNull(quantity, new ConsumptionException(QUANTITY_CANNOT_BE_NULL));
        isNotNull(tax, new ConsumptionException(TAX_AMOUNT_CANNOT_BE_NULL));

        Amount amount = this.getConsumptionAmount().fraccionar(quantity, tax);
        return IntStream.rangeClosed(1, quantity).mapToObj(value -> {
            var newDate = getConsumptionDate().plusMonths(value);

            return Consumption.create(amount, newDate, getCardId(), getSplitSellerName(value));
        }).toList();
    }

    public void validateIfConsumptionIsInApprobation() {
        isNotConditional(isNull(getConsumptionApprobationDate()),
                new ConsumptionException(CONSUMPTION_IS_STILL_IN_APPROBATION));
    }

    public void cancel() {
        softDelete();
    }
}
