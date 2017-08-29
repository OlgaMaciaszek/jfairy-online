package io.codearte.jFairyOnline.model;

import javax.validation.constraints.NotNull;

import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.Language;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
@Document(collection = "lastnames")
public class LastName extends BaseEntity {

	private String id;
	@Indexed
	@NotNull
	private Language language;
	@Indexed
	@NotNull
	private Gender gender;
	@Indexed
	@NotNull
	private String value;

	public LastName(Language language, Gender gender, String value) {
		this.language = language;
		this.gender = gender;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
