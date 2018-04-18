package com.div.spring.web.validation;

import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * Created by Div on 2018-04-17.
 */
public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String> {
    private int min;

    public void initialize(ValidEmail validEmail) {
        min = validEmail.min();
    }

    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(email.length() < min) {
            return false;
        }

        if(!EmailValidator.getInstance().isValid(email)) {
            return false;
        }

        return true;
    }
}
