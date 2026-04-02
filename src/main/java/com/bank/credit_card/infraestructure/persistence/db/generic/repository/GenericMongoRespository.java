package com.bank.credit_card.infraestructure.persistence.db.generic.repository;

import com.bank.credit_card.domain.base.StatusEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface GenericMongoRespository<T, ID extends Serializable> extends MongoRepository<T, ID> {

    default Optional<T> findActiveById(ID id) {
        return findById(id).filter(e -> {
            if (e instanceof GenericEntity ge) {
                return StatusEnum.ACTIVE.equals(ge.getStatus());
            }
            return true;
        });
    }
}
