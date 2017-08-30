package io.codearte.jFairyOnline.repositories;

import java.util.Optional;

import io.codearte.jFairyOnline.model.City;
import io.codearte.jFairyOnline.model.enums.Language;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
public interface CityRepository extends MongoRepository<City, String> {

	Optional<City> findFirstByValueAndLanguage(String value, Language language);
}
