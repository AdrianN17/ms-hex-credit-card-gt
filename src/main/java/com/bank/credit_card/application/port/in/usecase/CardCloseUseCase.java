package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardCloseCommand;

@FunctionalInterface
public interface CardCloseUseCase {
    Long closeCard(CardCloseCommand cardCloseCommand);
}
