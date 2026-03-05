package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadCardBalanceBenefitView;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.projection.CardSumaryProjection;

public class CardQueryMapperImpl implements CardQueryMapper {

    @Override
    public LoadCardBalanceBenefitView toView(CardSumaryProjection entity) {

        //validar que no sean nulos - programacion defensiva
        return new LoadCardBalanceBenefitView(
                entity.getTypeCardEnum(),
                entity.getCategoryCardEnum(),
                entity.getCreditTotal(),
                entity.getCurrencyEnum(),
                entity.getDebtTax(),
                entity.getCardStatusEnum(),
                entity.getPaymentDate(),
                entity.getTotalPoints(),
                entity.getHasDiscount(),
                entity.getMultiplierPoints(),
                entity.getTotalAmount(),
                entity.getOldAmount(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getAvailableAmount()

        );
    }
}
