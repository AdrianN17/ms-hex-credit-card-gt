package com.bank.credit_card.domain.benefit.vo;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

public class DiscountPolicy {
    private final Boolean hasDiscount;
    private final BigDecimal multiplierPoints;

    private DiscountPolicy(Boolean hasDiscount, BigDecimal multiplierPoints) {
        this.hasDiscount = hasDiscount;
        this.multiplierPoints = multiplierPoints;
    }

    public static DiscountPolicy create(Boolean hasDiscount, BigDecimal multiplierPoints) {

        if (isNull(hasDiscount))
            hasDiscount = false;

        if (isNull(multiplierPoints))
            multiplierPoints = BigDecimal.ZERO;

        return new DiscountPolicy(hasDiscount, multiplierPoints);
    }

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public BigDecimal getMultiplierPoints() {
        return multiplierPoints;
    }
}
