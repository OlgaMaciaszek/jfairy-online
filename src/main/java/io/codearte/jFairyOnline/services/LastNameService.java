package io.codearte.jFairyOnline.services;

import java.util.Optional;

import io.codearte.jFairyOnline.model.LastName;
import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.Language;
import io.codearte.jFairyOnline.repositories.LastNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import static io.codearte.jFairyOnline.model.enums.Gender.FEMALE;
import static io.codearte.jFairyOnline.model.enums.Gender.MALE;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
@Service
public class LastNameService {

	private static final Logger LOG = LoggerFactory.getLogger(LastNameService.class);

	private final LastNameRepository lastNameRepository;

	public LastNameService(LastNameRepository lastNameRepository) {
		this.lastNameRepository = lastNameRepository;
	}

	void processFemale(String value, Language language) {
		process(value, language, FEMALE);
	}

	private void process(String value, Language language, Gender gender) {
		Optional<LastName> lastNameOpt = lastNameRepository.findFirstByValueAndLanguageAndGender(value, language, gender);
		if (lastNameOpt.isPresent()) {
			LOG.warn("Last name {} already exists for language: {} and gender: {}",
					value, language, gender);
			return;
		}
		LastName name = new LastName(language, gender, value);
		lastNameRepository.save(name);
	}

	void processMale(String value, Language language) {
		process(value, language, MALE);
	}
}
