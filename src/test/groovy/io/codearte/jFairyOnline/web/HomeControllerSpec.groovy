package io.codearte.jFairyOnline.web

import spock.lang.IgnoreIf

import static io.codearte.jFairyOnline.web.HomeController.HOME
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
@IgnoreIf({!Boolean.valueOf(properties['integration'])})
class HomeControllerSpec extends WebControllerSpecification {

	def 'should display home page'() {
		expect:
			mockMvc.perform(get('/'))
					.andExpect(status().isOk())
					.andExpect(view().name(HOME))
	}
}
