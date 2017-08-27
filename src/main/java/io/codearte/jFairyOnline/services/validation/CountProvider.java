package io.codearte.jFairyOnline.services.validation;

import java.util.Optional;

import io.codearte.jFairyOnline.config.JFOProperties;
import io.codearte.jFairyOnline.exceptions.LimitExceededException;

import org.springframework.stereotype.Component;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
@Component
public class CountProvider {

	private final JFOProperties properties;

	public CountProvider(JFOProperties properties) {
		this.properties = properties;
	}

	public int valid(int count) {
		return valid(count, properties.getLimit());
	}

	public int validForText(int count) {
		return valid(count, properties.getTextLimit());
	}

	public int validForRandomString(int count) {
		return valid(count, properties.getRandomStringLimit());
	}

	private int valid(int count, int limit) {
		return count <= limit ? count : limit;
	}
}
