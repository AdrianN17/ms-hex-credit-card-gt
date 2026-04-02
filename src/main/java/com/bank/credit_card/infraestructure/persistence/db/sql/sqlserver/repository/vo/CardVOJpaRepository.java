package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo;

import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.projection.CardCurrencyProjection;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.projection.CardSumaryProjection;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.CardEntityVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardVOJpaRepository extends GenericJpaRepository<CardEntityVO, Long> {

    @Query(value = """
            SELECT
                c.typeCard             AS typeCard,
                c.categoryCard         AS categoryCard,
                ca.creditTotal         AS creditTotal,
                ca.debtTax             AS debtTax,
                ca.currency            AS currency,
                ca.paymentDate         AS paymentDate,
                ca.cardStatus          AS cardStatus,
                b.hasDiscount          AS hasDiscount,
                b.totalPoints          AS totalPoints,
                b.multiplierPoints     AS multiplierPoints,
                bal.totalAmount        AS totalAmount,
                bal.availableAmount    AS availableAmount,
                bal.oldAmount          AS oldAmount,
                bal.startDate          AS startDate,
                bal.endDate            AS endDate
            FROM Cards c
            JOIN CardAccounts ca  ON ca.cardId  = c.cardId AND ca.status = 1
            JOIN balances bal      ON bal.cardId = c.cardId AND bal.status = 1
            JOIN Benefits b        ON b.cardId   = c.cardId AND b.status = 1
            WHERE c.cardId = :cardId
            AND c.status = 1
            """, nativeQuery = true)
    Optional<CardSumaryProjection> getCardAllProjectionByCardId(Long cardId);

    @Query(value = """
            SELECT
                ca.currency            AS currency
            FROM Cards c
            JOIN CardAccounts ca ON ca.cardId = c.cardId AND ca.status = 1
            WHERE c.cardId = :cardId
            AND c.status = 1
            """, nativeQuery = true)
    Optional<CardCurrencyProjection> getCardCurrencyProjectionByCardId(Long cardId);
}
