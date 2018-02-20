package io.codearte.jFairyOnline.exceptions;

import io.codearte.jFairyOnline.services.validation.AllowedLanguageTags;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
@ResponseStatus(BAD_REQUEST)
public class IllegalLanguageTag extends RuntimeException {

	public IllegalLanguageTag(String languageTag) {
		super("The provided language tag: " + languageTag + " is not allowed. The currently supported language tags are: "
				+ AllowedLanguageTags.getAllowedLanguageTagsAsString() + ".");
	}
}
