package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.persistance;

import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericDomainCurrencyMapper;
import com.bank.credit_card.infraestructure.persistence.db.generic.mapper.GenericEntityMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.PaymentEntityMongo;

public interface PaymentPersistanceMapperMongo extends GenericDomainCurrencyMapper<Payment, PaymentEntityMongo, Currency>,
        GenericEntityMapper<Payment, PaymentEntityMongo> {

}
