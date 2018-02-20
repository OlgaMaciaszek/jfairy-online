package io.codearte.jFairyOnline.services.validation

import io.codearte.jFairyOnline.exceptions.IllegalLanguageTag
import spock.lang.Specification
import spock.lang.Subject

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
class LanguageTagValidatorSpec extends Specification {

	@Subject
	LanguageTagValidator languageTagValidator = new LanguageTagValidator()

	def 'should throw exception when invalid language tag'() {
		when:
			languageTagValidator.validate('XYZ')
		then:
			thrown IllegalLanguageTag
	}

	def 'should not throw exception when valid language tag'() {
		when:
			languageTagValidator.validate('EN')
		then:
			notThrown IllegalLanguageTag
	}
}
