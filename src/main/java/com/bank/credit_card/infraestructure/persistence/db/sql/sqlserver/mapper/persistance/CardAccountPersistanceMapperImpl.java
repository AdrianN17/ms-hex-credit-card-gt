package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardAccountEntity;

public class CardAccountPersistanceMapperImpl implements CardAccountPersistanceMapper {


    @Override
    public CardAccountEntity toEntity(Card card) {
        return CardAccountEntity.builder()
                .cardAccountId(card.getCardAccountId().getValue())
                .cardStatus(card.getCardStatus())
                .currency(card.getCredit().getCreditTotal().getCurrency().getCurrency())
                .debtTax(card.getCredit().getDebtTax())
                .creditTotal(card.getCredit().getCreditTotal().getAmount())
                .paymentDate(card.getPaymentDay())
                .createdDate(card.getCreatedDate())
                .updatedDate(card.getUpdatedDate())
                .status(card.getStatus())
                .cardId(card.getId())
                .build();
    }
}
