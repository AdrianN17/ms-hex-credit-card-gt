package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.domain.payment.factory.PaymentFactory;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.PaymentEntityMongo;

public class PaymentPersistanceMapperMongoImpl implements PaymentPersistanceMapperMongo {

    private final PaymentFactory paymentFactory;

    public PaymentPersistanceMapperMongoImpl(PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    @Override
    public Payment toDomain(PaymentEntityMongo paymentEntity, Currency currency) {
        return paymentFactory.create(
                paymentEntity.getPaymentId(),
                paymentEntity.getStatus(),
                paymentEntity.getCreatedDate(),
                paymentEntity.getUpdatedDate(),
                currency,
                paymentEntity.getAmount(),
                paymentEntity.getPaymentDate(),
                paymentEntity.getPaymentApprobationDate(),
                paymentEntity.getCategory(),
                Long.parseLong(paymentEntity.getCardId()),
                paymentEntity.getChannel()
        );
    }

    @Override
    public PaymentEntityMongo toEntity(Payment payment) {
        return PaymentEntityMongo.builder()
                .paymentId(payment.getId().getValue())
                .amount(payment.getPaymentAmount().getAmount())
                .currency(payment.getPaymentAmount().getCurrency().getCurrency())
                .paymentDate(payment.getPaymentApprobation().getDate())
                .paymentApprobationDate(payment.getPaymentApprobation().getApprobationDate())
                .category(payment.getCategory())
                .cardId(payment.getCardId().getValue().toString())
                .channel(payment.getChannelPayment())
                .createdDate(payment.getCreatedDate())
                .updatedDate(payment.getUpdatedDate())
                .status(payment.getStatus())
                .build();
    }
}
