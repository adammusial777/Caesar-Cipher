package pl.polsl.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation informing that it is a unit test.
 *
 * @author Adam Musiał
 * @version v1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)

public @interface UnitTest {

    public String value() default "Method of unit test.";
}
