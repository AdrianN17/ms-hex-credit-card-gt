package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BenefitEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.BenefitEntityVO;

public class BenefitPersistanceMapperImpl implements BenefitPersistanceMapper {

    @Override
    public Benefit toDomain(BenefitEntityVO benefitEntity) {
        return Benefit.builder()
                .benefitId(benefitEntity.getIdBenefit())
                .status(benefitEntity.getStatus())
                .createdDate(benefitEntity.getCreatedDate())
                .updatedDate(benefitEntity.getUpdatedDate())
                .totalPoints(benefitEntity.getTotalPoints())
                .discountPolicy(benefitEntity.getHasDiscount(), benefitEntity.getMultiplierPoints())
                .cardId(benefitEntity.getCard().getCardId())
                .build();
    }

    @Override
    public BenefitEntity toEntity(Benefit benefit) {
        return BenefitEntity.builder()
                .idBenefit(benefit.getId().getValue())
                .totalPoints(benefit.getTotalPoints().getPointEarned())
                .hasDiscount(benefit.getDiscountPolicy().getHasDiscount())
                .multiplierPoints(benefit.getDiscountPolicy().getMultiplierPoints())
                .createdDate(benefit.getCreatedDate())
                .updatedDate(benefit.getUpdatedDate())
                .status(benefit.getStatus())
                .cardId(benefit.getCardId().getValue())
                .build();
    }
}
