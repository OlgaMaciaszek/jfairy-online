package io.codearte.jFairyOnline.repositories;

import java.util.Optional;

import io.codearte.jFairyOnline.model.Name;
import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.Language;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
public interface NameRepository extends MongoRepository<Name, String> {

	Optional<Name> findFirstByValueAndLanguageAndGender(String value, Language language, Gender gender);
}
