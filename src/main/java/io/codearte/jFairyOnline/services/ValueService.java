package io.codearte.jFairyOnline.services;

import java.util.List;
import java.util.Optional;

import io.codearte.jFairyOnline.model.DataRecord;
import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.JFairyDataKey;
import io.codearte.jFairyOnline.model.enums.Language;
import io.codearte.jFairyOnline.repositories.DataRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import static io.codearte.jFairyOnline.model.enums.Gender.valueOf;
import static io.codearte.jFairyOnline.model.enums.JFairyDataKey.getByKeyValue;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.capitalize;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
@Service
public class ValueService {

	private static final Logger LOG = LoggerFactory.getLogger(ValueService.class);
	private static final String MISSING_VALUE_PLACEHOLDER = "???";

	private final DataRecordRepository dataRecordRepository;

	public ValueService(DataRecordRepository dataRecordRepository) {
		this.dataRecordRepository = dataRecordRepository;
	}

	public String getValue(String key, Language language) {
		return dataRecordRepository
				.findFirstByJFairyDataKeyAndLanguage(getByKeyValue(key), language).map(DataRecord::getValue)
				.orElse(MISSING_VALUE_PLACEHOLDER);
	}

	public List<String> getValues(String key, Language language) {
		return dataRecordRepository.getAllByJFairyDataKeyAndLanguage(getByKeyValue(key), language)
				.stream().map(DataRecord::getValue).collect(toList());
	}

	public List<String> getValues(String key, String gender, Language language) {
		return dataRecordRepository.getAllByJFairyDataKeyAndGenderAndLanguage(getByKeyValue(key),
				valueOf(gender), language)
				.stream().map(DataRecord::getValue).collect(toList());
	}

	void process(String value, Language language, JFairyDataKey jFairyDataKey, Gender gender) {
		String capitalisedValue = capitalize(value);
		Optional<DataRecord> dataRecordOpt = findExistingDataRecord(capitalisedValue, language, jFairyDataKey, gender);
		if (dataRecordOpt.isPresent()) {
			LOG.warn("City {} already exists for language: {}",
					capitalisedValue, language);
			return;
		}
		DataRecord dataRecord = new DataRecord(capitalisedValue, language, jFairyDataKey, gender);
		dataRecordRepository.save(dataRecord);
	}

	private Optional<DataRecord> findExistingDataRecord(String value, Language language, JFairyDataKey jFairyDataKey, Gender gender) {
		return ofNullable(gender).map(gen -> dataRecordRepository
				.findFirstByValueAndLanguageAndJFairyDataKeyAndGender(value, language, jFairyDataKey, gender))
				.orElseGet(() -> dataRecordRepository
						.findFirstByValueAndLanguageAndJFairyDataKey(value, language, jFairyDataKey));
	}
}
