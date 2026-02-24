package com.bank.credit_card.domain.point;

import com.bank.credit_card.domain.balance.vo.Amount;
import com.bank.credit_card.domain.base.GenericDomain;
import com.bank.credit_card.domain.card.CategoryCardEnum;
import com.bank.credit_card.domain.exception.DomainException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.bank.credit_card.domain.point.PointConstant.*;
import static com.bank.credit_card.domain.point.PointErrorMessage.CATEOGRY_NOT_NULL;
import static java.util.Objects.isNull;

public class Point extends GenericDomain {

    private Integer pointEarned;

    public Point(Long id, Integer pointEarned) throws DomainException {
        super(id);
        this.pointEarned = pointEarned;
    }

    public Point(Integer pointEarned) {
        super(null);
        this.pointEarned = pointEarned;
    }

    public static Point create(Amount amount, CategoryCardEnum categoryCard) {


        if (isNull(categoryCard)) {
            throw new PointException(CATEOGRY_NOT_NULL);
        }

        BigDecimal ratio = switch (categoryCard) {
            case NORMAL -> RATIO_NORMAL;
            case SILVER -> RATIO_SILVER;
            case GOLD -> RATIO_GOLD;
            case PLATINUM -> RATIO_PLATINUM;
            case BLACK -> RATIO_BLACK;
            case SIGNATURE -> RATIO_SIGNATURE;
            case INFINITY -> RATIO_INFINITY;
        };

        Integer pointEarned = amount.getAmount().divide(ratio, RoundingMode.DOWN).intValue();

        return new Point(pointEarned);
    }

    @Override
    public boolean valid() throws DomainException {
        return false;
    }
}
