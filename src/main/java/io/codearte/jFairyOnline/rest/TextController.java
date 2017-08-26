package io.codearte.jFairyOnline.rest;

import io.codearte.jFairyOnline.services.TextService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/26/17
 */
@RestController("restCompanyController")
@RequestMapping("/text")
public class TextController {

	private final TextService textService;

	public TextController(TextService textService) {
		this.textService = textService;
	}
}
