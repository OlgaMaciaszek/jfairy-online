package io.codearte.jFairyOnline.services;

import java.util.Optional;

import io.codearte.jFairyOnline.model.Name;
import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.Language;
import io.codearte.jFairyOnline.repositories.NameRepository;
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
public class NameService {

	private static final Logger LOG = LoggerFactory.getLogger(NameService.class);

	private final NameRepository nameRepository;

	public NameService(NameRepository nameRepository) {
		this.nameRepository = nameRepository;
	}

	void processFemale(String value, Language language) {
		process(value, language, FEMALE);
	}

	private void process(String value, Language language, Gender gender) {
		Optional<Name> nameOpt = nameRepository.findFirstByValueAndLanguageAndGender(value, language, gender);
		if (nameOpt.isPresent()) {
			LOG.warn("Name {} already exists for language: {} and gender: {}",
					value, language, gender);
			return;
		}
		Name name = new Name(language, gender, value);
		nameRepository.save(name);
	}

	void processMale(String value, Language language) {
		process(value, language, MALE);
	}
}
