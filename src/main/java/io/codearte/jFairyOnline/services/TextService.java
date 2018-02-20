package io.codearte.jFairyOnline.services;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

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

	private static final String LOREM_IPSUM = "loremIpsum";
	private static final String TEXT = "text";
	private static final String WORD = "word";
	private static final String LATIN_WORD = "latinWord";
	private static final String LATIN_SENTENCE = "latinSentence";
	private static final String SENTENCE = "sentence";
	private static final String PARAGRAPH = "paragraph";
	private static final String RANDOM = "random";
	private final JFOProperties jfoProperties;
	private final FairyProvider fairyProvider;
	private final CountProvider countProvider;
	private final Map<String, BiFunction<String, Integer, String>> methodMap = new HashMap<>();

	public TextService(JFOProperties jfoProperties, FairyProvider fairyProvider, CountProvider countProvider) {
		this.jfoProperties = jfoProperties;
		this.fairyProvider = fairyProvider;
		this.countProvider = countProvider;
		initialiseMethodMap();
	}

	public String getForType(String languageTag, int count, String textType) {
		return methodMap.get(textType).apply(languageTag, count);
	}

	public String loremIpsum() {
		return textProducer().loremIpsum();
	}

	private String loremIpsum(String languageTag, int count) {
		return loremIpsum();
	}

	public String text(String languageTag, int count) {
		return text(languageTag);
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

	private String latinWord(String languageTag, int count) {
		return latinWord(count);
	}

	public String latinSentence(int wordCount) {
		int validWordCount = countProvider.validForText(wordCount);
		return textProducer().latinSentence(validWordCount);
	}

	private String latinSentence(String languageTag, int wordCount) {
		return latinSentence(wordCount);
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

	private String randomString(String languageTag, int charsCount) {
		return randomString(charsCount);
	}

	private void initialiseMethodMap() {
		methodMap.put(LOREM_IPSUM, this::loremIpsum);
		methodMap.put(TEXT, this::text);
		methodMap.put(WORD, this::word);
		methodMap.put(LATIN_WORD, this::latinWord);
		methodMap.put(LATIN_SENTENCE, this::latinSentence);
		methodMap.put(SENTENCE, this::sentence);
		methodMap.put(PARAGRAPH, this::paragraph);
		methodMap.put(RANDOM, this::randomString);
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
