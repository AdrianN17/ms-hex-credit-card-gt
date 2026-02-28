package com.bank.credit_card.domain.benefit;

import com.bank.credit_card.domain.exception.DomainException;

import java.math.BigDecimal;

import static com.bank.credit_card.domain.benefit.PointConstant.POINT_EARNED_NOT_NULL;
import static com.bank.credit_card.domain.benefit.PointConstant.POINT_EARNED_POSITIVE;
import static com.bank.credit_card.domain.util.Validation.isConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Point {

    private final Integer pointEarned;

    private Point(Integer pointEarned) throws DomainException {
        this.pointEarned = pointEarned;
    }

    public static Point create(Integer pointEarned) {
        isNotNull(pointEarned, new PointException(POINT_EARNED_NOT_NULL));
        isConditional(pointEarned < 0, new PointException(POINT_EARNED_POSITIVE));

        return new Point(pointEarned);
    }

    public Point disminuirPuntos(Point puntosUsar) {
        return Point.create(getPointEarned() - puntosUsar.getPointEarned());
    }

    public Point aumentarPuntos(Point puntosSumar) {
        return Point.create(getPointEarned() + puntosSumar.getPointEarned());
    }

    public Boolean calcularSiNoTengoPuntosSuficientes(Point puntosUsar) {
        return getPointEarned() < puntosUsar.getPointEarned();
    }

    public Point multiplicar(BigDecimal multiplicador) {
        return Point.create(getPointEarned() * multiplicador.intValue());
    }

    public Integer getPointEarned() {
        return pointEarned;
    }
}
