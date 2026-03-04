package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntity;

import java.math.BigDecimal;

public class PaymentPersistanceMapperImpl implements PaymentPersistanceMapper {

    @Override
    public Payment toDomain(PaymentEntity paymentEntity) {
        return Payment.create(paymentEntity.getPaymentId(),
                Amount.create(Currency.create(paymentEntity.getCurrency(), BigDecimal.ONE), paymentEntity.getAmount()),
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
                .build();
    }
}
