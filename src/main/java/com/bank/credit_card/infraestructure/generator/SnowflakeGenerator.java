package com.bank.credit_card.infraestructure.generator;

import com.bank.credit_card.application.port.out.generator.LoadIdPort;

import java.util.Optional;

public class SnowflakeGenerator implements LoadIdPort {

    private final long machineId = 1L;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private synchronized Long nextId() {

        long timestamp = System.currentTimeMillis();

        if (timestamp == lastTimestamp) {
            sequence++;
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        return (timestamp << 22)
                | (machineId << 12)
                | sequence;
    }

    @Override
    public Optional<Long> load() {
        return Optional.of(nextId());
    }
}
