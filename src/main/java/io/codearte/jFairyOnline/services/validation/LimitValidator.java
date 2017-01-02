package io.codearte.jFairyOnline.services.validation;

import io.codearte.jFairyOnline.exceptions.LimitExceededException;
import org.springframework.stereotype.Component;

import static io.codearte.jFairyOnline.AppConstants.LIMIT;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
@Component
public class LimitValidator {

	public void validate(int number) {
		if (number > LIMIT) {
			throw new LimitExceededException(number);
		}
	}
}
