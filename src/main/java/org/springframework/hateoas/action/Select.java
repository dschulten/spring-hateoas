package org.springframework.hateoas.action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* Specifies possible values for an argument on a controller method.
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Select {

	/**
	 * Allows to pass String arguments to the Options implementation. By default, a String array can be used to define
	 * possible values, since the default Options implementation is {@link StringOptions}
	 * 
	 * @return
	 */
	String[] value() default {};

	/**
	 * Specifies an implementation of the {@link Options} interface which provides possible values.
	 * 
	 * @return
	 */
	Class<? extends Options> options() default StringOptions.class;

	/**
	 * When getting possible values from {@link Options}, pass the arguments having these names.
	 * 
	 * @return
	 */
	String[] args() default {};

}
