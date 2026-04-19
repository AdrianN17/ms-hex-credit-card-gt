package com.bank.credit_card.domain.balance.factory;

import com.bank.credit_card.domain.balance.Balance;
import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.domain.base.vo.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BalanceFactory {

    Balance create(
            Long id,
            StatusEnum status,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Currency currency,
            Long cardId,
            BigDecimal total,
            BigDecimal old,
            BigDecimal available,
            LocalDate startDate,
            LocalDate endDate
    );

    Balance create(
            Long id,
            Currency currency,
            Long cardId,
            BigDecimal total,
            Short paymentDay
    );

}
