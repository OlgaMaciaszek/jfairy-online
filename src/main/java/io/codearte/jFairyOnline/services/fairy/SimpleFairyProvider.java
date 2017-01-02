package io.codearte.jFairyOnline.services.fairy;

import io.codearte.jFairyOnline.services.fairy.FairyProvider;
import io.codearte.jFairyOnline.services.validation.LanguageTagValidator;
import io.codearte.jfairy.Fairy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
@Component
public class SimpleFairyProvider implements FairyProvider {

	private final LanguageTagValidator languageTagValidator;

	@Autowired
	public SimpleFairyProvider(LanguageTagValidator languageTagValidator) {
		this.languageTagValidator = languageTagValidator;
	}

	@Override
	public Fairy getFairy(String languageTag) {
		languageTagValidator.validate(languageTag);
		return Fairy.create(Locale.forLanguageTag(languageTag));
	}
}
