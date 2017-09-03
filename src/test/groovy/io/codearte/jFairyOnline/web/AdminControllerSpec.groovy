package io.codearte.jFairyOnline.web

import io.codearte.jFairyOnline.dto.AdminDataReviewDTO
import spock.lang.IgnoreIf

import static io.codearte.jFairyOnline.web.AdminController.ADMIN
import static io.codearte.jFairyOnline.web.AdminController.DATA_REVIEW
import static io.codearte.jFairyOnline.web.AdminController.INFO
import static io.codearte.jFairyOnline.web.AdminController.NO_UNPROCESSED_DATA_PACKS
import static org.hamcrest.Matchers.isA
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/3/17
 */
@IgnoreIf({!Boolean.valueOf(properties['integration'])})
class AdminControllerSpec extends WebControllerSpecification {

	def 'should load admin panel'() {
		expect:
			mockMvc.perform(get('/admin'))
					.andExpect(status().isOk())
					.andExpect(view().name(ADMIN))
					.andExpect(model().attribute(DATA_REVIEW, isA(AdminDataReviewDTO)))
	}

	def 'should load no data packs available'() {
		expect:
			mockMvc.perform(get('/admin/noPack'))
					.andExpect(status().isOk())
					.andExpect(view().name(ADMIN))
					.andExpect(model().attribute(DATA_REVIEW, isA(AdminDataReviewDTO)))
					.andExpect(model().attribute(INFO, NO_UNPROCESSED_DATA_PACKS))
	}

}
