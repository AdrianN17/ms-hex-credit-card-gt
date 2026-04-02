package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity;

import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
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
@Table(name = "Benefits")
public class BenefitEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBenefit", nullable = false)
    private Long idBenefit;

    @Column(name = "cardId", nullable = false)
    private Long cardId;

    @Column(name = "hasDiscount")
    private Boolean hasDiscount;

    @Column(name = "totalPoints")
    private Integer totalPoints;

    @Column(name = "multiplierPoints", precision = 5, scale = 2)
    private BigDecimal multiplierPoints;
}
