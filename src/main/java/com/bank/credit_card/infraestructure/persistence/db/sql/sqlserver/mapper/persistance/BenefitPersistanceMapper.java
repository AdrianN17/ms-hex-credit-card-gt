package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.persistance;

import com.bank.credit_card.domain.benefit.Benefit;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.BenefitEntity;

public interface BenefitPersistanceMapper extends GenericMapper<Benefit, BenefitEntity> {

}
