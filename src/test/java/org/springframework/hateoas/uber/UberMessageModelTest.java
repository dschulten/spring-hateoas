package org.springframework.hateoas.uber;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class UberMessageModelTest {

	private UberMessageModel uberMessageModel;

	@Before
	public void setUp() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Doe");
		uberMessageModel = new UberMessageModel(map);
	}

	@Test
	public void testUberMessageModel() throws Exception {
		assertEquals("1.0", uberMessageModel.getVersion());
		assertEquals(1, uberMessageModel.getData().size());
		assertEquals(0, uberMessageModel.getError().size());
	}

}
