package org.springframework.hateoas.action;

/**
 * Specifies explicit HTML5 input types.
 * 
 * @author Dietrich Schulten
 * 
 */
public enum Type {
	/** Determine input type text or number automatically, depending on the annotated parameter */
	AUTO(null),
	/** input type text */
	TEXT("text"),
	/** input type hidden */
	HIDDEN("hidden"),
	/** input type password */
	PASSWORD("password"), COLOR("color"), DATE("date"), DATETIME("datetime"), DATETIME_LOCAL("datetime-local"), EMAIL(
			"email"), MONTH("month"), NUMBER("number"), RANGE("range"), SEARCH("search"), TEL("tel"), TIME("time"), URL("url"), WEEK(
			"week");

	private String value;

	Type(String value) {
		this.value = value;
	}

	/**
	 * Returns the correct html input type string value.
	 */
	public String toString() {
		return value;
	}

}
