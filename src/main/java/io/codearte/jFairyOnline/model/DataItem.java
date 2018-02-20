package io.codearte.jFairyOnline.model;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
public class DataItem {

	private long id;
	private String value;

	public DataItem(long id, String value) {
		this.id = id;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
