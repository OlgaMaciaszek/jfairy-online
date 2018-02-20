package io.codearte.jFairyOnline.services

import io.codearte.jFairyOnline.model.DataRecord
import io.codearte.jFairyOnline.repositories.DataRecordRepository
import spock.lang.Specification
import spock.lang.Subject

import static io.codearte.jFairyOnline.model.enums.Gender.FEMALE
import static io.codearte.jFairyOnline.model.enums.JFairyDataKey.FIRST_NAME
import static io.codearte.jFairyOnline.model.enums.Language.EN
import static io.codearte.jFairyOnline.services.ValueService.MISSING_VALUE_PLACEHOLDER
import static java.util.Optional.empty
import static java.util.Optional.of

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
class ValueServiceSpec extends Specification {

	private static final String VALUE = 'value'
	private static final String FIRST_NAMES_KEY = 'firstNames'

	@Subject
	ValueService valueService

	DataRecordRepository dataRecordRepository

	def setup() {
		dataRecordRepository = Mock(DataRecordRepository)
		valueService = new ValueService(dataRecordRepository)
	}

	def 'should get value by jFairy data key and language'() {
		when:
			valueService.getValue(FIRST_NAMES_KEY, EN)
		then:
			1 * dataRecordRepository
					.findFirstByJFairyDataKeyAndLanguage(FIRST_NAME, EN) >> of(dataRecord())
	}

	def 'should return placeholder when value not found by jFairy data key and language'() {
		when:
			String value = valueService.getValue(FIRST_NAMES_KEY, EN)
		then:
			1 * dataRecordRepository.findFirstByJFairyDataKeyAndLanguage(FIRST_NAME, EN) >> empty()
			value == MISSING_VALUE_PLACEHOLDER
	}

	def 'should get value list by jFairy data key and language'() {
		when:
			valueService.getValues(FIRST_NAMES_KEY, EN)
		then:
			1 * dataRecordRepository.getAllByJFairyDataKeyAndLanguage(FIRST_NAME, EN) >> Arrays.asList(dataRecord())
	}

	def 'should get value list by jFairy data key, gender and language'() {
		when:
			valueService.getValues(FIRST_NAMES_KEY, FEMALE.name(), EN)
		then:
			1 * dataRecordRepository.getAllByJFairyDataKeyAndGenderAndLanguage(FIRST_NAME, FEMALE, EN) >> Arrays
					.asList(dataRecord())
	}

	private static DataRecord dataRecord() {
		new DataRecord(VALUE, EN, FIRST_NAME, FEMALE)
	}

}
