package io.codearte.jFairyOnline.services;

import io.codearte.jFairyOnline.config.JFOProperties;
import io.codearte.jFairyOnline.services.fairy.FairyProvider;
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

	public TextService(JFOProperties jfoProperties, FairyProvider fairyProvider) {
		this.jfoProperties = jfoProperties;
		this.fairyProvider = fairyProvider;
	}

	public String loremIpsum() {
		return textProducer().loremIpsum();
	}

	public String text(String languageTag) {
		return textProducer(languageTag).text();
	}

	public String word(String languageTag, int count) {
		return textProducer(languageTag).word(count);
	}

	public String latinWord(int count) {
		return textProducer().latinWord(count);
	}

	public String latinSentence(int wordCount) {
		return textProducer().latinSentence(wordCount);
	}

	public String sentence(String languageTag, int wordCount) {
		return textProducer(languageTag).sentence(wordCount);
	}

	public String paragraph(String languageTag, int sentenceCount) {
		return textProducer(languageTag).paragraph(sentenceCount);
	}

	public String randomString(String languageTag, int charsCount) {
		return textProducer(languageTag).randomString(charsCount);
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
