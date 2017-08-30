package io.codearte.jFairyOnline.dto;

import java.util.List;

import io.codearte.jFairyOnline.model.enums.DataType;
import io.codearte.jFairyOnline.model.enums.Language;

import static io.codearte.jFairyOnline.model.enums.DataType.FEMALE_NAME;
import static io.codearte.jFairyOnline.model.enums.Language.EN;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
public class DataPackDTO {

	private Language language = EN;
	private DataType dataType = FEMALE_NAME;
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
}
