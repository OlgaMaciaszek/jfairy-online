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

	@GetMapping("/loremipsum")
	public String loremIpsum(Model model) {
		String text = textService.loremIpsum();
		model.addAttribute(TEXT, text);
		return TEXT;
	}

	@GetMapping()
	public String text(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                   Model model) {
		String text = textService.text(languageTag);
		model.addAttribute(TEXT, text);
		return TEXT;
	}

	@GetMapping("/word")
	public String word(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                   @RequestParam(value = "count", defaultValue = "1") int count,
	                   Model model) {
		String text = textService.word(languageTag, count);
		model.addAttribute(TEXT, text);
		return TEXT;
	}

	@GetMapping("/latinword")
	public String latinWord(@RequestParam(value = "count", defaultValue = "1") int count,
	                        Model model) {
		String text = textService.latinWord(count);
		model.addAttribute(TEXT, text);
		return TEXT;
	}

	@GetMapping("/sentence")
	public String sentence(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                       @RequestParam(value = "count", defaultValue = "3") int count,
	                       Model model) {
		String text = textService.sentence(languageTag, count);
		model.addAttribute(TEXT, text);
		return TEXT;
	}

	@GetMapping("/latinsentence")
	public String latinSentence(@RequestParam(value = "count", defaultValue = "3") int wordCount,
	                            Model model) {
		String text = textService.latinSentence(wordCount);
		model.addAttribute(TEXT, text);
		return TEXT;
	}

	@GetMapping("/paragraph")
	public String paragraph(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                        @RequestParam(value = "count", defaultValue = "3") int sentenceCount,
	                        Model model) {
		String text = textService.paragraph(languageTag, sentenceCount);
		model.addAttribute(TEXT, text);
		return TEXT;
	}

	@GetMapping("/random")
	public String random(@RequestParam(value = "count", defaultValue = "10") int charsCount,
	                     Model model) {
		String text = textService.randomString(charsCount);
		model.addAttribute(TEXT, text);
		return TEXT;
	}
}
