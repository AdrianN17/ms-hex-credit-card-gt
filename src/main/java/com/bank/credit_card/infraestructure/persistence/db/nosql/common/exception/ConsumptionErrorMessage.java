package com.bank.credit_card.infraestructure.persistence.db.nosql.common.exception;

public interface ConsumptionErrorMessage {
    String NO_CONSUMPTIONS_FOUND = "No consumptions found";
    String CONSUMPTION_NOT_SAVED = "Consumption not saved";
    String CONSUMPTION_NOT_FOUND = "Consumption not found";

    String INCORRECT_CURRENCY_VALUE = "Incorrect currency value";

}
