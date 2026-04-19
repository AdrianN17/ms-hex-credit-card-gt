package com.bank.credit_card.domain.base.vo;

import com.bank.credit_card.domain.base.exceptions.DateRangeException;

import java.time.LocalDate;

import static com.bank.credit_card.domain.base.constants.DateRangeConstant.NEXT_MONTH;
import static com.bank.credit_card.domain.base.constants.DateRangeErrorMessage.END_DATE_BEFORE_START_DATE;
import static com.bank.credit_card.domain.base.constants.DateRangeErrorMessage.START_DATE_AFTER_END_DATE;
import static com.bank.credit_card.domain.util.Validation.isNotConditional;

public final class DateRange {
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
        isNotConditional(startDate.isAfter(endDate), new DateRangeException(START_DATE_AFTER_END_DATE));
        isNotConditional((startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())), new DateRangeException(END_DATE_BEFORE_START_DATE));

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
