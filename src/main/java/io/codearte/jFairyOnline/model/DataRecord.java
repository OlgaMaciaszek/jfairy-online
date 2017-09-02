package io.codearte.jFairyOnline.model;

import javax.validation.constraints.NotNull;

import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.JFairyDataKey;
import io.codearte.jFairyOnline.model.enums.Language;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
@Document(collection = "datarecords")
public class DataRecord extends BaseEntity {

	private String id;
	@Indexed
	@NotNull
	private Language language;
	@Indexed
	@NotNull
	private String value;
	@Indexed
	@NotNull
	private JFairyDataKey jFairyDataKey;
	@Indexed
	private Gender gender;

	public DataRecord(String value, Language language, JFairyDataKey jFairyDataKey, Gender gender) {
		this.language = language;
		this.value = value;
		this.jFairyDataKey = jFairyDataKey;
		this.gender = gender;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public JFairyDataKey getJFairyDataKey() {
		return jFairyDataKey;
	}

	public void setJFairyDataKey(JFairyDataKey jFairyDataKey) {
		this.jFairyDataKey = jFairyDataKey;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
