package com.bank.credit_card.domain.balance;

import com.bank.credit_card.domain.balance.factory.BalanceFactory;
import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.domain.base.vo.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BalanceFactoryImpl implements BalanceFactory {

    @Override
    public Balance create(Long id, Currency currency,
                          Long cardId, BigDecimal total, Short paymentDay) {
        return BalanceSnapshot.builder()
                .balanceId(id)
                .currency(currency)
                .cardId(cardId)
                .total(total)
                .dateRange(paymentDay)
                .build();
    }

    @Override
    public Balance create(Long id, StatusEnum status, LocalDateTime createdDate, LocalDateTime updatedDate,
                          Currency currency, Long cardId, BigDecimal total, BigDecimal old,
                          BigDecimal available, LocalDate startDate, LocalDate endDate) {
        return BalanceSnapshot.builder()
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
    }
}
