package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.adapter;

import com.bank.credit_card.application.port.in.query.criteria.FindPaymentByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.application.port.out.payment.query.LoadPaymentCurrencyPort;
import com.bank.credit_card.application.port.out.payment.query.LoadPaymentsByDatesAndCardIdPort;
import com.bank.credit_card.application.port.out.payment.usecase.LoadPaymentPort;
import com.bank.credit_card.application.port.out.payment.usecase.SavePaymentPort;
import com.bank.credit_card.domain.base.CurrencyEnum;
import com.bank.credit_card.domain.base.vo.Currency;
import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntity;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.exception.PaymentPersistanceException;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.PaymentPersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.PaymentQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository.PaymentCosmosRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.constant.TimeConstant.*;
import static com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.exception.PaymentErrorMessage.*;

public class PaymentCosmosRepositoryAdapter implements LoadPaymentPort, SavePaymentPort, LoadPaymentsByDatesAndCardIdPort, LoadPaymentCurrencyPort {

    private final PaymentCosmosRepository paymentCosmosRepository;
    private final PaymentPersistanceMapper paymentPersistanceMapper;
    private final PaymentQueryMapper paymentQueryMapper;

    public PaymentCosmosRepositoryAdapter(PaymentCosmosRepository paymentCosmosRepository, PaymentPersistanceMapper paymentPersistanceMapper, PaymentQueryMapper paymentQueryMapper) {
        this.paymentCosmosRepository = paymentCosmosRepository;
        this.paymentPersistanceMapper = paymentPersistanceMapper;
        this.paymentQueryMapper = paymentQueryMapper;
    }

    @Override
    public List<LoadPaymentView> load(FindPaymentByDatesAndCardIdCriteria criteria) {

        return Optional.of(paymentCosmosRepository.findByCardIdAndPaymentDateBetween(
                        String.valueOf(criteria.cardId()),
                        criteria.start().atStartOfDay(),
                        criteria.end().atTime(LAST_HOUR, LAST_MINUTE, LAST_SECOND)))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new PaymentPersistanceException(NO_PAYMENTS_FOUND))
                .stream()
                .map(paymentQueryMapper::toView)
                .toList();
    }

    @Override
    public Optional<UUID> save(Payment payment) {
        return Optional.of(Optional.of(payment)
                .map(paymentPersistanceMapper::toEntity)
                .map(paymentCosmosRepository::save)
                .map(PaymentEntity::getPaymentId)
                .orElseThrow(() -> new PaymentPersistanceException(PAYMENT_NOT_SAVED)));
    }

    @Override
    public Optional<Payment> load(UUID paymentId, Currency currency) {
        return Optional.of(paymentCosmosRepository.findById(paymentId)
                        .orElseThrow(() -> new PaymentPersistanceException(PAYMENT_NOT_FOUND)))
                .map(payment -> paymentPersistanceMapper.toDomain(payment, currency));
    }

    @Override
    public Optional<CurrencyEnum> load(UUID paymentId) {
        return Optional.of(paymentCosmosRepository.findById(paymentId)
                        .orElseThrow(() -> new PaymentPersistanceException(PAYMENT_NOT_FOUND)))
                .map(PaymentEntity::getCurrency);
    }
}
