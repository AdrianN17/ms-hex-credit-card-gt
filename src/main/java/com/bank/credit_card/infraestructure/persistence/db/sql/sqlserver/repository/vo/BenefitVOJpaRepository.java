package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo;

import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericJpaRespository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BenefitEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.BenefitEntityVO;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitVOJpaRepository extends GenericJpaRespository<BenefitEntityVO, Long> {
}
