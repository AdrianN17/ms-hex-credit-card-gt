package com.bank.credit_card.domain.payment;

import com.bank.credit_card.domain.base.enums.StatusEnum;
import com.bank.credit_card.domain.base.vo.Amount;
import com.bank.credit_card.domain.base.vo.Approbation;
import com.bank.credit_card.domain.base.vo.DateRange;
import com.bank.credit_card.domain.card.CategoryPaymentEnum;
import com.bank.credit_card.domain.card.vo.CardId;
import com.bank.credit_card.domain.payment.vo.PaymentId;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Payment {

    Amount getPaymentAmount();

    Approbation getPaymentApprobation();

    CategoryPaymentEnum getCategory();

    CardId getCardId();

    ChannelPaymentEnum getChannelPayment();

    StatusEnum getStatus();

    LocalDateTime getCreatedDate();

    LocalDateTime getUpdatedDate();

    PaymentId getId();

    void validateIfPaymentIsPossible(Amount available, Amount total, DateRange dateRange);

    void close();

}
