package io.codearte.jFairyOnline.rest;

import java.util.Set;

import io.codearte.jFairyOnline.config.JFOProperties;
import io.codearte.jFairyOnline.services.PersonService;
import io.codearte.jfairy.producer.person.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2016-12-27
 */
@RestController
@RequestMapping("/rest/persons")
public class PersonController {

	private final PersonService personService;
	private final JFOProperties properties;

	@Autowired
	public PersonController(PersonService personService, JFOProperties properties) {
		this.personService = personService;
		this.properties = properties;
	}

	@RequestMapping(method = RequestMethod.GET)
	Set<Person> persons(@RequestParam(value = "lang", defaultValue = "EN") String languageTag) {
		return personService.getPersons(languageTag, properties.getLimit());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{number}")
	Set<Person> persons(@PathVariable int number, @RequestParam(value = "lang", defaultValue = "EN") String languageTag) {
		return personService.getPersons(languageTag, number);
	}


}
