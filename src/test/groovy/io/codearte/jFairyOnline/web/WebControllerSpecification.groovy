package io.codearte.jFairyOnline.web

import spock.lang.Specification

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
@SpringBootTest
abstract class WebControllerSpecification extends Specification {

	protected MockMvc mockMvc

	@Autowired
	WebApplicationContext webApplicationContext

	def setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build()
	}
}