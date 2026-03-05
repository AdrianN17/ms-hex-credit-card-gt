package com.bank.credit_card.infraestructure.persistence.db.generic.converter;

import com.bank.credit_card.domain.base.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusEnumConverter implements AttributeConverter<StatusEnum, Short> {

    @Override
    public Short convertToDatabaseColumn(StatusEnum attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public StatusEnum convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : StatusEnum.ofValue(dbData.intValue()).orElse(null);
    }
}