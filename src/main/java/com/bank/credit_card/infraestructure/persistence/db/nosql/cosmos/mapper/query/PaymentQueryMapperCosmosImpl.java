package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query;

import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntityCosmos;
import org.springframework.stereotype.Component;

@Component
public class PaymentQueryMapperCosmosImpl implements PaymentQueryMapperCosmos {

    @Override
    public LoadPaymentView toView(PaymentEntityCosmos entity) {
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
