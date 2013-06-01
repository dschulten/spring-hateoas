package org.springframework.hateoas.action;

public class StringOptions implements Options {

	/**
	 * Allows to specify possible values for an argument. This allows an {@link ActionDescriptor} to determine possible
	 * values for an action argument.
	 * <p>
	 * The example below defines four possible values for the <code>mood</code> parameter.
	 * 
	 * <pre>
	 * &#0064;RequestMapping(value = "/customer", method = RequestMethod.GET, params = { "mood" })
	 * public HttpEntity<SamplePersonResource> showPersonByMood(
	 *     &#0064;RequestParam &#0064;Select({ "angry", "happy", "grumpy", "bored" })
	 *     String mood) {
	 *     ...
	 * }
	 * </pre>
	 */
	@Override
	public Object[] get(String[] value, Object... args) {
		return value;
	}

}
