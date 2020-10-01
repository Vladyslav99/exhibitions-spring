package my.spring.exhibitions.annotation;

import my.spring.exhibitions.validator.HallsAvailableValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = HallsAvailableValidator.class)
@Documented
public @interface HallsAvailable {
    String message() default "{admin_panel.exhibition_event.halls_in_use}\"";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
