package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.converter;

import com.bank.credit_card.domain.card.TypeCardEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TypeCardEnumConverter implements AttributeConverter<TypeCardEnum, Short> {

    @Override
    public Short convertToDatabaseColumn(TypeCardEnum attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public TypeCardEnum convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : TypeCardEnum.ofValue(dbData.intValue()).orElse(null);
    }
}
