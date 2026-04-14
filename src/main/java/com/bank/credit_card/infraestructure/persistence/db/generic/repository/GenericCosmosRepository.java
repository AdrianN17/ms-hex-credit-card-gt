package com.bank.credit_card.infraestructure.persistence.db.generic.repository;

import com.azure.cosmos.models.PartitionKey;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.bank.credit_card.domain.base.constants.StatusEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface GenericCosmosRepository<T, ID extends Serializable> extends CosmosRepository<T, ID> {

    default Optional<T> findActiveById(ID id, String partitionKeyValue) {
        return findById(id, new PartitionKey(partitionKeyValue)).filter(e -> {
            if (e instanceof GenericEntity ge) {
                return StatusEnum.ACTIVE.equals(ge.getStatus());
            }
            return true;
        });
    }
}
