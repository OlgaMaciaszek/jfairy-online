package io.codearte.jFairyOnline.rest

import spock.lang.Unroll

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
class TextControllerSpec extends RestControllerSpec {

	@Unroll
	def 'should get #textType text'() {
		when:
			ResponseEntity<String> response = restTemplate.getForEntity(buildUrl("/text/$textType"),
					String)
		then:
			response.statusCode == HttpStatus.OK
			response.body
		where:
			textType << ['', 'word', 'sentence', 'latinWord', 'latinSentence', 'loremIpsum', 'paragraph',
			             'random']
	}
}
