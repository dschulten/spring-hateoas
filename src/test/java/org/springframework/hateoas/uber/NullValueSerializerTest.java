package org.springframework.hateoas.uber;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class NullValueSerializerTest {

	private NullValueSerializer nullValueSerializer;
	
	@Mock
	private JsonGenerator jgen;
	@Mock
	private SerializerProvider serializerProvider;

	@Before
	public void setUp() throws Exception {
		nullValueSerializer = new NullValueSerializer();
	}

	@Test
	public void testSerialize() throws Exception {
		nullValueSerializer.serialize(UberNode.NULL_VALUE, jgen, serializerProvider);
		Mockito.verify(jgen).writeNull();
	}

}
