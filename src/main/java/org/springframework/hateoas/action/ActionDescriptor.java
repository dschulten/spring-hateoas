package org.springframework.hateoas.action;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.hateoas.mvc.MethodParameterValue;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponents;

/**
 * Describes an HTTP action. Has knowledge about possible request data, e.g. which types and values are suitable for an
 * action. For example, an action descriptor can be used to create a form with select options and typed input fields
 * that calls a Controller method which handles the request built by the form.
 * 
 * @author Dietrich Schulten
 * 
 */
public class ActionDescriptor {

	private UriComponents actionLink;
	private Map<String, MethodParameterValue> requestParams = new LinkedHashMap<String, MethodParameterValue>();
	private RequestMethod httpMethod;
	private String resourceName;
	private Map<String, MethodParameterValue> pathVariables = new LinkedHashMap<String, MethodParameterValue>();

	/**
	 * Creates an action descriptor.
	 * 
	 * @param resourceName can be used by the action representation, e.g. to identify the action using a form name.
	 * @param actionLink to which the action is submitted
	 * @param requestMethod used during submit
	 */
	public ActionDescriptor(String resourceName, UriComponents actionLink, RequestMethod requestMethod) {
		this.actionLink = actionLink;
		this.httpMethod = requestMethod;
		this.resourceName = resourceName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public RequestMethod getHttpMethod() {
		return httpMethod;
	}

	public String getActionLink() {
		return actionLink.toString();
	}

	public String getRelativeActionLink() {
		return actionLink.getPath();
	}

//	public Map<String, MethodParameterValue> getRequestParams() {
//		return requestParams;
//	}
	public Collection<String> getRequestParamNames() {
		return requestParams.keySet();
	}

	public void addRequestParam(String key, MethodParameterValue methodParameterValue) {
		requestParams.put(key, methodParameterValue);
	}

	public void addPathVariable(String key, MethodParameterValue methodParameterValue) {
		pathVariables.put(key, methodParameterValue);
	}

	public MethodParameterValue getParameterValue(String name) {
		MethodParameterValue ret = requestParams.get(name);
		if (ret == null) {
			ret = pathVariables.get(name);
		}
		return ret;
	}

}
