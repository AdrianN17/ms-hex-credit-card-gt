package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity;

import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.card.CardStatusEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.converter.CurrencyEnumConverter;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.converter.CardStatusEnumConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "CardAccounts")
public class CardAccountEntity extends GenericEntity {

    @Id
    @Column(name = "cardAccountId", nullable = false)
    private Long cardAccountId;

    @Column(name = "cardId", nullable = false)
    private Long cardId;

    @Column(name = "creditTotal", precision = 19, scale = 2, updatable = false)
    private BigDecimal creditTotal;

    @Column(name = "debtTax", precision = 19, scale = 2, updatable = false)
    private BigDecimal debtTax;

    @Convert(converter = CurrencyEnumConverter.class)
    @Column(name = "currency", updatable = false)
    private CurrencyEnum currency;

    @Column(name = "paymentDate", updatable = false)
    private Short paymentDate;

    @Convert(converter = CardStatusEnumConverter.class)
    @Column(name = "cardStatus", updatable = false)
    private CardStatusEnum cardStatus;
}
