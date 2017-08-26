package io.codearte.jFairyOnline.services;

import java.util.Set;

import io.codearte.jFairyOnline.services.fairy.FairyProvider;
import io.codearte.jFairyOnline.services.validation.LimitValidator;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2016-12-27
 */
@Service
public class PersonService {

	private final FairyProvider fairyProvider;
	private final LimitValidator limitValidator;

	public PersonService(FairyProvider fairyProvider, LimitValidator limitValidator) {
		this.fairyProvider = fairyProvider;
		this.limitValidator = limitValidator;
	}

	public Set<Person> getPersons(String languageTag, int count) {
		limitValidator.validate(count);
		Fairy fairy = fairyProvider.getFairy(languageTag);
		return range(0, count).mapToObj(num -> fairy.person()).collect(toSet());
	}
}
