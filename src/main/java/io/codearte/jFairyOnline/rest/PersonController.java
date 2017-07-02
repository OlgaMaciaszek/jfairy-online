package io.codearte.jFairyOnline.rest;

import java.util.Set;

import io.codearte.jFairyOnline.services.PersonService;
import io.codearte.jfairy.producer.person.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	Set<Person> persons(@RequestParam(value = "lang", defaultValue = "EN") String languageTag) {
		return personService.getPersons(languageTag);
	}

	@GetMapping(value = "/{number}")
	Set<Person> persons(@PathVariable int number, @RequestParam(value = "lang", defaultValue = "EN") String languageTag) {
		return personService.getPersons(languageTag, number);
	}


}
