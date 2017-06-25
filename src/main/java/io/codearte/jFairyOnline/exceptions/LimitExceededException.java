package io.codearte.jFairyOnline.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-01
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LimitExceededException extends RuntimeException {

	public LimitExceededException(int number, int limit) {
		super("The provided number: " + number + " is too high. Please provide a number equal " + limit + " or smaller.");
	}
}
