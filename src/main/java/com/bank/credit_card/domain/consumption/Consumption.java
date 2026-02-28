package com.bank.credit_card.domain.consumption;

import com.bank.credit_card.domain.base.GenericDomain;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.card.vo.IdentifierId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.bank.credit_card.domain.consumption.ConsumptionErrorMessage.*;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Consumption extends GenericDomain<UUID> {
    private final Amount consumptionAmount;
    private final LocalDateTime consumptionDate;
    private final IdentifierId identifierId;

    private Consumption(UUID id, Amount consumptionAmount, LocalDateTime consumptionDate, IdentifierId identifierId) {
        super(id);
        this.consumptionAmount = consumptionAmount;
        this.consumptionDate = consumptionDate;
        this.identifierId = identifierId;
    }

    public static Consumption create(UUID id, Amount consumptionAmount, LocalDateTime consumptionDate, IdentifierId identifierId) {
        isNotNull(consumptionAmount, new ConsumptionException(CONSUMPTION_AMOUNT_CANNOT_BE_NULL));
        isNotNull(consumptionDate, new ConsumptionException(CONSUMPTION_DATE_CANNOT_BE_NULL));
        isNotNull(identifierId, new ConsumptionException(IDENTIFIER_ID_CANNOT_BE_NULL));

        return new Consumption(id, consumptionAmount, consumptionDate, identifierId);
    }

    public static Consumption create(Amount consumptionAmount, LocalDateTime consumptionDate, IdentifierId identifierId) {
        isNotNull(consumptionAmount, new ConsumptionException(CONSUMPTION_AMOUNT_CANNOT_BE_NULL));
        isNotNull(consumptionDate, new ConsumptionException(CONSUMPTION_DATE_CANNOT_BE_NULL));
        isNotNull(identifierId, new ConsumptionException(IDENTIFIER_ID_CANNOT_BE_NULL));

        return new Consumption(UUID.randomUUID(), consumptionAmount, consumptionDate, identifierId);
    }

    public Amount getConsumptionAmount() {
        return consumptionAmount;
    }

    public LocalDateTime getConsumptionDate() {
        return consumptionDate;
    }

    public IdentifierId getIdentifierId() {
        return identifierId;
    }

    public List<Consumption> split(Integer quantity, BigDecimal tax) {

        Amount amount = this.getConsumptionAmount().fraccionar(quantity, tax);
        return IntStream.rangeClosed(1, quantity).mapToObj(value -> {
            var newDate = getConsumptionDate().plusMonths(value);

            return Consumption.create(amount, newDate, getIdentifierId());
        }).toList();
    }

    public Consumption create(Amount amount, LocalDateTime newDate) {
        return Consumption.create(amount, newDate, this.identifierId);
    }
}
