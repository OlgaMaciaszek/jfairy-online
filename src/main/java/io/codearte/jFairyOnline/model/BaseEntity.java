package io.codearte.jFairyOnline.model;

import java.time.LocalDateTime;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
abstract class BaseEntity {

	private LocalDateTime createDate = LocalDateTime.now();

	void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}
}