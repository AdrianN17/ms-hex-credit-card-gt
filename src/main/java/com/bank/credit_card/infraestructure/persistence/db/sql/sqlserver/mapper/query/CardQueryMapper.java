package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadCardBalanceBenefitView;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardEntity;

public interface CardQueryMapper extends GenericQueryMapper<LoadCardBalanceBenefitView, CardEntity> {
}
