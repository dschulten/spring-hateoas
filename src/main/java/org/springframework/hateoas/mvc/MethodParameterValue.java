package org.springframework.hateoas.mvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.hateoas.action.ActionDescriptor;
import org.springframework.hateoas.action.Input;
import org.springframework.hateoas.action.Options;
import org.springframework.hateoas.action.Select;
import org.springframework.hateoas.action.Type;
import org.springframework.hateoas.mvc.AnnotatedParametersParameterAccessor.BoundMethodParameter;

/**
 * Holds a method parameter value together with {@link MethodParameter} information.
 * 
 * @author Dietrich Schulten
 * 
 */
public class MethodParameterValue /* extends MethodParameter*/ {

	private MethodParameter methodParameter;
	private Object value;
	private String formattedValue;
	private Boolean arrayOrCollection = null;
	private Input inputAnnotation;
	private Map<String, Object> inputConditions = new HashMap<String, Object>();
	private int upToCollectionItems = 3;

	public MethodParameterValue(MethodParameter methodParameter, Object value, String formattedValue,
			List<BoundMethodParameter> boundMethodParameters) {
		//super(original);
		this.methodParameter = methodParameter;
		this.value = value;
		this.formattedValue = formattedValue;
		this.inputAnnotation = methodParameter.getParameterAnnotation(Input.class);
		if (inputAnnotation != null) {
			putInputCondition("min", Integer.MIN_VALUE, inputAnnotation.min());
			putInputCondition("max", Integer.MAX_VALUE, inputAnnotation.max());
			putInputCondition("step", 0, inputAnnotation.step());
			this.upToCollectionItems  = inputAnnotation.upTo();
		}
		
	}

	public int getUpToCollectionItems() {
		return upToCollectionItems;
	}

	private void putInputCondition(String key, int defaultValue, int value) {
		if (value != defaultValue) {
			inputConditions.put(key, value);
		}

	}

	/**
	 * The value of the parameter at invocation time.
	 * 
	 * @return value, may be null
	 */
	public Object getCallValue() {
		return value;
	}

	/**
	 * The value of the parameter at invocation time, formatted according to conversion configuration.
	 * 
	 * @return value, may be null
	 */
	public String getCallValueFormatted() {
		return formattedValue;
	}

	public Type getInputFieldType() {
		final Type ret;
		if (inputAnnotation == null || inputAnnotation.value() == Type.AUTO) {
			Class<?> parameterType = getParameterType();
			if (Number.class.isAssignableFrom(parameterType)) {
				ret = Type.NUMBER;
			} else {
				ret = Type.TEXT;
			}
		} else {
			ret = inputAnnotation.value();
		}
		return ret;
	}

	public boolean hasInputConditions() {
		return !inputConditions.isEmpty();
	}


	public Object[] getPossibleValues(ActionDescriptor actionDescriptor) {
		// TODO: other sources of possible values, e.g. max, min, step
		try {
			Class<?> parameterType = getParameterType();
			Object[] possibleValues;
			Class<?> nested;
			if (Enum[].class.isAssignableFrom(parameterType)) {
				possibleValues = parameterType.getComponentType().getEnumConstants();
			} else if (Enum.class.isAssignableFrom(parameterType)) {
				possibleValues = parameterType.getEnumConstants();
			} else if (Collection.class.isAssignableFrom(parameterType)
					&& Enum.class.isAssignableFrom(nested = TypeDescriptor.nested(methodParameter, 1).getType())) {
				possibleValues = nested.getEnumConstants();
			} else {
				Select select = methodParameter.getParameterAnnotation(Select.class);
				if (select != null) {
					Class<? extends Options> options = select.options();
					Options instance = options.newInstance();
					List<Object> from = new ArrayList<Object>();
					for (String paramName : select.args()) {
						MethodParameterValue parameterValue = actionDescriptor.getParameterValue(paramName);
						if (parameterValue != null) {
							from.add(parameterValue.getCallValue());
						}
					}

					Object[] args = from.toArray();
					possibleValues = instance.get(select.value(), args);
				} else {
					possibleValues = new Object[0];
				}
			}
			return possibleValues;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isArrayOrCollection() {
		if (arrayOrCollection == null) {
			Class<?> parameterType = getParameterType();
			arrayOrCollection = (parameterType.isArray() || Collection.class.isAssignableFrom(parameterType));
		}
		return arrayOrCollection;
	}

	public Object[] getCallValues() {
		Object[] callValues;
		if (!isArrayOrCollection()) {
			throw new UnsupportedOperationException("parameter is not an array or collection");
		}
		Object callValue = getCallValue();
		if (callValue == null) {
			callValues = new Object[0];
		} else {
			Class<?> parameterType = getParameterType();
			if (parameterType.isArray()) {
				callValues = (Object[]) callValue;
			} else {
				callValues = ((Collection<?>) callValue).toArray();
			}
		}
		return callValues;
	}

	Class<?> getParameterType() {
		return methodParameter.getParameterType();
	}

	public Map<String, Object> getInputConditions() {
		return inputConditions;
	}

}
