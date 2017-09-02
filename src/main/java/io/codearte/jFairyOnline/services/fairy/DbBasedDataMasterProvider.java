package io.codearte.jFairyOnline.services.fairy;

import com.google.inject.Provider;
import io.codearte.jFairyOnline.model.enums.Language;
import io.codearte.jfairy.data.DataMaster;

import org.springframework.stereotype.Component;

import static io.codearte.jFairyOnline.model.enums.Language.EN;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
@Component
public class DbBasedDataMasterProvider implements Provider<DataMaster> {

	private final DbBasedDataMaster dbBasedDataMaster;
	private Language language = EN;

	public DbBasedDataMasterProvider(DbBasedDataMaster dbBasedDataMaster) {
		this.dbBasedDataMaster = dbBasedDataMaster;
	}

	@Override
	public DbBasedDataMaster get() {
		dbBasedDataMaster.setLanguage(language);
		return dbBasedDataMaster;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
}
