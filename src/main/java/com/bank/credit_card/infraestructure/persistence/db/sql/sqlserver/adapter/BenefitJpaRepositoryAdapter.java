package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.adapter;

import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BenefitEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception.BenefitPersistanceException;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.BenefitPersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.BenefitJpaRepository;

import java.util.Optional;

import static com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception.BenefitErrorMessage.BENEFIT_NOT_SAVED;

public class BenefitJpaRepositoryAdapter implements SaveBenefitPort {

    private final BenefitJpaRepository benefitJpaRepository;
    private final BenefitPersistanceMapper benefitPersistenceMapper;

    public BenefitJpaRepositoryAdapter(BenefitJpaRepository benefitJpaRepository, BenefitPersistanceMapper benefitPersistenceMapper) {
        this.benefitJpaRepository = benefitJpaRepository;
        this.benefitPersistenceMapper = benefitPersistenceMapper;
    }

    @Override
    public Optional<Long> save(Benefit benefit) {
        Optional<BenefitEntity> benefitEntity = Optional.of(benefitJpaRepository.save(benefitPersistenceMapper.toEntity(benefit)));
        return Optional.ofNullable(benefitEntity
                        .orElseThrow(() -> new BenefitPersistanceException(BENEFIT_NOT_SAVED)))
                .map(BenefitEntity::getIdBenefit);
    }
}
