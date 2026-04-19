package com.bank.credit_card.domain.base.vo;

import com.bank.credit_card.domain.base.exceptions.ApprobationException;

import java.time.LocalDateTime;

import static com.bank.credit_card.domain.base.constants.ApprobationErrorMessage.APPROBATION_DATE_REQUIRED;
import static com.bank.credit_card.domain.util.Validation.isNotNull;

public final class Approbation {
    private final LocalDateTime date;
    private final LocalDateTime approbationDate;

    private Approbation(LocalDateTime date, LocalDateTime approbationDate) {
        this.date = date;
        this.approbationDate = approbationDate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LocalDateTime getApprobationDate() {
        return approbationDate;
    }

    public static Approbation create(LocalDateTime date) {
        isNotNull(date, new ApprobationException(APPROBATION_DATE_REQUIRED));
        return new Approbation(date, null);
    }

    public static Approbation create(LocalDateTime date, LocalDateTime approbationDate) {
        isNotNull(date, new ApprobationException(APPROBATION_DATE_REQUIRED));
        return new Approbation(date, approbationDate);
    }
}
