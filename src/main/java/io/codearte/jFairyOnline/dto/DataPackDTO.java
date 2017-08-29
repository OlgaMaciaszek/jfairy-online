package io.codearte.jFairyOnline.dto;

import java.util.List;

import io.codearte.jFairyOnline.model.enums.DataType;
import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.Language;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
public class DataPackDTO {

	private Language language;
	private DataType dataType;
	private Gender gender;
	private List<String> data;

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
