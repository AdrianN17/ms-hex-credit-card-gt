package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity;

import com.bank.credit_card.domain.base.constants.CurrencyEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.converter.CurrencyEnumConverter;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "balances")
public class BalanceEntity extends GenericEntity {

    @Id
    @Column(name = "idBalance", nullable = false)
    private Long idBalance;

    @Column(name = "cardId", nullable = false)
    private Long cardId;

    @Column(name = "totalAmount", precision = 19, scale = 2, updatable = false)
    private BigDecimal totalAmount;

    @Column(name = "availableAmount", precision = 19, scale = 2)
    private BigDecimal availableAmount;

    @Column(name = "oldAmount", precision = 19, scale = 2)
    private BigDecimal oldAmount;

    @Convert(converter = CurrencyEnumConverter.class)
    @Column(name = "currency", updatable = false)
    private CurrencyEnum currency;

    @Column(name = "startDate", updatable = false)
    private LocalDate startDate;

    @Column(name = "endDate", updatable = false)
    private LocalDate endDate;
}
