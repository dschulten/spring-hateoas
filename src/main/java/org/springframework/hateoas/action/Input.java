package org.springframework.hateoas.action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allows to define the input characteristics for an input field. E.g. this is useful to specify possible value ranges
 * as in <code>&#64;Input(min=0)</code>, and it can also be used to mark a method parameter as
 * <code>&#64;Input(Type.HIDDEN)</code>, e.g. when used as a POST parameter for a form which is not supposed to be
 * changed by the client.
 * 
 * @author Dietrich Schulten
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Input {

	int ANY = -1;

	/** Input type. With the default type AUTO the type will be number or text, depending on the parameter type. */
	Type value() default Type.AUTO;

	int max() default Integer.MAX_VALUE;

	int min() default Integer.MIN_VALUE;

	int step() default 0;

	/**
	 * Specifies how many items are allowed if the annotated parameter is a Collection. The value {@link Input#ANY} means,
	 * there are no restrictions.
	 */
	int upTo() default 3;

}
