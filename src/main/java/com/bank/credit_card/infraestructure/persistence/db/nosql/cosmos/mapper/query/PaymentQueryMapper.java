package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntity;

public interface PaymentQueryMapper extends GenericQueryMapper<LoadPaymentView, PaymentEntity> {
}
