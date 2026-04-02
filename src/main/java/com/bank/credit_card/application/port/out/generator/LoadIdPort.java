package com.bank.credit_card.application.port.out.generator;

import java.util.Optional;

@FunctionalInterface
public interface LoadIdPort {
    Optional<Long> load();
}
