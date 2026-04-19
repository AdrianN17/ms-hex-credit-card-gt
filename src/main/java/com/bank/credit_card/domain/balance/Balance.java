package com.bank.credit_card.domain.balance;

import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.DateRange;
import com.bank.credit_card.domain.card.vo.CardId;

import java.time.LocalDateTime;

public interface Balance {
    CardId getCardId();

    Amount getTotal();

    Amount getOld();

    DateRange getDateRange();

    Amount getAvailable();

    StatusEnum getStatus();

    LocalDateTime getCreatedDate();

    LocalDateTime getUpdatedDate();

    BalanceId getId();

    void close();
}
