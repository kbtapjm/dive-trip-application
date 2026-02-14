package io.divetrip.application.validator;


import io.divetrip.application.validator.valid.EnumValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ValueOfEnumValidator implements ConstraintValidator<EnumValue, String> {

    private EnumValue enumValue;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        this.enumValue = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = false;
        Enum<?>[] enumValues = this.enumValue.enumClass().getEnumConstants();

        if (StringUtils.isEmpty(value)) {
            return false;
        }

        if (!Objects.isNull(enumValues)) {
            for (Object enumValue : enumValues) {
                if (value.equals(enumValue.toString()) || this.enumValue.ignoreCase() && value.equalsIgnoreCase(enumValue.toString())) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

}
