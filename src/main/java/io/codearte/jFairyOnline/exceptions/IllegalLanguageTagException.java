package io.codearte.jFairyOnline.exceptions;

import io.codearte.jFairyOnline.services.validation.AllowedLanguageTags;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalLanguageTagException extends RuntimeException {

	public IllegalLanguageTagException(String languageTag) {
		super("The provided language tag: " + languageTag + " is not allowed. The currently supported language tags are: "
				+ AllowedLanguageTags.getAllowedLanguageTagsAsString() + ".");
	}
}
