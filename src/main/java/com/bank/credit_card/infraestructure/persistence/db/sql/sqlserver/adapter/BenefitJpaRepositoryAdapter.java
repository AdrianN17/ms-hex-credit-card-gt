package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.adapter;

import com.bank.credit_card.application.port.out.benefit.LoadBenefitPort;
import com.bank.credit_card.application.port.out.benefit.SaveBenefitPort;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BenefitEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception.BenefitPersistanceException;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance.BenefitPersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.BenefitJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo.BenefitVOJpaRepository;

import java.util.Optional;

import static com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception.BenefitErrorMessage.BENEFIT_NOT_SAVED;

public class BenefitJpaRepositoryAdapter implements SaveBenefitPort, LoadBenefitPort {

    private final BenefitJpaRepository benefitJpaRepository;
    private final BenefitVOJpaRepository benefitVOJpaRepository;
    private final BenefitPersistanceMapper benefitPersistenceMapper;

    public BenefitJpaRepositoryAdapter(BenefitJpaRepository benefitJpaRepository, BenefitVOJpaRepository benefitVOJpaRepository, BenefitPersistanceMapper benefitPersistenceMapper) {
        this.benefitJpaRepository = benefitJpaRepository;
        this.benefitVOJpaRepository = benefitVOJpaRepository;
        this.benefitPersistenceMapper = benefitPersistenceMapper;
    }

    @Override
    public Optional<Long> save(Benefit benefit) {
        Optional<BenefitEntity> benefitEntity = Optional.of(benefitJpaRepository.save(benefitPersistenceMapper.toEntity(benefit)));
        return Optional.ofNullable(benefitEntity
                        .orElseThrow(() -> new BenefitPersistanceException(BENEFIT_NOT_SAVED)))
                .map(BenefitEntity::getIdBenefit);
    }

    @Override
    public Optional<Benefit> load(Long cardId) {
        return benefitVOJpaRepository.findActiveByCardId(cardId)
                .map(benefitPersistenceMapper::toDomain);
    }
}
