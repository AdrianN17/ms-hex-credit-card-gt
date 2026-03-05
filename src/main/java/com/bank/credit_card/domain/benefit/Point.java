package com.bank.credit_card.domain.benefit;

import com.bank.credit_card.domain.exception.DomainException;

import java.math.BigDecimal;

import static com.bank.credit_card.domain.benefit.PointConstant.POINT_EARNED_NOT_NULL;
import static com.bank.credit_card.domain.benefit.PointConstant.POINT_EARNED_POSITIVE;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Point {

    private final Integer pointEarned;

    private Point(Integer pointEarned) throws DomainException {
        this.pointEarned = pointEarned;
    }

    public static Point create(Integer pointEarned) {
        isNotNull(pointEarned, new PointException(POINT_EARNED_NOT_NULL));
        isNotConditional(pointEarned < 0, new PointException(POINT_EARNED_POSITIVE));

        return new Point(pointEarned);
    }

    public static Point create() {

        return new Point(0);
    }

    public Point dismissPoints(Point usedPoints) {
        return Point.create(getPointEarned() - usedPoints.getPointEarned());
    }

    public Point earnPoints(Point points) {
        return Point.create(getPointEarned() + points.getPointEarned());
    }

    public Boolean calculateIfHaveEnoughPoints(Point usedPoints) {
        return getPointEarned() < usedPoints.getPointEarned();
    }

    public Point mulitply(BigDecimal factor) {
        return Point.create(getPointEarned() * factor.intValue());
    }

    public Integer getPointEarned() {
        return pointEarned;
    }
}
