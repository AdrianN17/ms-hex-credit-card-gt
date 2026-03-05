package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.adapter;

import com.bank.credit_card.application.port.in.query.view.LoadCardBalanceBenefitView;
import com.bank.credit_card.application.port.out.card.query.LoadCardBalanceBenefitPort;
import com.bank.credit_card.application.port.out.card.usecase.LoadCardPort;
import com.bank.credit_card.application.port.out.card.usecase.SaveCardPort;
import com.bank.credit_card.domain.card.Card;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception.CardPersistanceException;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.CardPersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.query.CardQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.CardJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo.CardVOJpaRepository;

import java.util.Optional;

import static com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception.CardErrorMessage.*;

public class CardJpaRepositoryAdapter implements LoadCardPort, SaveCardPort, LoadCardBalanceBenefitPort {

    private final CardJpaRepository cardJpaRepository;
    private final CardVOJpaRepository cardVOJpaRepository;
    private final CardPersistanceMapper cardPersistanceMapper;
    private final CardQueryMapper cardQueryMapper;

    public CardJpaRepositoryAdapter(CardJpaRepository cardJpaRepository, CardVOJpaRepository cardVOJpaRepository, CardPersistanceMapper cardPersistanceMapper, CardQueryMapper cardQueryMapper) {
        this.cardJpaRepository = cardJpaRepository;
        this.cardVOJpaRepository = cardVOJpaRepository;
        this.cardPersistanceMapper = cardPersistanceMapper;
        this.cardQueryMapper = cardQueryMapper;
    }


    @Override
    public Optional<Card> load(Long cardId) {
        return Optional.of(cardVOJpaRepository.findById(cardId)
                        .orElseThrow(() -> new CardPersistanceException(CARD_NOT_FOUND)))
                .map(cardPersistanceMapper::toDomain);
    }

    @Override
    public Optional<Long> save(Card card) {
        return Optional.of(Optional.of(card)
                .map(cardPersistanceMapper::toEntity)
                .map(cardJpaRepository::save)
                .map(CardEntity::getCardId)
                .orElseThrow(() -> new CardPersistanceException(CARD_NOT_SAVED)));
    }

    @Override
    public Optional<LoadCardBalanceBenefitView> loadAll(Long cardId) {
        return Optional.of(cardVOJpaRepository.getCardAllProjectionByCardId(cardId)
                        .orElseThrow(() -> new CardPersistanceException(NO_CARD_AND_BALANCE_AND_BENEFIT_FOUND)))
                .map(cardQueryMapper::toView);
    }
}
