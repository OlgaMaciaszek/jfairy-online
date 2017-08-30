package io.codearte.jFairyOnline.services.validation;

import io.codearte.jFairyOnline.exceptions.IllegalLanguageTag;

import org.springframework.stereotype.Component;

import static java.util.Arrays.stream;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
@Component
public class LanguageTagValidator {

	public void validate(String languageTag) {
		if (languageTagNotAllowed(languageTag)) {
			throw new IllegalLanguageTag(languageTag);
		}
	}

	private boolean languageTagNotAllowed(String languageTag) {
		return stream(AllowedLanguageTags.values())
				.noneMatch(langTag -> langTag.name().equals(languageTag));
	}
}
