package io.codearte.jFairyOnline.web

import io.codearte.jFairyOnline.dto.DataPackDTO
import spock.lang.IgnoreIf

import static io.codearte.jFairyOnline.web.DataController.DATA_INPUT_CONFIRM
import static io.codearte.jFairyOnline.web.DataController.DATA_PACK
import static org.hamcrest.Matchers.isA
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
@IgnoreIf({!Boolean.valueOf(properties['integration'])})
class DataControllerSpec extends WebControllerSpecification {

	def 'should display data pack input form'() {
		expect:
			mockMvc.perform(get('/data'))
					.andExpect(status().isOk())
					.andExpect(view().name('dataInputForm'))
					.andExpect(model().attributeExists(DATA_PACK))
					.andExpect(model().attribute(DATA_PACK, isA(DataPackDTO)))
	}

	def 'should display data input confirmation'() {
		expect:
			mockMvc.perform(post('/data')
					.contentType(APPLICATION_FORM_URLENCODED)
					.param('language', 'EN')
					.param('dataType', 'FEMALE_NAME')
					.param('data', 'Ann'))
					.andExpect(status().isOk())
					.andExpect(view().name(DATA_INPUT_CONFIRM))
	}
}
