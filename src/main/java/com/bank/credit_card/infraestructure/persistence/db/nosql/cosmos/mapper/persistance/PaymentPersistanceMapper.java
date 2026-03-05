package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericDomainCurrencyMapper;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericEntityMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntity;

public interface PaymentPersistanceMapper extends GenericDomainCurrencyMapper<Payment, PaymentEntity, Currency>,
        GenericEntityMapper<Payment, PaymentEntity> {

}
