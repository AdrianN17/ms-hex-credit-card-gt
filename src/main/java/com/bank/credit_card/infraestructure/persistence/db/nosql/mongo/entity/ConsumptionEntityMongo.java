package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity;

import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
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
@Document(collection = "Consumptions")
public class ConsumptionEntityMongo extends GenericEntity {

    @Id
    private UUID consumptionId;

    @Indexed
    @Field("cardId")
    private String cardId;

    private String sellerName;

    private CurrencyEnum currency;

    private BigDecimal amount;

    private LocalDateTime consumptionDate;

    private LocalDateTime consumptionApprobationDate;

}
