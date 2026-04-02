package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo;

import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericJpaRepository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.BalanceEntityVO;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceVOJpaRepository extends GenericJpaRepository<BalanceEntityVO, Long> {
}
