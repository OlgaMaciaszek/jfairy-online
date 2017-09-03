package io.codearte.jFairyOnline.rest

import spock.lang.Specification

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

/**
 * @author Olga Maciaszek-Sharma
 * @since 6/25/2017
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
class RestControllerSpec extends Specification {

	@Autowired
	protected TestRestTemplate restTemplate

	@LocalServerPort
	int port

	protected String buildUrl(String path) {
		return "http://localhost:$port/rest$path"
	}
}
