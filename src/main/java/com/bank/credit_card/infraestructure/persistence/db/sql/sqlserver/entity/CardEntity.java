package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity;

import com.bank.credit_card.domain.card.CategoryCardEnum;
import com.bank.credit_card.domain.card.TypeCardEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Cards")
public class CardEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardId", nullable = false)
    private Long cardId;

    @Column(name = "typeCard")
    private TypeCardEnum typeCard;

    @Column(name = "categoryCard")
    private CategoryCardEnum categoryCard;

    private CardAccountEntity cardAccountEntity;

    private BalanceEntity balanceEntity;

    private BenefitEntity benefitEntity;
}
