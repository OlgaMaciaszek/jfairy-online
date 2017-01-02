package io.codearte.jFairyOnline.services.validation;

import io.codearte.jFairyOnline.exceptions.IllegalLanguageTagException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
@Component
public class LanguageTagValidator {

	public void validate(String languageTag) {
		if ( languageTagNotAllowed(languageTag)) {
			throw new IllegalLanguageTagException(languageTag);
		}
	}

	private boolean languageTagNotAllowed(String languageTag) {
		return Arrays.stream(AllowedLanguageTags.values())
				.noneMatch(langTag -> langTag.name().equals(languageTag));
	}
}
