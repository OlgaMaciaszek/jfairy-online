package io.codearte.jFairyOnline.services;

import io.codearte.jFairyOnline.config.JFOProperties;
import io.codearte.jFairyOnline.services.fairy.FairyProvider;
import io.codearte.jFairyOnline.services.validation.CountProvider;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.text.TextProducer;

import org.springframework.stereotype.Service;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/26/17
 */
@Service
public class TextService {

	private final JFOProperties jfoProperties;
	private final FairyProvider fairyProvider;
	private final CountProvider countProvider;

	public TextService(JFOProperties jfoProperties, FairyProvider fairyProvider, CountProvider countProvider) {
		this.jfoProperties = jfoProperties;
		this.fairyProvider = fairyProvider;
		this.countProvider = countProvider;
	}

	public String loremIpsum() {
		return textProducer().loremIpsum();
	}

	public String text(String languageTag) {
		return textProducer(languageTag).text();
	}

	public String word(String languageTag, int count) {
		int validCount = countProvider.validForText(count);
		return textProducer(languageTag).word(validCount);
	}

	public String latinWord(int count) {
		int validCount = countProvider.validForText(count);
		return textProducer().latinWord(validCount);
	}

	public String latinSentence(int wordCount) {
		int validWordCount = countProvider.validForText(wordCount);
		return textProducer().latinSentence(validWordCount);
	}

	public String sentence(String languageTag, int wordCount) {
		int validWordCount = countProvider.validForText(wordCount);
		return textProducer(languageTag).sentence(validWordCount);
	}

	public String paragraph(String languageTag, int sentenceCount) {
		int validSentenceCount = countProvider.validForText(sentenceCount);
		return textProducer(languageTag).paragraph(validSentenceCount);
	}

	public String randomString(int charsCount) {
		int validCharsCount = countProvider.validForRandomString(charsCount);
		return textProducer().randomString(validCharsCount);
	}

	private TextProducer textProducer(String languageTag) {
		Fairy fairy = fairyProvider.getFairy(languageTag);
		return fairy.textProducer();
	}

	private TextProducer textProducer() {
		Fairy fairy = fairyProvider.getFairy(jfoProperties.getDefaultLanguageTag());
		return fairy.textProducer();
	}
}
