package com.bank.credit_card.domain.generator;

@FunctionalInterface
public interface CardIdGenerator {
    Long nextId();
}
