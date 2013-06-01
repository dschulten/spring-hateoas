package org.springframework.hateoas.sample;

import static org.springframework.hateoas.mvc.ControllerActionBuilder.actionFor;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.action.ActionDescriptor;
import org.springframework.hateoas.action.Input;
import org.springframework.hateoas.action.Select;
import org.springframework.hateoas.action.Type;
import org.springframework.hateoas.sample.SamplePerson.Gadget;
import org.springframework.hateoas.sample.SamplePerson.Gender;
import org.springframework.hateoas.sample.SamplePerson.Sport;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/people")
public class SamplePersonController {

	@Autowired
	SamplePersonAccess access;

	@RequestMapping(value = "/customerById")
	public HttpEntity<ActionDescriptor> showPersonAction() {
		long defaultPersonId = 1234L;
		ActionDescriptor action = actionFor(methodOn(this.getClass()).showPerson(defaultPersonId), "searchPerson");
		return new HttpEntity<ActionDescriptor>(action);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET, params = { "personId" })
	public HttpEntity<SamplePersonResource> showPerson(@RequestParam @Input(min = 0, max = 9999) Long personId) {

		SamplePersonResourceAssembler assembler = new SamplePersonResourceAssembler();
		SamplePersonResource resource = assembler.toResource(access.getPerson(personId));
		resource.add(linkTo(methodOn(this.getClass()).editPersonAction(personId)).withSelfRel());

		return new HttpEntity<SamplePersonResource>(resource);
	}

	@RequestMapping(value = "/customerByName")
	public HttpEntity<ActionDescriptor> showPersonByNameAction() {
		String defaultName = "Bombur";
		ActionDescriptor action = actionFor(methodOn(this.getClass()).showPerson(defaultName), "searchPerson");
		return new HttpEntity<ActionDescriptor>(action);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST, params = { "name" })
	public HttpEntity<SamplePersonResource> showPerson(@RequestParam String name) {

		SamplePersonResourceAssembler assembler = new SamplePersonResourceAssembler();
		SamplePersonResource resource = assembler.toResource(access.getPerson(name));

		return new HttpEntity<SamplePersonResource>(resource);
	}

	@RequestMapping(value = "/customerByAttribute")
	public HttpEntity<ActionDescriptor> showPersonByAttributesAction() {
		String[] defaultAttributes = { "hungry", "tired" };
		ActionDescriptor action = actionFor(methodOn(this.getClass()).showPerson(Arrays.asList(defaultAttributes)),
				"searchPerson");
		return new HttpEntity<ActionDescriptor>(action);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET, params = { "attr" })
	public HttpEntity<SamplePersonResource> showPerson(
			@RequestParam @Select({ "hungry", "thirsty", "tired" }) List<String> attr) {

		SamplePersonResourceAssembler assembler = new SamplePersonResourceAssembler();
		SamplePersonResource resource = assembler.toResource(access.getPerson(attr));

		return new HttpEntity<SamplePersonResource>(resource);
	}

	@RequestMapping("/customer/{personId}/details")
	public HttpEntity<ActionDescriptor> showPersonDetailsAction(@PathVariable("personId") Long personId) {
		ActionDescriptor action = actionFor(methodOn(this.getClass()).showPersonDetails(personId, null), "showDetails");
		return new HttpEntity<ActionDescriptor>(action);
	}

	@RequestMapping(value = "/customer/{personId}/details", params = { "detail" })
	public HttpEntity<Resource<Map<String, String>>> showPersonDetails(@PathVariable @Input(Type.HIDDEN) Long personId,
			@RequestParam("detail") @Select(options = DetailOptions.class, args = "personId") List<String> detail) {
		Map<String, String> map = new HashMap<String, String>();
		Resource<Map<String, String>> resource = new Resource<Map<String, String>>(map);

		return new HttpEntity<Resource<Map<String, String>>>(resource);
	}

	@RequestMapping("/customer/{personId}/numbers")
	public HttpEntity<ActionDescriptor> showListOfIntAction(@PathVariable("personId") Long personId) {
		ActionDescriptor action = actionFor(methodOn(this.getClass()).showListOfInt(personId, Arrays.asList(42)),
				"showNumbers");
		return new HttpEntity<ActionDescriptor>(action);
	}

	@RequestMapping(value = "/customer/{personId}/numbers", params = { "number" })
	public HttpEntity<Resource<List<Integer>>> showListOfInt(@PathVariable @Input(Type.HIDDEN) Long personId,
			@RequestParam("number") List<Integer> numbers) {
		Resource<List<Integer>> resource = new Resource<List<Integer>>(numbers);
		return new HttpEntity<Resource<List<Integer>>>(resource);
	}

	@RequestMapping(value = "/customerByMood")
	public HttpEntity<ActionDescriptor> showPersonByMoodAction() {
		String defaultAttribute = "angry";
		ActionDescriptor action = actionFor(methodOn(this.getClass()).showPersonByMood(defaultAttribute), "searchPerson");
		return new HttpEntity<ActionDescriptor>(action);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET, params = { "mood" })
	public HttpEntity<SamplePersonResource> showPersonByMood(@RequestParam @Select({ "angry", "happy", "grumpy", "bored",
			"ecstatic" }) String mood) {

		SamplePersonResourceAssembler assembler = new SamplePersonResourceAssembler();
		SamplePersonResource resource = assembler.toResource(access.getPersonInMood(mood));

		return new HttpEntity<SamplePersonResource>(resource);
	}

	@RequestMapping(value = "/customer/{personId}/editor")
	public HttpEntity<ActionDescriptor> editPersonAction(@PathVariable Long personId) {
		// The updatePerson resource accepts a PUT.
		SamplePerson person = access.getPerson(personId);
		ActionDescriptor action = actionFor(
				methodOn(this.getClass()).updatePerson(personId, person.getFirstname(), person.getLastname(),
						person.getGender(), person.getSports(), person.getGadgets()), "updatePerson");

		return new HttpEntity<ActionDescriptor>(action);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.PUT, params = { "personId", "firstname", "lastname",
			"gender", "sports", "gadgets" })
	public HttpEntity<SamplePersonResource> updatePerson(@RequestParam @Input(Type.HIDDEN) Long personId,
			@RequestParam String firstname, @RequestParam String lastname, @RequestParam Gender gender,
			@RequestParam Sport[] sports, @RequestParam List<Gadget> gadgets) {
		SamplePerson person = access.getPerson(personId);
		person.setFirstname(firstname);
		person.setLastname(lastname);
		person.setGender(gender);
		person.setSports(sports);
		person.setGadgets(gadgets);
		SamplePersonResourceAssembler assembler = new SamplePersonResourceAssembler();
		SamplePersonResource resource = assembler.toResource(person);

		return new HttpEntity<SamplePersonResource>(resource);
	}

}
