package com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.adapter;

import com.bank.credit_card.application.port.in.query.criteria.FindPaymentByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.application.port.out.payment.query.LoadPaymentCurrencyPort;
import com.bank.credit_card.application.port.out.payment.query.LoadPaymentsByDatesAndCardIdPort;
import com.bank.credit_card.application.port.out.payment.usecase.LoadPaymentPort;
import com.bank.credit_card.application.port.out.payment.usecase.SavePaymentPort;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.entity.PaymentEntityMongo;
import com.bank.credit_card.infraestructure.persistence.db.nosql.common.exception.PaymentPersistanceException;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.persistance.PaymentPersistanceMapperMongo;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.mapper.query.PaymentQueryMapperMongo;
import com.bank.credit_card.infraestructure.persistence.db.nosql.mongo.repository.PaymentMongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.bank.credit_card.infraestructure.persistence.db.nosql.common.constant.TimeConstant.*;
import static com.bank.credit_card.infraestructure.persistence.db.nosql.common.exception.PaymentErrorMessage.*;

public class PaymentMongoRepositoryAdapter implements LoadPaymentPort, SavePaymentPort, LoadPaymentsByDatesAndCardIdPort, LoadPaymentCurrencyPort {

    private final PaymentMongoRepository paymentMongoRepository;
    private final PaymentPersistanceMapperMongo paymentPersistanceMapperMongo;
    private final PaymentQueryMapperMongo paymentQueryMapperMongo;

    public PaymentMongoRepositoryAdapter(PaymentMongoRepository paymentMongoRepository, PaymentPersistanceMapperMongo paymentPersistanceMapperMongo, PaymentQueryMapperMongo paymentQueryMapperMongo) {
        this.paymentMongoRepository = paymentMongoRepository;
        this.paymentPersistanceMapperMongo = paymentPersistanceMapperMongo;
        this.paymentQueryMapperMongo = paymentQueryMapperMongo;
    }

    @Override
    public List<LoadPaymentView> load(FindPaymentByDatesAndCardIdCriteria criteria) {

        return Optional.of(paymentMongoRepository.findByCardIdAndPaymentDateBetween(
                        String.valueOf(criteria.cardId()),
                        criteria.start().atStartOfDay(),
                        criteria.end().atTime(LAST_HOUR, LAST_MINUTE, LAST_SECOND)))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new PaymentPersistanceException(NO_PAYMENTS_FOUND))
                .stream()
                .map(paymentQueryMapperMongo::toView)
                .toList();
    }

    @Override
    public Optional<UUID> save(Payment payment) {
        return Optional.of(Optional.of(payment)
                .map(paymentPersistanceMapperMongo::toEntity)
                .map(paymentMongoRepository::save)
                .map(PaymentEntityMongo::getPaymentId)
                .orElseThrow(() -> new PaymentPersistanceException(PAYMENT_NOT_SAVED)));
    }

    @Override
    public Optional<Payment> load(UUID paymentId, Currency currency) {
        return Optional.of(paymentMongoRepository.findById(paymentId)
                        .orElseThrow(() -> new PaymentPersistanceException(PAYMENT_NOT_FOUND)))
                .map(payment -> paymentPersistanceMapperMongo.toDomain(payment, currency));
    }

    @Override
    public Optional<CurrencyEnum> load(UUID paymentId) {
        return Optional.of(paymentMongoRepository.findById(paymentId)
                        .orElseThrow(() -> new PaymentPersistanceException(PAYMENT_NOT_FOUND)))
                .map(PaymentEntityMongo::getCurrency);
    }
}
