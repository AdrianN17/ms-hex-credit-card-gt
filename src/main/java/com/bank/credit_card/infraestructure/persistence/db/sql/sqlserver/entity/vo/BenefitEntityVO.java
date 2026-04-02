package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo;

import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Benefits")
public class BenefitEntityVO extends GenericEntity {

    @Id
    @Column(name = "idBenefit", nullable = false)
    private Long idBenefit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardId", nullable = false)
    private CardEntityVO card;

    @Column(name = "hasDiscount")
    private Boolean hasDiscount;

    @Column(name = "totalPoints")
    private Integer totalPoints;

    @Column(name = "multiplierPoints", precision = 5, scale = 2)
    private BigDecimal multiplierPoints;
}
