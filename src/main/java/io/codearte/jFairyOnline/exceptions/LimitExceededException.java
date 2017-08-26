package io.codearte.jFairyOnline.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-01
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LimitExceededException extends RuntimeException {

	public LimitExceededException(int count, int limit) {
		super("The provided count: " + count + " is too high. Please provide a count equal " + limit + " or smaller.");
	}
}
