package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.domain.payment.factory.PaymentFactory;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntityCosmos;

public class PaymentPersistanceMapperCosmosImpl implements PaymentPersistanceMapperCosmos {

    private final PaymentFactory paymentFactory;

    public PaymentPersistanceMapperCosmosImpl(PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    @Override
    public Payment toDomain(PaymentEntityCosmos paymentEntityCosmos, Currency currency) {
        return paymentFactory.create(
                paymentEntityCosmos.getPaymentId(),
                paymentEntityCosmos.getStatus(),
                paymentEntityCosmos.getCreatedDate(),
                paymentEntityCosmos.getUpdatedDate(),
                currency,
                paymentEntityCosmos.getAmount(),
                paymentEntityCosmos.getPaymentDate(),
                paymentEntityCosmos.getPaymentApprobationDate(),
                paymentEntityCosmos.getCategory(),
                Long.parseLong(paymentEntityCosmos.getCardId()),
                paymentEntityCosmos.getChannel()
        );
    }

    @Override
    public PaymentEntityCosmos toEntity(Payment payment) {
        return PaymentEntityCosmos.builder()
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
