package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.PaymentEntityMongo;

public interface PaymentQueryMapperMongo extends GenericQueryMapper<LoadPaymentView, PaymentEntityMongo> {
}
