package com.bank.credit_card.application.port.in.usecase;

import com.bank.credit_card.application.port.in.command.CardCloseCommand;
import com.bank.credit_card.domain.card.vo.CardId;

@FunctionalInterface
public interface CardCloseUseCase {
    CardId closeCard(CardCloseCommand cardCloseCommand);
}
