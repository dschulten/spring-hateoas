package org.springframework.hateoas.uber;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.core.JsonPathLinkDiscoverer;

public class UberLinkDiscoverer extends JsonPathLinkDiscoverer {

	public UberLinkDiscoverer() {
		super("$_links..%s.href", MediaTypes.UBER_JSON);
	}

}
