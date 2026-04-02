package com.bank.credit_card.infraestructure.persistence.db.generic.repository;

import com.bank.credit_card.domain.base.StatusEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface GenericMongoRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {

    @Query("{ '_id': ?#{[0].toString()}, 'cardId': ?1 }")
    Optional<T> findByIdAndCardId(ID id, String partitionId);

    default Optional<T> findActiveById(ID id, String partitionId) {
        return findByIdAndCardId(id, partitionId).filter(e -> {
            if (e instanceof GenericEntity ge) {
                return StatusEnum.ACTIVE.equals(ge.getStatus());
            }
            return true;
        });
    }
}
