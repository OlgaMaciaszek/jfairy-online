package io.codearte.jFairyOnline.services.fairy;

import java.util.Locale;

import io.codearte.jFairyOnline.services.validation.LanguageTagValidator;
import io.codearte.jfairy.Fairy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import static io.codearte.jFairyOnline.model.enums.Language.valueOf;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
@Component
@ConditionalOnProperty("databaseDriven")
@Primary
public class DatabaseFairyProvider implements FairyProvider {

	private final LanguageTagValidator languageTagValidator;
	private final DbBasedDataMasterProvider dbBasedDataMasterProvider;

	public DatabaseFairyProvider(LanguageTagValidator languageTagValidator, DbBasedDataMasterProvider dbBasedDataMasterProvider) {
		this.languageTagValidator = languageTagValidator;
		this.dbBasedDataMasterProvider = dbBasedDataMasterProvider;
	}

	@Override
	public Fairy getFairy(String languageTag) {
		languageTagValidator.validate(languageTag);
		dbBasedDataMasterProvider.setLanguage(valueOf(languageTag));
		return Fairy.create(dbBasedDataMasterProvider, Locale.forLanguageTag(languageTag));
	}
}
