package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadCardBalanceBenefitView;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardEntity;

public class CardQueryMapperImpl implements CardQueryMapper {

    @Override
    public LoadCardBalanceBenefitView toView(CardEntity entity) {
        return new LoadCardBalanceBenefitView(
                entity.getTypeCard(),
                entity.getCategoryCard(),
                entity.getCardAccountEntity().getCreditTotal(),
                entity.getCardAccountEntity().getCurrency(),
                entity.getCardAccountEntity().getDebtTax(),
                entity.getCardAccountEntity().getCardStatus(),
                entity.getCardAccountEntity().getPaymentDate(),
                entity.getBenefitEntity().getTotalPoints(),
                entity.getBenefitEntity().getHasDiscount(),
                entity.getBenefitEntity().getMultiplierPoints(),
                entity.getBalanceEntity().getTotalAmount(),
                entity.getBalanceEntity().getOldAmount(),
                entity.getBalanceEntity().getStartDate(),
                entity.getBalanceEntity().getEndDate(),
                entity.getBalanceEntity().getAvailableAmount()

        );
    }
}
