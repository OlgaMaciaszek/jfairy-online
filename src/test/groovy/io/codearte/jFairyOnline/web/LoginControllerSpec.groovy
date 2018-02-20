package io.codearte.jFairyOnline.web

import spock.lang.IgnoreIf

import static io.codearte.jFairyOnline.web.LoginController.LOGIN
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
@IgnoreIf({!Boolean.valueOf(properties['integration'])})
class LoginControllerSpec extends WebControllerSpecification {

	def 'should display login page'() {
		expect:
			mockMvc.perform(get('/login'))
					.andExpect(status().isOk())
					.andExpect(view().name(LOGIN))
	}
}
