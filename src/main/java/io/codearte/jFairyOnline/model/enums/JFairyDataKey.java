package io.codearte.jFairyOnline.model.enums;

import io.codearte.jFairyOnline.exceptions.IllegalDataKey;

import static java.util.Arrays.stream;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
public enum JFairyDataKey {

	FIRST_NAME("firstNames"),
	LAST_NAME("lastNames"),
	STREET("street"),
	CITY("city"),
	POSTAL_CODE_FORMAT("postal_code"),
	TELEPHONE_NUMBER_FORMAT("telephone_number_formats"),
	PERSONAL_EMAIL("personalEmails"),
	TEXT("text"),
	ALPHABET("alphabet"),
	LOREM_IPSUM("loremIpsum"),
	DOMAIN("domains"),
	COMPANY_SUFFIX("companySuffixes"),
	COMPANY_NAME("companyNames"),
	COMPANY_EMAIL("companyEmails"),
	CARD_VENDOR("cardVendors");

	private final String keyValue;

	JFairyDataKey(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public static JFairyDataKey getByKeyValue(String keyValue) {
		return stream(JFairyDataKey.values()).filter(jFairyDataKey -> keyValue.equals(jFairyDataKey.getKeyValue()))
				.findFirst().orElseThrow(() -> new IllegalDataKey(keyValue));
	}
}
