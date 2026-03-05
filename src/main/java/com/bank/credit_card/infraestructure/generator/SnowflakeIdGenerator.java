package com.bank.credit_card.infraestructure.generator;

import com.bank.credit_card.domain.generator.CardIdGenerator;

public class SnowflakeIdGenerator implements CardIdGenerator {

    private final long machineId = 1L;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    @Override
    public synchronized Long nextId() {

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
}
