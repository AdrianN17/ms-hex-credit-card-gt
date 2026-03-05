package com.bank.credit_card.infraestructure.persistence.db.sql.sqlserver.converter;

import com.bank.credit_card.domain.card.CategoryCardEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryCardEnumConverter implements AttributeConverter<CategoryCardEnum, Short> {

    @Override
    public Short convertToDatabaseColumn(CategoryCardEnum attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public CategoryCardEnum convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : CategoryCardEnum.ofValue(dbData.intValue()).orElse(null);
    }
}

