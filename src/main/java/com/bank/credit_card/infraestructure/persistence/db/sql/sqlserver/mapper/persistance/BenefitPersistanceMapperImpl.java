package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.domain.benefit.Point;
import com.bank.credit_card.domain.benefit.vo.DiscountPolicy;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BenefitEntity;

public class BenefitPersistanceMapperImpl implements BenefitPersistanceMapper {

    @Override
    public Benefit toDomain(BenefitEntity benefitEntity) {
        return Benefit.create(
                benefitEntity.getIdBenefit(),
                Point.create(benefitEntity.getTotalPoints()),
                DiscountPolicy.create(benefitEntity.getHasDiscount(), benefitEntity.getMultiplierPoints()),
                CardId.create(benefitEntity.getCardId())
        );
    }

    @Override
    public BenefitEntity toEntity(Benefit benefit) {
        return BenefitEntity.builder()
                .totalPoints(benefit.getTotalPoints().getPointEarned())
                .hasDiscount(benefit.getDiscountPolicy().getHasDiscount())
                .multiplierPoints(benefit.getDiscountPolicy().getMultiplierPoints())
                .build();
    }
}
