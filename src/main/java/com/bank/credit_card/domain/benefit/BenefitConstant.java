package com.bank.credit_card.domain.benefit;

import java.math.BigDecimal;

public interface BenefitConstant {
    BigDecimal RATIO_NORMAL = BigDecimal.valueOf(10);
    BigDecimal RATIO_SILVER = BigDecimal.valueOf(8);
    BigDecimal RATIO_GOLD = BigDecimal.valueOf(6);
    BigDecimal RATIO_PLATINUM = BigDecimal.valueOf(4);
    BigDecimal RATIO_BLACK = BigDecimal.valueOf(3);
    BigDecimal RATIO_SIGNATURE = BigDecimal.valueOf(2);
    BigDecimal RATIO_INFINITY = BigDecimal.valueOf(1);

    BigDecimal DISCOUNT_PER_POINT = BigDecimal.valueOf(1);
}
