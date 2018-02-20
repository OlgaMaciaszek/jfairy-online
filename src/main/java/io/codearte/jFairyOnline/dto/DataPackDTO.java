package io.codearte.jFairyOnline.dto;

import java.util.List;
import java.util.Set;

import io.codearte.jFairyOnline.model.DataItem;
import io.codearte.jFairyOnline.model.DataPack;
import io.codearte.jFairyOnline.model.enums.DataType;
import io.codearte.jFairyOnline.model.enums.Language;

import static io.codearte.jFairyOnline.model.enums.DataType.FEMALE_NAME;
import static io.codearte.jFairyOnline.model.enums.Language.EN;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
public class DataPackDTO {

	private String id;
	private Language language = EN;
	private DataType dataType = FEMALE_NAME;
	private List<String> data;
	private Set<DataItem> dataItems;
	private Long[] dataItemsToDelete;

	public DataPackDTO() {
	}

	public DataPackDTO(DataPack dataPack) {
		this(dataPack.getId(), dataPack.getLanguage(), dataPack.getDataType(), dataPack.getDataItems());
	}

	private DataPackDTO(String id, Language language, DataType dataType, Set<DataItem> dataItems) {
		this.id = id;
		this.language = language;
		this.dataType = dataType;
		this.dataItems = dataItems;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<DataItem> getDataItems() {
		return dataItems;
	}

	public void setDataItems(Set<DataItem> dataItems) {
		this.dataItems = dataItems;
	}

	public Long[] getDataItemsToDelete() {
		return dataItemsToDelete;
	}

	public void setDataItemsToDelete(Long[] dataItemsToDelete) {
		this.dataItemsToDelete = dataItemsToDelete;
	}
}
