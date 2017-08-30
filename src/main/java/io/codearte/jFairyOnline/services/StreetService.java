package io.codearte.jFairyOnline.services;

import java.util.Optional;

import io.codearte.jFairyOnline.model.Street;
import io.codearte.jFairyOnline.model.enums.Language;
import io.codearte.jFairyOnline.repositories.StreetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
@Service
public class StreetService {

	private static final Logger LOG = LoggerFactory.getLogger(StreetService.class);

	private final StreetRepository streetRepository;

	public StreetService(StreetRepository streetRepository) {
		this.streetRepository = streetRepository;
	}

	void process(String value, Language language) {
		Optional<Street> streetOpt = streetRepository.findFirstByValueAndLanguage(value, language);
		if (streetOpt.isPresent()) {
			LOG.warn("Street {} already exists for language: {}",
					value, language);
			return;
		}
		Street street = new Street(language, value);
		streetRepository.save(street);
	}
}
