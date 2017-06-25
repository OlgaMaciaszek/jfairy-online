package io.codearte.jFairyOnline.services.validation;

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
		int limit = properties.getLimit();
		if (number > limit) {
			throw new LimitExceededException(number, limit);
		}
	}
}
