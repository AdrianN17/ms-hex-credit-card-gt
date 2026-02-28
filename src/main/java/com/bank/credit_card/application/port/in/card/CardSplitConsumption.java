package com.bank.credit_card.application.port.in.card;

@FunctionalInterface
public interface CardSplitConsumption {
        void splitConsumption(String consumptionId, Integer installments);
}
