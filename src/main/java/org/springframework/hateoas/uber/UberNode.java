package org.springframework.hateoas.uber;

import java.util.List;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "name", "rel", "url", "action", "transclude", "model", "sending", "accepting", "value",
		"data" })
public class UberNode extends AbstractUberNode {

	@JsonSerialize(using = NullValueSerializer.class)
	static class NullValue {

	}

	/**
	 * Uses {@link NullValueSerializer} to render undefined values as null.
	 */
	public static final NullValue NULL_VALUE = new NullValue();

	private String id;

	private String name;

	private List<String> rel;

	private String url;

	private UberAction action;

	/**
	 * Defines if the {@link #url} content should be embedded within the currently loaded document or treated as a
	 * navigation to a new document. Default is false (navigate).
	 */
	private Boolean transclude;

	/** RFC6570 URI template */
	private String model;

	/** Default is formUrlEncoded */
	private List<MediaType> sending;

	/** Default is the mediatype of this data. */
	private List<MediaType> accepting;// = Arrays.asList(mediaType);

	/** One of number, string, false, true, null */
	private Object value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getRel() {
		return rel;
	}

	public void setRel(List<String> rel) {
		this.rel = rel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UberAction getAction() {
		return action;
	}

	public void setAction(UberAction action) {
		this.action = action;
	}

	public Boolean isTransclude() {
		return transclude;
	}

	public void setTransclude(Boolean transclude) {
		this.transclude = transclude;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<MediaType> getSending() {
		return sending;
	}

	public void setSending(List<MediaType> sending) {
		this.sending = sending;
	}

	public List<MediaType> getAccepting() {
		return accepting;
	}

	public void setAccepting(List<MediaType> accepting) {
		this.accepting = accepting;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "UberNode [name=" + name + ", value=" + value + ", data=" + data + ", id=" + id + ", rel=" + rel + ", url="
				+ url + ", action=" + action + ", transclude=" + transclude + ", model=" + model + ", sending=" + sending
				+ ", accepting=" + accepting + "]";
	}

}
