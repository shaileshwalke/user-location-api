package com.dwp.userlocationapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = com.dwp.userlocationapi.validation.CityValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCityConstraint {

    String message() default "City you have passed is currently not supported by this service";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
