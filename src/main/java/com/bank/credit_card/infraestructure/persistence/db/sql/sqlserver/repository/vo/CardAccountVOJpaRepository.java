package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository.vo;

import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericJpaRespository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardAccountEntity;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.vo.CardAccountEntityVO;
import org.springframework.stereotype.Repository;

@Repository
public interface CardAccountVOJpaRepository extends GenericJpaRespository<CardAccountEntityVO, Long> {
}
