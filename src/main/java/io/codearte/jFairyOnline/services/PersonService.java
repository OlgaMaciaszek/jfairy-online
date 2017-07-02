package io.codearte.jFairyOnline.services;

import java.util.Set;
import java.util.stream.IntStream;

import io.codearte.jFairyOnline.config.JFOProperties;
import io.codearte.jFairyOnline.services.fairy.FairyProvider;
import io.codearte.jFairyOnline.services.validation.LimitValidator;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toSet;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2016-12-27
 */
@Service
public class PersonService {

	private final FairyProvider fairyProvider;
	private final LimitValidator limitValidator;
	private final JFOProperties properties;

	public PersonService(FairyProvider fairyProvider, LimitValidator limitValidator, JFOProperties properties) {
		this.fairyProvider = fairyProvider;
		this.limitValidator = limitValidator;
		this.properties = properties;
	}

	public Set<Person> getPersons(String languageTag) {
		return getPersons(languageTag, properties.getLimit());
	}

	public Set<Person> getPersons(String languageTag, int number) {
		limitValidator.validate(number);
		Fairy fairy = fairyProvider.getFairy(languageTag);
		return IntStream.range(0, number).mapToObj(i -> fairy.person()).collect(toSet());
	}
}
