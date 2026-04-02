package com.bank.credit_card.infraestructure.persistence.db.generic.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GenericMongoRespository<T, ID extends Serializable> extends MongoRepository<T, ID> {

}
