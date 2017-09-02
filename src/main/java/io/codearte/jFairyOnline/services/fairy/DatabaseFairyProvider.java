package io.codearte.jFairyOnline.services.fairy;

import java.util.Locale;

import io.codearte.jFairyOnline.services.validation.LanguageTagValidator;
import io.codearte.jfairy.Fairy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
@Component
@ConditionalOnProperty("databaseDriven")
@Primary
public class DatabaseFairyProvider implements FairyProvider {

	private final LanguageTagValidator languageTagValidator;

	public DatabaseFairyProvider(LanguageTagValidator languageTagValidator) {
		this.languageTagValidator = languageTagValidator;
	}

	@Override
	public Fairy getFairy(String languageTag) {
		languageTagValidator.validate(languageTag);
		return Fairy.create(Locale.forLanguageTag(languageTag));
	}
}
