package org.springframework.hateoas.uber;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("uber")
public class UberMessageModel extends AbstractUberNode {

	private String version = "1.0";

	private List<UberNode> error = new ArrayList<UberNode>();

	public UberMessageModel(Object toWrap) {
		UberUtils.toUberData(this, toWrap);
	}

	public String getVersion() {
		return version;
	}

	@JsonInclude(Include.NON_EMPTY)
	public List<UberNode> getError() {
		return error;
	}

}
