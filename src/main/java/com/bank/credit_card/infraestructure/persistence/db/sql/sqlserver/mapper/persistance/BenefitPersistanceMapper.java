package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericDomainMapper;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericEntityMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BenefitEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.BenefitEntityVO;

public interface BenefitPersistanceMapper extends GenericDomainMapper<Benefit, BenefitEntityVO>,
        GenericEntityMapper<Benefit, BenefitEntity> {

}
