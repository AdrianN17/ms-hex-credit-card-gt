package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.base.vo.DateRange;
import com.bank.credit_card.domain.balance.old.Balance;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BalanceEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.BalanceEntityVO;

public class BalancePersistanceMapperImpl implements BalancePersistanceMapper {

    @Override
    public Balance toDomain(BalanceEntityVO balanceEntity, Currency currency) {
        return Balance.create(
                balanceEntity.getIdBalance(),
                balanceEntity.getStatus(),
                balanceEntity.getCreatedDate(),
                balanceEntity.getUpdatedDate(),
                Amount.create(currency, balanceEntity.getTotalAmount()),
                Amount.create(currency, balanceEntity.getOldAmount()),
                DateRange.create(balanceEntity.getStartDate(), balanceEntity.getEndDate()),
                Amount.create(currency, balanceEntity.getAvailableAmount()),
                CardId.create(balanceEntity.getCard().getCardId())
        );
    }

    @Override
    public BalanceEntity toEntity(Balance balance) {
        return BalanceEntity.builder()
                .idBalance(balance.getId())
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
