package io.codearte.jFairyOnline.model;

import java.util.Set;

import io.codearte.jFairyOnline.model.enums.DataType;
import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.Language;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
@Document(collection = "datapacks")
public class GenderSpecificDataPack extends DataPack {

	private Gender gender;

	public GenderSpecificDataPack(DataType dataType, Language language, Set<DataItem> dataItems, Gender gender) {
		super(dataType, language, dataItems);
		this.gender = gender;

	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
