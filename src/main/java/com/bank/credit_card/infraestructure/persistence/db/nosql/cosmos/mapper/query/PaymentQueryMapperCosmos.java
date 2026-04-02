package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntityCosmos;

public interface PaymentQueryMapperCosmos extends GenericQueryMapper<LoadPaymentView, PaymentEntityCosmos> {
}
