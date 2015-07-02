package com.codingtest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by arahansa on 2015-06-29.
 */
public class NotEmptyNotNullValidator  implements ConstraintValidator<NotEmptyNotNull, Object> {
    private String nickFieldName;
    private String subjectFieldName;

    @Override
    public void initialize(NotEmptyNotNull constraintAnnotation) {
        nickFieldName = constraintAnnotation.nickFieldName();
        subjectFieldName = constraintAnnotation.subjectFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        try {
            return checkFieldsAreValid(value, context);
        }catch (Exception ex) {
            throw new RuntimeException("Exception occurred during validation", ex);
        }
    }

    private boolean checkFieldsAreValid(Object value, ConstraintValidatorContext context) throws NoSuchFieldException, IllegalAccessException {
        boolean passwordWordFieldsAreValid = true;
        String nick = (String) ValidatorUtil.getFieldValue(value, nickFieldName);
        if (isNullOrEmpty(nick)) {
            ValidatorUtil.addValidationError(nickFieldName, context);
            passwordWordFieldsAreValid = false;
        }
        String subject = (String) ValidatorUtil.getFieldValue(value, subjectFieldName);
        if (isNullOrEmpty(subject)) {
            ValidatorUtil.addValidationError(subjectFieldName, context);
            passwordWordFieldsAreValid = false;
        }
        return passwordWordFieldsAreValid;
    }

    private boolean isNullOrEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }
}
