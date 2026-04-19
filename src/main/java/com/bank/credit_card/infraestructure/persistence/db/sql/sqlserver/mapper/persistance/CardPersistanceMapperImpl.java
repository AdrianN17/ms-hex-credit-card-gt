package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.card.vo.CardAccountId;
import com.bank.credit_card.domain.card.vo.Credit;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.CardEntityVO;

public class CardPersistanceMapperImpl implements CardPersistanceMapper {

    @Override
    public CardEntity toEntity(Card card) {
        return CardEntity.builder()
                .cardId(card.getId().getValue())
                .typeCard(card.getTypeCard())
                .categoryCard(card.getCategoryCard())
                .createdDate(card.getCreatedDate())
                .updatedDate(card.getUpdatedDate())
                .status(card.getStatus())
                .build();
    }

    @Override
    public Card toDomain(CardEntityVO cardEntity, Currency currency) {
        return Card.builder()
                .cardId(cardEntity.getCardId())
                .status(cardEntity.getStatus())
                .createdDate(cardEntity.getCreatedDate())
                .updatedDate(cardEntity.getUpdatedDate())
                .typeCard(cardEntity.getTypeCard())
                .categoryCard(cardEntity.getCategoryCard())
                .credit(cardEntity.getCardAccount().getCreditTotal(), cardEntity.getCardAccount().getDebtTax(), currency)
                .cardStatus(cardEntity.getCardAccount().getCardStatus())
                .cardAccountId(cardEntity.getCardAccount().getCardAccountId())
                .paymentDay(cardEntity.getCardAccount().getPaymentDate())
                .build();
    }
}
