package io.codearte.jFairyOnline.services;

import java.util.Optional;

import io.codearte.jFairyOnline.model.City;
import io.codearte.jFairyOnline.model.enums.Language;
import io.codearte.jFairyOnline.repositories.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
@Service
public class CityService {

	private static final Logger LOG = LoggerFactory.getLogger(CityService.class);

	private final CityRepository cityRepository;

	public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	void process(String value, Language language) {
		Optional<City> cityOpt = cityRepository.findFirstByValueAndLanguage(value, language);
		if (cityOpt.isPresent()) {
			LOG.warn("City {} already exists for language: {}",
					value, language);
			return;
		}
		City city = new City(language, value);
		cityRepository.save(city);
	}
}
