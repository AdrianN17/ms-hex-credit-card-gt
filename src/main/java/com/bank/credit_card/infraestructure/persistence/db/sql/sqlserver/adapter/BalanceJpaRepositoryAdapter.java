package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.adapter;

import com.bank.credit_card.application.port.out.balance.LoadBalancePort;
import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.domain.balance.old.Balance;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BalanceEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception.BalancePersistanceException;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.BalancePersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.BalanceJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo.BalanceVOJpaRepository;

import java.util.Optional;

import static com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception.BalanceErrorMessage.BALANCE_NOT_SAVED;

public class BalanceJpaRepositoryAdapter implements SaveBalancePort, LoadBalancePort {

    private final BalanceJpaRepository balanceJpaRepository;
    private final BalanceVOJpaRepository balanceVOJpaRepository;
    private final BalancePersistanceMapper balancePersistenceMapper;

    public BalanceJpaRepositoryAdapter(BalanceJpaRepository balanceJpaRepository, BalanceVOJpaRepository balanceVOJpaRepository, BalancePersistanceMapper balancePersistenceMapper) {
        this.balanceJpaRepository = balanceJpaRepository;
        this.balanceVOJpaRepository = balanceVOJpaRepository;
        this.balancePersistenceMapper = balancePersistenceMapper;
    }

    @Override
    public Optional<Long> save(Balance balance) {
        Optional<BalanceEntity> balanceEntity = Optional.of(balanceJpaRepository.save(balancePersistenceMapper.toEntity(balance)));
        return Optional.ofNullable(balanceEntity
                        .orElseThrow(() -> new BalancePersistanceException(BALANCE_NOT_SAVED)))
                .map(BalanceEntity::getIdBalance);
    }

    @Override
    public Optional<Balance> load(Long cardId, Currency currency) {
        return balanceVOJpaRepository.findActiveByCardId(cardId)
                .map(balanceEntityVO ->
                        balancePersistenceMapper.toDomain(balanceEntityVO, currency));
    }
}
