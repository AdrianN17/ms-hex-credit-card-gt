package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.balance.factory.BalanceFactory;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BalanceEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.BalanceEntityVO;

public class BalancePersistanceMapperImpl implements BalancePersistanceMapper {

    private final BalanceFactory balanceFactory;

    public BalancePersistanceMapperImpl(BalanceFactory balanceFactory) {
        this.balanceFactory = balanceFactory;
    }

    @Override
    public Balance toDomain(BalanceEntityVO balanceEntity, Currency currency) {
        return balanceFactory.create(
                balanceEntity.getIdBalance(),
                balanceEntity.getStatus(),
                balanceEntity.getCreatedDate(),
                balanceEntity.getUpdatedDate(),
                currency,
                balanceEntity.getCard().getCardId(),
                balanceEntity.getTotalAmount(),
                balanceEntity.getOldAmount(),
                balanceEntity.getAvailableAmount(),
                balanceEntity.getStartDate(),
                balanceEntity.getEndDate()
        );
    }

    @Override
    public BalanceEntity toEntity(Balance balance) {
        return BalanceEntity.builder()
                .idBalance(balance.getId().getValue())
                .totalAmount(balance.getTotal().getAmount())
                .oldAmount(balance.getOld().getAmount())
                .availableAmount(balance.getAvailable().getAmount())
                .startDate(balance.getDateRange().getStartDate())
                .endDate(balance.getDateRange().getEndDate())
                .createdDate(balance.getCreatedDate())
                .updatedDate(balance.getUpdatedDate())
                .status(balance.getStatus())
                .cardId(balance.getCardId().getValue())
                .build();
    }
}
