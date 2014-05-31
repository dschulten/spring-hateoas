package org.springframework.hateoas.uber;

import org.springframework.web.bind.annotation.RequestMethod;

public enum UberAction {

	/** POST */
	APPEND(RequestMethod.POST),
	/** PATCH */
	PARTIAL(RequestMethod.PATCH),
	/** GET */
	READ(RequestMethod.GET),
	/** DELETE */
	REMOVE(RequestMethod.DELETE),
	/** PUT */
	REPLACE(RequestMethod.PUT);

	public final RequestMethod httpMethod;

	private UberAction(RequestMethod method) {
		this.httpMethod = method;
	}

	/**
	 * Maps given request method to uber action, GET will be mapped as null since it is the default.
	 * @param method to map
	 * @return action, or null for GET
	 */
	public static UberAction forRequestMethod(RequestMethod method) {
		if (RequestMethod.GET == method) {
			return null;
		}
		for (UberAction action : UberAction.values()) {
			if (action.httpMethod == method) {
				return action;
			}
		}
		throw new IllegalArgumentException("unsupported method: " + method);
	}
}
