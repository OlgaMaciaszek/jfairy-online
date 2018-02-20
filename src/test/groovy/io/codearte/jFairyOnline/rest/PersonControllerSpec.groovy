package io.codearte.jFairyOnline.rest

import spock.lang.IgnoreIf

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
@IgnoreIf({!Boolean.valueOf(properties['integration'])})
class PersonControllerSpec extends RestControllerSpec {

	def 'should get persons'() {
		when:
			ResponseEntity<String> response = restTemplate.getForEntity(buildUrl('/persons'),
					String)
		then:
			response.statusCode == HttpStatus.OK
			response.body
	}
}
