package io.codearte.jFairyOnline.model;

import java.util.Set;

import io.codearte.jFairyOnline.model.enums.DataType;
import io.codearte.jFairyOnline.model.enums.Language;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
@Document(collection = "datapacks")
public class DataPack {

	protected String id;
	protected DataType dataType;
	protected Language language;
	protected Set<DataItem> dataItems;
	protected boolean processed = false;

	public DataPack(DataType dataType, Language language, Set<DataItem> dataItems) {
		this.dataType = dataType;
		this.language = language;
		this.dataItems = dataItems;
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
}
