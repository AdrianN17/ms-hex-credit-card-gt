package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance;

import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericDomainMapper;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericEntityMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntity;

public interface PaymentPersistanceMapper extends GenericDomainMapper<Payment, PaymentEntity>,
        GenericEntityMapper<Payment, PaymentEntity> {

}
