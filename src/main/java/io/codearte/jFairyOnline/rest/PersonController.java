package io.codearte.jFairyOnline.rest;

import java.util.Set;

import io.codearte.jFairyOnline.services.PersonService;
import io.codearte.jfairy.producer.person.Person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2016-12-27
 */
@RestController("restPersonController")
@RequestMapping("/rest/persons")
public class PersonController {

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	Set<Person> persons(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                    @RequestParam(value = "count", defaultValue = "100") int count) {
		return personService.getPersons(languageTag, count);
	}

}
