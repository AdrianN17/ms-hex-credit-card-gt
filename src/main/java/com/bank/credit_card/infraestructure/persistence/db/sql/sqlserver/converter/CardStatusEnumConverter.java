package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.converter;

import com.bank.credit_card.domain.card.CardStatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CardStatusEnumConverter implements AttributeConverter<CardStatusEnum, Short> {

    @Override
    public Short convertToDatabaseColumn(CardStatusEnum attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public CardStatusEnum convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : CardStatusEnum.ofValue(dbData.intValue()).orElse(null);
    }
}
