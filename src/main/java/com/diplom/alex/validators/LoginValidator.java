package com.diplom.alex.validators;

import com.diplom.alex.validators.annotations.LoginMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class LoginValidator implements ConstraintValidator<LoginMatch, String> {

    private static final String LOGIN_PATTERN = "^[a-zA-Z\\d]+$";

    @Override
    public void initialize(LoginMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.compile(LOGIN_PATTERN).matcher(login).matches();
    }
}
