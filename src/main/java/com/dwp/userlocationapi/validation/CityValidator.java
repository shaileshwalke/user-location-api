package com.dwp.userlocationapi.validation;

import com.dwp.userlocationapi.config.ApplicationProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@EnableConfigurationProperties(value = ApplicationProps.class)
public class CityValidator implements ConstraintValidator<ValidCityConstraint, String> {

    @Autowired
    private ApplicationProps applicationProps;

    @Override
    public void initialize(ValidCityConstraint constraintAnnotation) {
        // initialize constraintAnnotation
    }

    @Override
    public boolean isValid(String  city, ConstraintValidatorContext ctx) {
        return StringUtils.hasText(city) && applicationProps.getCityList().containsKey(city) ;
    }
}
