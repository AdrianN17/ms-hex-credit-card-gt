package com.bank.credit_card.domain.balance.vo;

import com.bank.credit_card.domain.balance.BalanceException;

import java.time.LocalDate;

import static com.bank.credit_card.domain.balance.vo.DateRangeErrorMessage.*;

public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;

    private DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    private boolean isWithinRange(LocalDate date) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }

    public void ensureWithinRange(LocalDate date) {
        if (!isWithinRange(date)) {
            throw new BalanceException(DATE_NOT_WITHIN_RANGE);
        }
    }

    public static DateRange create(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BalanceException(START_DATE_AFTER_END_DATE);
        }

        if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
            throw new BalanceException(END_DATE_BEFORE_START_DATE);
        }

        return new DateRange(startDate, endDate);
    }

    public static DateRange create(Short day) {
        LocalDate startDate = LocalDate.now().withDayOfMonth(day);
        LocalDate endDate = startDate.plusMonths(1);
        return DateRange.create(startDate, endDate);
    }
}
