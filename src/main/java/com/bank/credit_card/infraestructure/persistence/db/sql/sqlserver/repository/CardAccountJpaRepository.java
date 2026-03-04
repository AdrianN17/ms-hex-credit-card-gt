package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.repository;

import com.bank.credit_card.infraestructure.persistence.db.generic.repository.GenericJpaRespository;
import com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.entity.CardAccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CardAccountJpaRepository extends GenericJpaRespository<CardAccountEntity, Long> {
}
