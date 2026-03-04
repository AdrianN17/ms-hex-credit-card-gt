package com.bank.credit_card.infraestructure.persistence.db.generic.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GenericCosmosRespository<T, ID extends Serializable> extends CosmosRepository<T, ID> {

}
