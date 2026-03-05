package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntity;

public class PaymentPersistanceMapperImpl implements PaymentPersistanceMapper {

    @Override
    public Payment toDomain(PaymentEntity paymentEntity, Currency currency) {
        return Payment.create(
                paymentEntity.getPaymentId(),
                paymentEntity.getStatus(),
                paymentEntity.getCreatedDate(),
                paymentEntity.getUpdatedDate(),
                Amount.create(currency, paymentEntity.getAmount()),
                paymentEntity.getPaymentDate(),
                paymentEntity.getPaymentApprobationDate(),
                paymentEntity.getCategory(),
                CardId.create(paymentEntity.getCardId()),
                paymentEntity.getChannel()
        );
    }

    @Override
    public PaymentEntity toEntity(Payment payment) {
        return PaymentEntity.builder()
                .paymentId(payment.getId())
                .amount(payment.getPaymentAmount().getAmount())
                .currency(payment.getPaymentAmount().getCurrency().getCurrency())
                .paymentDate(payment.getPaymentDate())
                .paymentApprobationDate(payment.getPaymentApprobationDate())
                .category(payment.getCategory())
                .cardId(payment.getCardId().getValue().toString())
                .channel(payment.getChannelPayment())
                .createdDate(payment.getCreatedDate())
                .updatedDate(payment.getUpdatedDate())
                .status(payment.getStatus())
                .build();
    }
}
