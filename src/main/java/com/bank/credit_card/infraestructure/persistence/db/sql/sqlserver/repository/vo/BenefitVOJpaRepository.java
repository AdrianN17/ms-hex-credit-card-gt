package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo;

import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.BenefitEntityVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BenefitVOJpaRepository extends GenericJpaRepository<BenefitEntityVO, Long> {
    @Query("SELECT b FROM BenefitEntityVO b WHERE b.card.cardId = :cardId")
    Optional<BenefitEntityVO> findByCardId(@Param("cardId") Long cardId);

    default Optional<BenefitEntityVO> findActiveByCardId(Long cardId) {
        return findByCardId(cardId).filter(e -> StatusEnum.ACTIVE.equals(e.getStatus()));
    }
}
