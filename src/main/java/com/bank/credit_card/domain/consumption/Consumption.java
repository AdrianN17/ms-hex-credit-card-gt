package com.bank.credit_card.domain.consumption;

import com.bank.credit_card.domain.base.enums.CurrencyEnum;
import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Approbation;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.consumption.vo.ConsumptionId;
import com.bank.credit_card.domain.consumption.vo.SellerName;
import com.bank.credit_card.domain.generic.GenericDomain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.bank.credit_card.domain.base.enums.StatusEnum.ACTIVE;
import static com.bank.credit_card.domain.consumption.ConsumptionConstant.CONSUMPTION_SPLIT;
import static com.bank.credit_card.domain.consumption.ConsumptionErrorMessage.*;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;
import static java.util.Objects.isNull;

public class Consumption extends GenericDomain<ConsumptionId> {
    private final Amount consumptionAmount;
    private final Approbation consumptionApprobation;
    private final CardId cardId;
    private final SellerName sellerName;

    private Consumption(ConsumptionBuilder builder) {
        super(builder.id, builder.status, builder.createdDate, builder.updatedDate);
        this.consumptionAmount = builder.consumptionAmount;
        this.consumptionApprobation = builder.consumptionApprobation;
        this.cardId = builder.cardId;
        this.sellerName = builder.sellerName;
    }

    public Amount getConsumptionAmount() {
        return consumptionAmount;
    }

    public Approbation getConsumptionApprobation() {
        return consumptionApprobation;
    }

    public CardId getCardId() {
        return cardId;
    }

    public SellerName getSellerName() {
        return sellerName;
    }

    public void close() {
        isNotConditional(isNull(getConsumptionApprobation().getApprobationDate()),
                new ConsumptionException(CONSUMPTION_IS_STILL_IN_APPROBATION));

        softDelete();
    }

    private String getSplitSellerName(int count) {
        return getSellerName().getValue() + CONSUMPTION_SPLIT + " " + count;
    }

    public List<Consumption> split(Integer quantity,
                                   BigDecimal tax) {

        isNotConditional(isNull(getConsumptionApprobation().getApprobationDate()),
                new ConsumptionException(CONSUMPTION_IS_STILL_IN_APPROBATION));

        isNotNull(quantity, new ConsumptionException(QUANTITY_CANNOT_BE_NULL));
        isNotNull(tax, new ConsumptionException(TAX_AMOUNT_CANNOT_BE_NULL));

        Amount amount = this.getConsumptionAmount().fraccionar(quantity, tax);
        return IntStream.rangeClosed(1, quantity).mapToObj(value -> {
            var newDate = getConsumptionApprobation().getDate().plusMonths(value);

            return Consumption.builder()
                    .consumptionAmount(amount)
                    .consumptionApprobation(newDate)
                    .cardId(getCardId())
                    .sellerName(getSplitSellerName(value))
                    .build();
        }).toList();
    }

    public static ConsumptionBuilder builder() {
        return new ConsumptionBuilder();
    }

    public static class ConsumptionBuilder {
        private ConsumptionId id;
        private StatusEnum status;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private Amount consumptionAmount;
        private Approbation consumptionApprobation;
        private CardId cardId;
        private SellerName sellerName;

        public ConsumptionBuilder id(UUID id) {
            this.id = ConsumptionId.create(id);
            return this;
        }

        public ConsumptionBuilder status(StatusEnum status) {
            this.status = status;
            return this;
        }

        public ConsumptionBuilder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public ConsumptionBuilder updatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public ConsumptionBuilder consumptionAmount(Amount consumptionAmount) {
            this.consumptionAmount = consumptionAmount;
            return this;
        }

        public ConsumptionBuilder consumptionAmount(BigDecimal amount, Currency currency) {
            isNotNull(amount, new ConsumptionException(CONSUMPTION_AMOUNT_CANNOT_BE_NULL));
            isNotNull(currency, new ConsumptionException(CONSUMPTION_CURRENCY_CANNOT_BE_NULL));
            this.consumptionAmount = Amount.create(currency, amount);
            return this;
        }

        public ConsumptionBuilder cardId(CardId cardId) {
            this.cardId = cardId;
            return this;
        }

        public ConsumptionBuilder cardId(Long cardId) {
            isNotNull(cardId, new ConsumptionException(CARD_ID_NOT_NULL));
            this.cardId = CardId.create(cardId);
            return this;
        }

        public ConsumptionBuilder consumptionApprobation(LocalDateTime date, LocalDateTime approbationDate) {
            this.consumptionApprobation = Approbation.create(date, approbationDate);
            return this;
        }

        public ConsumptionBuilder consumptionApprobation(LocalDateTime date) {
            this.consumptionApprobation = Approbation.create(date);
            return this;
        }

        public ConsumptionBuilder sellerName(String sellerName) {
            this.sellerName = SellerName.create(sellerName);
            return this;
        }

        public Consumption build() {
            if (this.id == null)
                this.id = ConsumptionId.create(UUID.randomUUID());
            if (this.status == null) this.status = ACTIVE;
            if (this.createdDate == null) this.createdDate = LocalDateTime.now();
            if (this.consumptionApprobation == null)
                this.consumptionApprobation = Approbation.create(LocalDateTime.now());

            isNotNull(consumptionAmount, new ConsumptionException(CONSUMPTION_AMOUNT_CANNOT_BE_NULL));
            isNotNull(cardId, new ConsumptionException(CARD_ID_NOT_NULL));
            isNotNull(sellerName, new ConsumptionException(SELLER_NAME_CANNOT_BE_NULL));
            isNotConditional(consumptionAmount.estaVacio(), new ConsumptionException(TAX_AMOUNT_CANNOT_BE_NULL));

            return new Consumption(this);
        }
    }
}
