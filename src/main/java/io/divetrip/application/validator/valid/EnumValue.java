package io.divetrip.application.validator.valid;

import io.divetrip.application.validator.ValueOfEnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface EnumValue {

    // enum class
    Class<? extends Enum<?>> enumClass();

    // 오류 발생 시 생성할 메세지
    String message() default "{invalid.type.value}";

    // 상황별 validation 제어를 위해 사용
    Class<?>[] groups() default {};

    // 심각도
    Class<? extends Payload>[] payload() default {};

    // 대소문자를 구별할 것인지 정하는 boolean 값
    boolean ignoreCase() default false;
}
