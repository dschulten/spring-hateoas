package org.springframework.hateoas.sample;

import java.util.List;

import org.springframework.hateoas.Identifiable;

public class SamplePerson implements Identifiable<Long> {

	public enum Gender {
		MALE, FEMALE
	}

	public enum Sport {
		SURFING, HIKING, SKIING, RIDING, BIKING, DANCING, STEALING, SMOKING, DRAGON_QUEST
	}

	public enum Gadget {
		RING, SWORD, MITHRIL_SHIRT, LEMBAS_BREAD, EARENDILS_LIGHT, HORN_OF_GONDOR
	}

	private Long id;
	private String firstname;
	private String lastname;
	private Gender gender;
	private Sport[] sports;
	private List<Gadget> gadgets;
	private List<String> details;

	public String getFirstname() {
		return firstname;
	}

	public void setSports(Sport[] sports) {
		this.sports = sports;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {
		return gender;
	}

	public Sport[] getSports() {
		return sports;
	}

	public List<Gadget> getGadgets() {
		return gadgets;
	}

	public void setGadgets(List<Gadget> gadgets) {
		this.gadgets = gadgets;
	}

	public List<String> getDetails() {		
		return details;
	}
	
	public void addDetail(String detail) {
		details.add(detail);
	}

}
