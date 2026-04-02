package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository;

import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BenefitEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitJpaRepository extends GenericJpaRepository<BenefitEntity, Long> {
}
