package io.codearte.jFairyOnline.rest;

import io.codearte.jFairyOnline.services.TextService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/loremipsum")
	public String loremIpsum() {
		return textService.loremIpsum();
	}

	@GetMapping()
	public String text(@RequestParam(value = "lang", defaultValue = "EN") String languageTag) {
		return textService.text(languageTag);
	}

	@GetMapping("/word")
	public String word(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                   @RequestParam(value = "count", defaultValue = "1") int count) {
		return textService.word(languageTag, count);
	}

	@GetMapping("/latinword")
	public String latinWord(@RequestParam(value = "count", defaultValue = "1") int count) {
		return textService.latinWord(count);
	}

	@GetMapping("/sentence")
	public String sentence(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                       @RequestParam(value = "count", defaultValue = "1") int count) {
		return textService.sentence(languageTag, count);
	}

	@GetMapping("/latinsentence")
	public String latinSentence(@RequestParam(value = "count", defaultValue = "1") int wordCount) {
		return textService.latinSentence(wordCount);
	}

	@GetMapping("/paragraph")
	public String paragraph(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                        @RequestParam(value = "count", defaultValue = "1") int sentenceCount) {
		return textService.paragraph(languageTag, sentenceCount);
	}

	@GetMapping("/random")
	public String random(@RequestParam(value = "count", defaultValue = "1") int charsCount) {
		return textService.randomString(charsCount);
	}
}
