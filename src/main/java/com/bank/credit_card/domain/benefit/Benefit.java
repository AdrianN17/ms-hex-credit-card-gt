package com.bank.credit_card.domain.benefit;

import com.bank.credit_card.domain.base.GenericDomain;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.benefit.vo.DiscountPolicy;
import com.bank.credit_card.domain.card.CategoryCardEnum;
import com.bank.credit_card.domain.card.vo.IdentifierId;
import com.bank.credit_card.domain.exception.DomainException;
import com.bank.credit_card.domain.payment.Payment;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.bank.credit_card.domain.benefit.BenefitConstant.*;
import static com.bank.credit_card.domain.benefit.BenefitErrorMessage.*;
import static com.bank.credit_card.domain.util.Validation.isConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Benefit extends GenericDomain {

    private Point totalPoints;
    private final DiscountPolicy discountPolicy;
    private final IdentifierId identifierId;
    //tabla

    private Benefit(Long id, Point totalPoints, DiscountPolicy discountPolicy, IdentifierId identifierId) throws DomainException {
        super(id);
        this.totalPoints = totalPoints;
        this.discountPolicy = discountPolicy;
        this.identifierId = identifierId;
    }

    public Point getTotalPoints() {
        return totalPoints;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    public IdentifierId getIdentifierId() {
        return identifierId;
    }

    public static Benefit create(Long id,
                                 Point totalPoints,
                                 Boolean hasDiscount,
                                 BigDecimal multiplierPoints,
                                 IdentifierId identifierId) {

        isNotNull(totalPoints, new BenefitException(POINT_NOT_NULL));
        isNotNull(hasDiscount, new BenefitException(HAS_DISCOUNT_NOT_NULL));
        isNotNull(identifierId, new BenefitException(IDENTIFIER_ID_NOT_NULL));

        if (hasDiscount)
            isNotNull(multiplierPoints, new BenefitException(MULTIPLIER_POINTS_NOT_NULL));

        return new Benefit(id, totalPoints, DiscountPolicy.create(hasDiscount, multiplierPoints), identifierId);
    }

    public void acumular(Amount amount, CategoryCardEnum categoryCard) {

        isNotNull(amount, new BenefitException(AMOUNT_NOT_NULL));
        isNotNull(categoryCard, new BenefitException(CATEGORY_NOT_NULL));

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

        this.totalPoints = getTotalPoints()
                .aumentarPuntos(Point.create(pointEarned));
    }

    public Payment descontar(Payment payment, Point puntosUsar) {
        isNotNull(payment, new BenefitException(PAYMENT_NOT_NULL));
        isNotNull(puntosUsar, new BenefitException(POINT_NOT_NULL));

        isConditional(getTotalPoints()
                .calcularSiNoTengoPuntosSuficientes(puntosUsar), new BenefitException(NOT_ENOUGH_POINTS));

        Point puntosCalculados = (getDiscountPolicy().getHasDiscount()) ? puntosUsar.multiplicar(getDiscountPolicy().getMultiplierPoints()) : puntosUsar;

        BigDecimal descuento = new BigDecimal(puntosCalculados.getPointEarned()).multiply(DISCOUNT_PER_POINT);

        this.totalPoints = getTotalPoints().disminuirPuntos(puntosCalculados);

        return payment.descontar(descuento);
    }

    public void cerrar() {
        softDelete();
    }
}
