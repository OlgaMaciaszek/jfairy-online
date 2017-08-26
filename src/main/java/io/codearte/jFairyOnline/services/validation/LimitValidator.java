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
public class LimitValidator {

	private final JFOProperties properties;

	public LimitValidator(JFOProperties properties) {
		this.properties = properties;
	}

	public void validate(int count) {
		validate(count, properties.getLimit());
	}

	public void validateForText(int count) {
		validate(count, properties.getTextLimit());
	}

	private void validate(int count, int limit) {
		Optional.of(count)
				.filter(num -> num <= limit)
				.orElseThrow(() -> new LimitExceededException(count, limit));
	}
}
