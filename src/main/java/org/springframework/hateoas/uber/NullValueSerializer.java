package org.springframework.hateoas.uber;

import java.io.IOException;

import org.springframework.hateoas.uber.UberNode.NullValue;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.NonTypedScalarSerializerBase;

public class NullValueSerializer extends NonTypedScalarSerializerBase<NullValue> {

	protected NullValueSerializer() {
		super(NullValue.class);
	}

	@Override
	public void serialize(NullValue value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeNull();
	}

}
