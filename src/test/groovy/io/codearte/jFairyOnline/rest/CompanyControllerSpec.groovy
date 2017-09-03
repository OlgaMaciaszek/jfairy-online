package io.codearte.jFairyOnline.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
class CompanyControllerSpec extends RestControllerSpec {

	def 'should get companies'() {
		when:
			ResponseEntity<String> response = restTemplate.getForEntity(buildUrl('/companies'),
					String)
		then:
			response.statusCode == HttpStatus.OK
			response.body
	}
}
