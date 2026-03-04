package com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.adapter;

import com.bank.credit_card.application.port.in.query.criteria.FindPaymentByDatesAndCardIdCriteria;
import com.bank.credit_card.application.port.in.query.view.LoadPaymentView;
import com.bank.credit_card.application.port.out.payment.query.LoadPaymentsByDatesAndCardIdPort;
import com.bank.credit_card.application.port.out.payment.usecase.LoadPaymentPort;
import com.bank.credit_card.application.port.out.payment.usecase.SavePaymentPort;
import com.bank.credit_card.domain.payment.Payment;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.entity.PaymentEntity;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.persistance.PaymentPersistanceMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.mapper.query.PaymentQueryMapper;
import com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.repository.PaymentCosmosRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.bank.credit_card.infraestructure.persistence.db.nosql.cosmos.constant.TimeConstant.*;

public class PaymentCosmosRepositoryAdapter implements LoadPaymentPort, SavePaymentPort, LoadPaymentsByDatesAndCardIdPort {

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
        return paymentCosmosRepository.findByCardIdAndPaymentDateBetween(
                        String.valueOf(criteria.cardId()),
                        criteria.start().atStartOfDay(),
                        criteria.end().atTime(LAST_HOUR, LAST_MINUTE, LAST_SECOND))
                .stream()
                .map(paymentQueryMapper::toView)
                .toList();
    }

    @Override
    public Optional<UUID> save(Payment payment) {
        Optional<PaymentEntity> paymentEntity = Optional.of(paymentCosmosRepository.save(paymentPersistanceMapper.toEntity(payment)));
        return paymentEntity.map(PaymentEntity::getPaymentId);
    }

    @Override
    public Optional<Payment> load(UUID paymentId) {
        return paymentCosmosRepository.findById(paymentId)
                .map(paymentPersistanceMapper::toDomain);
    }
}
