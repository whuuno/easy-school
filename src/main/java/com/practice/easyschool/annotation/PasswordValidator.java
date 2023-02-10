package com.practice.easyschool.annotation;

import com.practice.easyschool.vaidations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface PasswordValidator {
    String message() default "Password entered is not a valid method";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
