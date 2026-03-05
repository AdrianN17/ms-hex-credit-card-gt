package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.exception;

public interface CardErrorMessage {
    String NO_CARD_AND_BALANCE_AND_BENEFIT_FOUND = "No card with children found";
    String CARD_NOT_SAVED = "Card not saved";
    String CARD_NOT_FOUND = "Card not found";
}
