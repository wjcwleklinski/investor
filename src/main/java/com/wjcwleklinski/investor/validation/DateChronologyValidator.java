package com.wjcwleklinski.investor.validation;


import org.apache.commons.beanutils.PropertyUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateChronologyValidator implements ConstraintValidator<DateChronology, Object> {

    private String startDateVar;
    private String endDateVar;

    @Override
    public void initialize(DateChronology constraintAnnotation) {
        startDateVar = constraintAnnotation.startDate();
        endDateVar = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        try {
            LocalDate startDate = (LocalDate) PropertyUtils.getProperty(o, startDateVar);
            LocalDate endDate = (LocalDate) PropertyUtils.getProperty(o, endDateVar);
            return startDate.isBefore(endDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
