package com.bank.credit_card.domain.balance.factory;

import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.balance.BalanceConsumo;
import com.bank.credit_card.domain.balance.BalancePago;
import com.bank.credit_card.domain.base.vo.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BalanceFactoryImpl implements BalanceFactory {

    @Override
    public Balance create(Long id,
                          Integer status,
                          LocalDateTime createdDate,
                          LocalDateTime updatedDate,
                          Currency currency,
                          Long cardId,
                          BigDecimal total,
                          BigDecimal old,
                          BigDecimal available,
                          LocalDate startDate,
                          LocalDate endDate,
                          BalanceType balanceType) {

        return switch (balanceType) {
            case PAYMENT -> BalancePago.builder()
                    .balanceId(id)
                    .status(status)
                    .createdDate(createdDate)
                    .updatedDate(updatedDate)
                    .currency(currency)
                    .cardId(cardId)
                    .total(total)
                    .old(old)
                    .available(available)
                    .dateRange(startDate, endDate)
                    .build();
            case CONSUMPTION -> BalanceConsumo.builder()
                    .balanceId(id)
                    .status(status)
                    .createdDate(createdDate)
                    .updatedDate(updatedDate)
                    .currency(currency)
                    .cardId(cardId)
                    .total(total)
                    .old(old)
                    .available(available)
                    .dateRange(startDate, endDate)
                    .build();
        };
    }

    @Override
    public Balance create(Long id,
                          Currency currency,
                          Long cardId,
                          BigDecimal total,
                          Short paymentDay,
                          BalanceType balanceType) {
        return switch (balanceType) {
            case PAYMENT -> BalancePago.builder()
                    .balanceId(id)
                    .currency(currency)
                    .cardId(cardId)
                    .total(total)
                    .dateRange(paymentDay)
                    .build();
            case CONSUMPTION -> BalanceConsumo.builder()
                    .balanceId(id)
                    .currency(currency)
                    .cardId(cardId)
                    .total(total)
                    .dateRange(paymentDay)
                    .build();
        };
    }

}
