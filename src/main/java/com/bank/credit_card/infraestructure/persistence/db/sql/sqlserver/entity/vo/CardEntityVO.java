package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo;

import com.bank.credit_card.domain.card.CategoryCardEnum;
import com.bank.credit_card.domain.card.TypeCardEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.converter.CategoryCardEnumConverter;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.converter.TypeCardEnumConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Cards")
public class CardEntityVO extends GenericEntity {

    @Id
    @Column(name = "cardId", nullable = false)
    private Long cardId;

    @Convert(converter = TypeCardEnumConverter.class)
    @Column(name = "typeCard")
    private TypeCardEnum typeCard;

    @Convert(converter = CategoryCardEnumConverter.class)
    @Column(name = "categoryCard")
    private CategoryCardEnum categoryCard;

    @OneToOne(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private CardAccountEntityVO cardAccount;

    @OneToOne(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private BalanceEntityVO balance;

    @OneToOne(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private BenefitEntityVO benefit;
}
