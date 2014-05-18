package org.springframework.hateoas.uber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;

public class UberActionTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testForRequestMethodGet() throws Exception {
		assertNull(UberAction.forRequestMethod(RequestMethod.GET));
	}

	@Test
	public void testForRequestMethodPost() throws Exception {
		assertEquals(UberAction.APPEND, UberAction.forRequestMethod(RequestMethod.POST));
	}

	@Test
	public void testForRequestMethodPut() throws Exception {
		assertEquals(UberAction.REPLACE, UberAction.forRequestMethod(RequestMethod.PUT));
	}
	
	@Test
	public void testForRequestMethodDelete() throws Exception {
		assertEquals(UberAction.REMOVE, UberAction.forRequestMethod(RequestMethod.DELETE));
	}

	@Test
	public void testForRequestMethodPatch() throws Exception {
		assertEquals(UberAction.PARTIAL, UberAction.forRequestMethod(RequestMethod.PATCH));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testForRequestMethodOptions() throws Exception {
		UberAction.forRequestMethod(RequestMethod.OPTIONS);
	}

}
