package io.codearte.jFairyOnline.rest;

import io.codearte.jFairyOnline.services.TextService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/26/17
 */
@RestController("restTextController")
@RequestMapping("/rest/text")
public class TextController {

	private final TextService textService;

	public TextController(TextService textService) {
		this.textService = textService;
	}

	@GetMapping("/loremIpsum")
	public ResponseEntity<String> loremIpsum(@RequestParam(value = "count", defaultValue = "1") int count) {
		String loremIpsum = textService.loremIpsum("", count);
		return ok(loremIpsum);
	}

	@GetMapping
	public ResponseEntity<String> text(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                                   @RequestParam(value = "count", defaultValue = "1") int count) {
		String text = textService.text(languageTag, count);
		return ok(text);
	}

	@GetMapping("/word")
	public ResponseEntity<String> word(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                                   @RequestParam(value = "count", defaultValue = "1") int count) {
		String word = textService.word(languageTag, count);
		return ok(word);
	}

	@GetMapping("/latinWord")
	public ResponseEntity<String> latinWord(@RequestParam(value = "count", defaultValue = "1") int count) {
		String latinWord = textService.latinWord(count);
		return ok(latinWord);
	}

	@GetMapping("/sentence")
	public ResponseEntity<String> sentence(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                                       @RequestParam(value = "count", defaultValue = "1") int count) {
		String sentence = textService.sentence(languageTag, count);
		return ok(sentence);
	}

	@GetMapping("/latinSentence")
	public ResponseEntity<String> latinSentence(@RequestParam(value = "count", defaultValue = "1") int wordCount) {
		String latinSentence = textService.latinSentence(wordCount);
		return ok(latinSentence);
	}

	@GetMapping("/paragraph")
	public ResponseEntity<String> paragraph(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                                        @RequestParam(value = "count", defaultValue = "1") int sentenceCount) {
		String paragraph = textService.paragraph(languageTag, sentenceCount);
		return ok(paragraph);
	}

	@GetMapping("/random")
	public ResponseEntity<String> random(@RequestParam(value = "count", defaultValue = "1") int charsCount) {
		String randomString = textService.randomString(charsCount);
		return ok(randomString);
	}
}
