package com.bank.credit_card.infraestructure.persistence.db.generic.repository;

import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.infraestructure.persistence.db.generic.entity.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface GenericJpaRepository<T, ID> extends JpaRepository<T, ID> {

    default Optional<T> findActiveById(ID id) {
        return findById(id).filter(e -> {
            if (e instanceof GenericEntity ge) {
                return StatusEnum.ACTIVE.equals(ge.getStatus());
            }
            return true;
        });
    }
}
