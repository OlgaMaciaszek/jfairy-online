package io.codearte.jFairyOnline.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Language;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
@Document(collection = "companynames")
public class CompanyName extends BaseEntity {

	private String id;
	@Indexed
	@NotNull
	private Language language;
	@Indexed
	@NotNull
	private String value;

	public CompanyName(Language language, String value) {
		this.language = language;
		this.value = value;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
