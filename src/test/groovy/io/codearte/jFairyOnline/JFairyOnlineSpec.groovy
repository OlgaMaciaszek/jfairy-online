package io.codearte.jFairyOnline

import spock.lang.Specification

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

/**
 * @author Olga Maciaszek-Sharma
 * @since 6/25/2017
 */
@SpringBootTest
class JFairyOnlineSpec extends Specification {

	@Autowired
	ApplicationContext applicationContext

	def 'should load application context'() {
		expect:
			applicationContext
	}
}
