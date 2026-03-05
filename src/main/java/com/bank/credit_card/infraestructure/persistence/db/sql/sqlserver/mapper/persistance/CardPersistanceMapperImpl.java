package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.card.vo.CardAccountId;
import com.bank.credit_card.domain.card.vo.Credit;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardAccountEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.CardEntityVO;

import java.math.BigDecimal;

public class CardPersistanceMapperImpl implements CardPersistanceMapper {

    private final BalancePersistanceMapper balancePersistanceMapper;
    private final BenefitPersistanceMapper benefitPersistanceMapper;

    public CardPersistanceMapperImpl(BalancePersistanceMapper balancePersistanceMapper, BenefitPersistanceMapper benefitPersistanceMapper) {
        this.balancePersistanceMapper = balancePersistanceMapper;
        this.benefitPersistanceMapper = benefitPersistanceMapper;
    }

    @Override
    public CardEntity toEntity(Card card) {
        return CardEntity.builder()
                .cardId(card.getId())
                .typeCard(card.getTypeCard())
                .categoryCard(card.getCategoryCard())
                .createdDate(card.getCreatedDate())
                .updatedDate(card.getUpdatedDate())
                .status(card.getStatus())
                .build();
    }

    @Override
    public Card toDomain(CardEntityVO cardEntity) {
        Amount credit = Amount.create(
                Currency.create(cardEntity.getCardAccount().getCurrency(), BigDecimal.ONE),
                cardEntity.getCardAccount().getCreditTotal()
        );

        return Card.create(
                cardEntity.getCardId(),
                cardEntity.getStatus(),
                cardEntity.getCreatedDate(),
                cardEntity.getUpdatedDate(),
                cardEntity.getTypeCard(),
                cardEntity.getCategoryCard(),
                Credit.create(credit, cardEntity.getCardAccount().getDebtTax()),
                cardEntity.getCardAccount().getCardStatus(),
                balancePersistanceMapper.toDomain(cardEntity.getBalance()),
                benefitPersistanceMapper.toDomain(cardEntity.getBenefit()),
                CardAccountId.create(cardEntity.getCardAccount().getCardAccountId()),
                cardEntity.getCardAccount().getPaymentDate()
        );
    }
}
