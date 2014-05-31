package org.springframework.hateoas.uber;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.core.JsonPathLinkDiscoverer;

public class UberJsonLinkDiscoverer extends JsonPathLinkDiscoverer {

	public UberJsonLinkDiscoverer() {
		super("$_links..%s.href", MediaTypes.UBER_JSON);
	}

}
