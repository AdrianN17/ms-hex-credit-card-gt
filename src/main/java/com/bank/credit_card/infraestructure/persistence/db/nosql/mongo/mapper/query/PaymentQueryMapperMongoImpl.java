package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.PaymentEntityMongo;

public class PaymentQueryMapperMongoImpl implements PaymentQueryMapperMongo {

    @Override
    public LoadPaymentView toView(PaymentEntityMongo entity) {
        return new LoadPaymentView(
                entity.getPaymentId(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getPaymentDate(),
                entity.getPaymentApprobationDate(),
                entity.getCategory(),
                entity.getCardId(),
                entity.getChannel()
        );
    }
}
