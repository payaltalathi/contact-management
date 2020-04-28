package com.evolenthealth.contactmanagement.validator;

import com.evolenthealth.contactmanagement.entity.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<ValidStatus, String> {

    @Override
    public void initialize(ValidStatus status) {
        ConstraintValidator.super.initialize(status);
    }

    @Override
    public boolean isValid(String statusField, ConstraintValidatorContext context) {

        context.disableDefaultConstraintViolation();
        if(null != statusField && null == Status.fromValue(statusField)){
            context.buildConstraintViolationWithTemplate("Invalid value for status. Status Field can also take values from Enum: [Active, Inactive]").addConstraintViolation();
            return false;
        }
        return true;
    }

}
