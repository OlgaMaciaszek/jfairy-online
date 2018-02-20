package io.codearte.jFairyOnline.model;

import java.util.Set;

import io.codearte.jFairyOnline.model.enums.DataType;
import io.codearte.jFairyOnline.model.enums.Gender;
import io.codearte.jFairyOnline.model.enums.JFairyDataKey;
import io.codearte.jFairyOnline.model.enums.Language;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
@Document(collection = "datapacks")
public class DataPack {

	private String id;
	private DataType dataType;
	private Language language;
	private Gender gender;
	private JFairyDataKey jFairyDataKey;
	private Set<DataItem> dataItems;
	private boolean processed = false;

	public DataPack(DataType dataType, Language language, Gender gender, JFairyDataKey jFairyDataKey,
	                Set<DataItem> dataItems) {
		this.dataType = dataType;
		this.language = language;
		this.dataItems = dataItems;
		this.jFairyDataKey = jFairyDataKey;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Set<DataItem> getDataItems() {
		return dataItems;
	}

	public void setDataItems(Set<DataItem> dataItems) {
		this.dataItems = dataItems;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public JFairyDataKey getjFairyDataKey() {
		return jFairyDataKey;
	}

	public void setjFairyDataKey(JFairyDataKey jFairyDataKey) {
		this.jFairyDataKey = jFairyDataKey;
	}
}
