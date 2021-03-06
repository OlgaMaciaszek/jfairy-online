package io.codearte.jFairyOnline.web;

import java.util.Set;

import io.codearte.jFairyOnline.services.PersonService;
import io.codearte.jfairy.producer.person.Person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Olga Maciaszek-Sharma
 * @since 7/2/2017
 */
@Controller
@RequestMapping("/persons")
public class PersonController {

	private static final String PERSONS = "persons";
	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	public String persons(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                      @RequestParam(value = "count", defaultValue = "100") int count,
	                      @RequestParam(value = "personTableConfig", defaultValue = "empty") String[] config,
	                      Model model) {
		Set<Person> persons = personService.getPersons(languageTag, count);
		model.addAttribute(PERSONS, persons);
		model.addAttribute("personTableConfig", config);
		return PERSONS;
	}
}
