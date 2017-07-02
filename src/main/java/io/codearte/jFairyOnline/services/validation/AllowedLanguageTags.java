package io.codearte.jFairyOnline.services.validation;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
public enum AllowedLanguageTags {
	EN, PL, ES, FR, SV;

	public static String getAllowedLanguageTagsAsString() {
		return String.join(", ", getValuesAsStrings());
	}

	private static List<String> getValuesAsStrings() {
		return stream(values()).map(Enum::name).collect(toList());
	}
}
