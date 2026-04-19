package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo;

import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.BalanceEntityVO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceVOJpaRepository extends GenericJpaRepository<BalanceEntityVO, Long> {
    Optional<BalanceEntityVO> findByCardId(Long cardId);

    default Optional<BalanceEntityVO> findActiveByCardId(Long cardId) {
        return findByCardId(cardId).filter(e -> StatusEnum.ACTIVE.equals(e.getStatus()));
    }
}
