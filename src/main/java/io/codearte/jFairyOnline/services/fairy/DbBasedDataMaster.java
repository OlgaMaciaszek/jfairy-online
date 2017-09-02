package io.codearte.jFairyOnline.services.fairy;

import java.util.List;
import java.util.Random;

import io.codearte.jFairyOnline.model.enums.Language;
import io.codearte.jFairyOnline.services.ValueService;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;

import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkArgument;
import static io.codearte.jFairyOnline.model.enums.Language.EN;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
@Component
public class DbBasedDataMaster implements DataMaster {

	private static final String MISSING_VALUE_PLACEHOLDER = "???";
	private final ValueService valueService;
	private final BaseProducer baseProducer = new BaseProducer(new Random());
	private Language language = EN;

	public DbBasedDataMaster(ValueService valueService) {
		this.valueService = valueService;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public String getString(String key) {
		checkKey(key);
		return valueService.getValue(key, language);
	}

	@Override
	public List<String> getStringList(String key) {
		checkKey(key);
		return valueService.getValues(key, language);
	}

	@Override
	public String getValuesOfType(String key, String gender) {
		checkKey(key);
		checkGender(gender);
		List<String> values = valueService.getValues(key, gender, language);
		if (values.size() > 0) {
			return baseProducer.randomElement(values);
		}
		return MISSING_VALUE_PLACEHOLDER;
	}

	@Override
	public String getRandomValue(String key) {
		List<String> values = getStringList(key);
		if (values.size() > 0) {
			return baseProducer.randomElement(values);
		}
		return MISSING_VALUE_PLACEHOLDER;
	}

	private void checkGender(String gender) {
		checkArgument(gender != null, "Gender cannot be null");
	}

	private void checkKey(String key) {
		checkArgument(key != null, "Key cannot be null");
	}
}
