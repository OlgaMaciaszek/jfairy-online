package io.codearte.jFairyOnline.repositories;

import java.util.Optional;

import io.codearte.jFairyOnline.model.LastName;
import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.Language;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
public interface LastNameRepository extends MongoRepository<LastName, String> {

	Optional<LastName> findFirstByValueAndLanguageAndGender(String value, Language language, Gender gender);
}
