package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity;

import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.card.CardStatusEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "CardAccounts")
public class CardAccountEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardAccountId", nullable = false)
    private Long cardAccountId;

    @Column(name = "cardId", nullable = false)
    private Long cardId;

    @Column(name = "creditTotal", precision = 19, scale = 2)
    private BigDecimal creditTotal;

    @Column(name = "debtTax", precision = 19, scale = 2)
    private BigDecimal debtTax;

    @Column(name = "currency")
    private CurrencyEnum currency;

    @Column(name = "paymentDate")
    private Short paymentDate;

    @Column(name = "cardStatus")
    private CardStatusEnum cardStatus;
}
