package org.springframework.hateoas.sample;

import java.util.Arrays;
import java.util.List;

import org.springframework.hateoas.sample.SamplePerson.Gender;
import org.springframework.hateoas.sample.SamplePerson.Sport;

public class SamplePersonAccess {
	
	private static SamplePerson person = new SamplePerson();
	static {
		person.setId(1234L);
		person.setFirstname("Bilbo");
		person.setLastname("Baggins");
		person.setGender(Gender.MALE);
		person.setSports(new Sport[] { Sport.STEALING, Sport.SMOKING, Sport.DRAGON_QUEST });
	}
	
	SamplePerson getPerson(Long personId) {
		return person;
	}
	
	SamplePerson getPerson(String name) {
		return person;
	}
	
	SamplePerson getPerson(List<String> attr) {
		return person;
	}
	
	SamplePerson getPersonInMood(String mood) {
		return person;
	}
	
	public List<String> getPossibleDetails(Gender gender) {
		List<String> ret; 
		if (Gender.MALE.equals(gender)) {
			ret = Arrays.asList("beard", "afterShave", "noseHairTrimmer");
		} else {
			ret = Arrays.asList("perfume", "lipstick", "makeupRemover");
		}
		return ret;
	}
}
