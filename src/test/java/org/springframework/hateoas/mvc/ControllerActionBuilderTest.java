package org.springframework.hateoas.mvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;
import org.springframework.hateoas.TestUtils;
import org.springframework.hateoas.action.ActionDescriptor;
import org.springframework.hateoas.sample.SamplePerson.Gender;
import org.springframework.hateoas.sample.SamplePersonController;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ControllerActionBuilderTest extends TestUtils {

  @Test
  public void createsRelativeActionLinkWithMethodLevelAndTypeLevelVariables() throws Exception {
    ActionDescriptor actionDescriptor = ControllerActionBuilder.actionFor(
        methodOn(PersonControllerForAction.class, "region1").showPerson("mike", null), "searchPerson");
    assertEquals("/region/region1/person/mike", actionDescriptor.getRelativeActionLink());
  }
  
  @Test
  public void actionDescriptorIsSerializable() throws JsonGenerationException, JsonMappingException, IOException {
    ActionDescriptor actionDescriptor = ControllerActionBuilder.actionFor(
        methodOn(PersonControllerForAction.class, "region1").showPerson("mike", null), "searchPerson");
    ObjectMapper mapper = new ObjectMapper();
    StringWriter w = new StringWriter();
    mapper.writeValue(w, actionDescriptor);
    System.out.println(w.toString());
  }

  @Test
  public void createsActionDescriptorWithEnum() throws Exception {
    ActionDescriptor actionDescriptor = ControllerActionBuilder.actionFor(methodOn(SamplePersonController.class)
        .updatePerson(123L, "Frodo", "Baggins", Gender.MALE, null, null), "searchPerson");
    assertEquals("/people/customer", actionDescriptor.getRelativeActionLink());
    assertEquals(Gender.class, actionDescriptor.getParameterValue("gender").getParameterType());
  }

  @RequestMapping("/region/{regionId}")
  static class PersonControllerForAction {

    @RequestMapping(value = "/person/{personName}", method = RequestMethod.GET)
    public HttpEntity<?> showPerson(@PathVariable("personName") String personName,
        @RequestParam(value = "personId") Long personId) {
      return null;
    }

  }

}
