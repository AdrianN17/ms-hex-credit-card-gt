package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardCreateCommand;
import com.bank.credit_card.domain.card.vo.CardId;

@FunctionalInterface
public interface CardCreateUseCase {
    CardId createCard(CardCreateCommand cardCreateCommand);
}
