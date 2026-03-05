package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.domain.card.vo.CardAccountId;
import com.bank.credit_card.domain.card.vo.Credit;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardAccountEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardEntity;

import java.math.BigDecimal;

public class CardPersistanceMapperImpl implements CardPersistanceMapper {

    private final BalancePersistanceMapper balancePersistanceMapper;
    private final BenefitPersistanceMapper benefitPersistanceMapper;

    public CardPersistanceMapperImpl(BalancePersistanceMapper balancePersistanceMapper, BenefitPersistanceMapper benefitPersistanceMapper) {
        this.balancePersistanceMapper = balancePersistanceMapper;
        this.benefitPersistanceMapper = benefitPersistanceMapper;
    }

    @Override
    public Card toDomain(CardEntity cardEntity) {

        Amount credit = Amount.create(
                Currency.create(cardEntity.getCardAccountEntity().getCurrency(), BigDecimal.ONE),
                cardEntity.getCardAccountEntity().getCreditTotal()
        );

        return Card.create(
                cardEntity.getCardId(),
                cardEntity.getStatus(),
                cardEntity.getCreatedDate(),
                cardEntity.getUpdatedDate(),
                cardEntity.getTypeCard(),
                cardEntity.getCategoryCard(),
                Credit.create(credit, cardEntity.getCardAccountEntity().getDebtTax()),
                cardEntity.getCardAccountEntity().getCardStatus(),
                balancePersistanceMapper.toDomain(cardEntity.getBalanceEntity()),
                benefitPersistanceMapper.toDomain(cardEntity.getBenefitEntity()),
                CardAccountId.create(cardEntity.getCardAccountEntity().getCardAccountId()),
                cardEntity.getCardAccountEntity().getPaymentDate()
        );
    }

    @Override
    public CardEntity toEntity(Card card) {

        CardAccountEntity cardAccountEntity = CardAccountEntity.builder()
                .cardAccountId(card.getCardAccountId().getValue())
                .cardStatus(card.getCardStatus())
                .currency(card.getCredit().getCreditTotal().getCurrency().getCurrency())
                .debtTax(card.getCredit().getDebtTax())
                .creditTotal(card.getCredit().getCreditTotal().getAmount())
                .paymentDate(card.getPaymentDay())
                .createdDate(card.getCreatedDate())
                .updatedDate(card.getUpdatedDate())
                .status(card.getStatus())
                .build();

        return CardEntity.builder()
                .cardId(card.getId())
                .typeCard(card.getTypeCard())
                .categoryCard(card.getCategoryCard())
                .cardAccountEntity(cardAccountEntity)
                .balanceEntity(balancePersistanceMapper.toEntity(card.getBalance()))
                .benefitEntity(benefitPersistanceMapper.toEntity(card.getBenefit()))
                .createdDate(card.getCreatedDate())
                .updatedDate(card.getUpdatedDate())
                .status(card.getStatus())
                .build();
    }
}
