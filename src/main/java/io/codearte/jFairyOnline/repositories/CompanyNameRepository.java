package io.codearte.jFairyOnline.repositories;

import java.util.Optional;

import io.codearte.jFairyOnline.model.CompanyName;
import io.codearte.jFairyOnline.model.enums.Language;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
public interface CompanyNameRepository extends MongoRepository<CompanyName, String> {

	Optional<CompanyName> findFirstByValueAndLanguage(String value, Language language);
}
