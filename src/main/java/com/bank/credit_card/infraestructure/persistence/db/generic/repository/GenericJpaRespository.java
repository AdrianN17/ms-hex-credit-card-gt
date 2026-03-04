package com.bank.credit_card.infraestructure.persistence.db.generic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericJpaRespository<T, ID> extends JpaRepository<T, ID> {

}
