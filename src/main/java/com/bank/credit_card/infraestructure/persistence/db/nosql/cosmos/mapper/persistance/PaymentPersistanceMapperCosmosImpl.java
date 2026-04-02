package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntityCosmos;

public class PaymentPersistanceMapperCosmosImpl implements PaymentPersistanceMapperCosmos {

    @Override
    public Payment toDomain(PaymentEntityCosmos paymentEntityCosmos, Currency currency) {
        return Payment.create(
                paymentEntityCosmos.getPaymentId(),
                paymentEntityCosmos.getStatus(),
                paymentEntityCosmos.getCreatedDate(),
                paymentEntityCosmos.getUpdatedDate(),
                Amount.create(currency, paymentEntityCosmos.getAmount()),
                paymentEntityCosmos.getPaymentDate(),
                paymentEntityCosmos.getPaymentApprobationDate(),
                paymentEntityCosmos.getCategory(),
                CardId.create(paymentEntityCosmos.getCardId()),
                paymentEntityCosmos.getChannel()
        );
    }

    @Override
    public PaymentEntityCosmos toEntity(Payment payment) {
        return PaymentEntityCosmos.builder()
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
