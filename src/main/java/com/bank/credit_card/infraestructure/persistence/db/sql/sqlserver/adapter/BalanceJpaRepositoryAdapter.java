package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.adapter;

import com.bank.credit_card.application.port.out.balance.SaveBalancePort;
import com.bank.credit_card.domain.card.Balance;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BalanceEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.BalancePersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.BalanceJpaRepository;

import java.util.Optional;

public class BalanceJpaRepositoryAdapter implements SaveBalancePort {

    private final BalanceJpaRepository balanceJpaRepository;
    private final BalancePersistanceMapper balancePersistenceMapper;

    public BalanceJpaRepositoryAdapter(BalanceJpaRepository balanceJpaRepository, BalancePersistanceMapper balancePersistenceMapper) {
        this.balanceJpaRepository = balanceJpaRepository;
        this.balancePersistenceMapper = balancePersistenceMapper;
    }

    @Override
    public Optional<Long> save(Balance balance) {
        Optional<BalanceEntity> balanceEntity = Optional.of(balanceJpaRepository.save(balancePersistenceMapper.toEntity(balance)));
        return balanceEntity.map(BalanceEntity::getIdBalance);
    }
}
