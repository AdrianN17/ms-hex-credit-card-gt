package com.bank.credit_card.domain.benefit;

import com.bank.credit_card.domain.generic.GenericDomain;
import com.bank.credit_card.domain.base.StatusEnum;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.benefit.vo.DiscountPolicy;
import com.bank.credit_card.domain.card.CategoryCardEnum;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.generator.CardIdGenerator;
import com.bank.credit_card.domain.payment.Payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static com.bank.credit_card.domain.base.StatusEnum.ACTIVE;
import static com.bank.credit_card.domain.benefit.BenefitConstant.*;
import static com.bank.credit_card.domain.benefit.BenefitErrorMessage.*;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public class Benefit extends GenericDomain {

    private Point totalPoints;
    private final DiscountPolicy discountPolicy;
    private final CardId cardId;
    //tabla

    private Benefit(
            Long id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Point totalPoints,
            DiscountPolicy discountPolicy,
            CardId cardId) {
        super(id, status, createdDate, updatedDate);
        this.totalPoints = totalPoints;
        this.discountPolicy = discountPolicy;
        this.cardId = cardId;
    }

    public Point getTotalPoints() {
        return totalPoints;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    public CardId getCardId() {
        return cardId;
    }

    public static Benefit create(
            Long id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Point totalPoints,
            DiscountPolicy discountPolicy,
            CardId cardId) {

        isNotNull(totalPoints, new BenefitException(POINT_NOT_NULL));
        isNotNull(cardId, new BenefitException(CARD_ID_NOT_NULL));
        isNotNull(discountPolicy, new BenefitException(DISCUOUNT_POLICY_NOT_NULL));


        return new Benefit(id,
                status,
                createdDate,
                updatedDate,
                totalPoints,
                discountPolicy,
                cardId);
    }

    public static Benefit create(
            CardIdGenerator cardIdGenerator,
            DiscountPolicy discountPolicy,
            CardId cardId) {

        isNotNull(cardId, new BenefitException(CARD_ID_NOT_NULL));
        isNotNull(discountPolicy, new BenefitException(DISCUOUNT_POLICY_NOT_NULL));

        return new Benefit(
                cardIdGenerator.nextId(),
                ACTIVE,
                LocalDateTime.now(),
                null,
                Point.create(),
                discountPolicy,
                cardId);
    }

    public void accumulate(Amount amount,
                           CategoryCardEnum categoryCard) {

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
                .earnPoints(Point.create(pointEarned));
    }

    public Payment discount(Payment payment,
                            Point puntosUsar) {
        isNotNull(payment, new BenefitException(PAYMENT_NOT_NULL));
        isNotNull(puntosUsar, new BenefitException(POINT_NOT_NULL));

        isNotConditional(getTotalPoints()
                .calculateIfHaveEnoughPoints(puntosUsar), new BenefitException(NOT_ENOUGH_POINTS));

        Point calculatePoints = (getDiscountPolicy().getHasDiscount()) ?
                puntosUsar.mulitply(getDiscountPolicy().getMultiplierPoints()) :
                puntosUsar;

        BigDecimal discount = new BigDecimal(calculatePoints.getPointEarned()).multiply(DISCOUNT_PER_POINT);

        this.totalPoints = getTotalPoints().dismissPoints(calculatePoints);

        return payment.discount(discount);
    }

    public void close() {
        softDelete();
    }
}
