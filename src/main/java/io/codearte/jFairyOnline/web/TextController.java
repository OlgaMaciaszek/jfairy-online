package io.codearte.jFairyOnline.web;

import io.codearte.jFairyOnline.services.TextService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/26/17
 */
@Controller
@RequestMapping("/text")
public class TextController {

	private static final String TEXT = "text";

	private final TextService textService;

	public TextController(TextService textService) {
		this.textService = textService;
	}

	@GetMapping()
	public String text(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                   @RequestParam(value = "count", defaultValue = "1") int count,
	                   @RequestParam(value = "textType") String textType,
	                   Model model) {
		String text = textService.getForType(languageTag, count, textType);
		model.addAttribute(TEXT, text);
		return TEXT;
	}
}
