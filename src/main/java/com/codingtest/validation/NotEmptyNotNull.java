package com.codingtest.validation;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by arahansa on 2015-06-29.
 */
@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NotEmptyNotNullValidator.class)
@Documented
public @interface NotEmptyNotNull {

    String message() default "NotEmptyNotNull Constraint";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String nickFieldName() default "";

    String subjectFieldName() default "";

}
