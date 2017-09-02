package io.codearte.jFairyOnline.repositories;

import java.util.Optional;

import io.codearte.jFairyOnline.model.DataRecord;
import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.JFairyDataKey;
import io.codearte.jFairyOnline.model.enums.Language;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
public interface DataRecordRepository extends MongoRepository<DataRecord, String> {

	Optional<DataRecord> findFirstByValueAndLanguageAndJFairyDataKey(String value, Language language,
	                                                                 JFairyDataKey jFairyDataKey);

	Optional<DataRecord> findFirstByValueAndLanguageAndJFairyDataKeyAndGender(String value, Language language,
	                                                                          JFairyDataKey jFairyDataKey,
	                                                                          Gender gender);
}
