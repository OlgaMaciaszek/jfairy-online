package io.codearte.jFairyOnline.web

import spock.lang.IgnoreIf

import static io.codearte.jFairyOnline.web.TextController.TEXT
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
@IgnoreIf({!Boolean.valueOf(properties['integration'])})
class TextControllerSpec extends WebControllerSpecification {

	def 'should display text data page'() {
		expect:
			mockMvc.perform(get('/text')
					.param('textType', TEXT))
					.andExpect(status().isOk())
					.andExpect(view().name(TEXT))
					.andExpect(model().attributeExists(TEXT))
	}
}
