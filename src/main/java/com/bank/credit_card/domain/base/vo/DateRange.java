package com.bank.credit_card.domain.base.vo;

import com.bank.credit_card.domain.card.BalanceException;

import java.time.LocalDate;

import static com.bank.credit_card.domain.base.vo.DateRangeConstant.NEXT_MONTH;
import static com.bank.credit_card.domain.base.vo.DateRangeErrorMessage.END_DATE_BEFORE_START_DATE;
import static com.bank.credit_card.domain.base.vo.DateRangeErrorMessage.START_DATE_AFTER_END_DATE;
import static com.bank.credit_card.domain.util.Validation.isConditional;

public class DateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;

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

    private Boolean isWithinRange(LocalDate date) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }

    public Boolean ensureWithinRange(LocalDate date) {
        return !isWithinRange(date);
    }

    public static DateRange create(LocalDate startDate, LocalDate endDate) {
        isConditional(startDate.isAfter(endDate), new BalanceException(START_DATE_AFTER_END_DATE));
        isConditional((startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())), new BalanceException(END_DATE_BEFORE_START_DATE));

        return new DateRange(startDate, endDate);
    }

    public static DateRange create(Short day) {
        LocalDate startDate = LocalDate.now().withDayOfMonth(day);
        LocalDate endDate = startDate.plusMonths(NEXT_MONTH);
        return new DateRange(startDate, endDate);
    }

    public static DateRange create(DateRange dateRange) {
        LocalDate startDate = dateRange.getStartDate().plusMonths(NEXT_MONTH);
        LocalDate endDate = dateRange.getEndDate().plusMonths(NEXT_MONTH);
        return new DateRange(startDate, endDate);
    }
}
