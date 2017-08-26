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

	public void validate(int number) {
		validate(number, properties.getLimit());
	}

	public void validateForText(int number) {
		validate(number, properties.getTextLimit());
	}

	private void validate(int number, int limit) {
		Optional.of(number)
				.filter(num -> num <= limit)
				.orElseThrow(() -> new LimitExceededException(number, limit));
	}
}
