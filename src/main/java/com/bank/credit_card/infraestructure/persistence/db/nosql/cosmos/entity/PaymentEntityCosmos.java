package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.bank.credit_card.domain.base.constants.CurrencyEnum;
import com.bank.credit_card.domain.card.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.ChannelPaymentEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Container(containerName = "Payments")
public class PaymentEntityCosmos extends GenericEntity {

    @Id
    private UUID paymentId;

    @PartitionKey
    private String cardId;

    private BigDecimal amount;

    private CurrencyEnum currency;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime paymentDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime paymentApprobationDate;

    private ChannelPaymentEnum channel;

    private CategoryPaymentEnum category;
}
