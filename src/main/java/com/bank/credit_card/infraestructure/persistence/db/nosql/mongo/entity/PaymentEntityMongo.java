package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity;

import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.card.CategoryPaymentEnum;
import com.bank.credit_card.domain.payment.ChannelPaymentEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.converter.CurrencyEnumConverter;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import com.bank.credit_card.infraestructure.persistence.db.nosql.common.converter.CategoryPaymentEnumConverter;
import com.bank.credit_card.infraestructure.persistence.db.nosql.common.converter.ChannelPaymentEnumConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Document(collection = "Payments")
public class PaymentEntityMongo extends GenericEntity {

    @Id
    private UUID paymentId;

    @Indexed
    @Field("cardId")
    private String cardId;

    private BigDecimal amount;

    @Convert(converter = CurrencyEnumConverter.class)
    private CurrencyEnum currency;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime paymentDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime paymentApprobationDate;

    @Convert(converter = ChannelPaymentEnumConverter.class)
    private ChannelPaymentEnum channel;

    @Convert(converter = CategoryPaymentEnumConverter.class)
    private CategoryPaymentEnum category;
}
