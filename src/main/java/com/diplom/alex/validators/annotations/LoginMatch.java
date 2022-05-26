package com.diplom.alex.validators.annotations;

import com.diplom.alex.validators.LoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LoginValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginMatch {

    String message() default "Логин имеет неверный формат!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
