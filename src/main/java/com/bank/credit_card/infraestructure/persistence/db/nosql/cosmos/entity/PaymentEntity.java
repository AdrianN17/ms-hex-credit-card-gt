package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.card.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.ChannelPaymentEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Container(containerName = "Payments")
public class PaymentEntity extends GenericEntity {

    @Id
    private UUID paymentId;

    @PartitionKey
    private String cardId;

    private BigDecimal amount;

    private CurrencyEnum currency;

    private LocalDateTime paymentDate;

    private LocalDateTime paymentApprobationDate;

    private ChannelPaymentEnum channel;

    private CategoryPaymentEnum category;
}
