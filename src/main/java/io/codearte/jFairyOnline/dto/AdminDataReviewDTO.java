package io.codearte.jFairyOnline.dto;

import io.codearte.jFairyOnline.model.enums.DataType;
import io.codearte.jFairyOnline.model.enums.Language;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/1/17
 */
public class AdminDataReviewDTO {

	private Language language;
	private DataType dataType;

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
}
