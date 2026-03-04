package com.bank.credit_card.application.port.in.command;

import java.util.UUID;

public record CardCancelPaymentCommand(UUID paymentId, Long cardId) {
}
