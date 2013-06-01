package org.springframework.hateoas.sample;

import org.springframework.hateoas.action.Options;
import org.springframework.hateoas.sample.SamplePerson.Gender;

public class DetailOptions implements Options {

	
	private static SamplePersonAccess access;

	@Override
	public Object[] get(String[] value, Object... args) {
		Long personId = (Long)args[0];
		SamplePerson person = access.getPerson(personId);
		Gender gender = person.getGender();
		return access.getPossibleDetails(gender).toArray();
	}

	public SamplePersonAccess getAccess() {
		return access;
	}

	public static void setAccess(SamplePersonAccess access) {
		DetailOptions.access = access;
	}

}
