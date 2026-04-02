package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo;

import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.BenefitEntityVO;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitVOJpaRepository extends GenericJpaRepository<BenefitEntityVO, Long> {
}
