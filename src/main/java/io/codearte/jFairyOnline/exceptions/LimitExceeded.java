package io.codearte.jFairyOnline.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-01
 */
@ResponseStatus(BAD_REQUEST)
public class LimitExceeded extends RuntimeException {

	public LimitExceeded(int count, int limit) {
		super("The provided count: " + count + " is too high. Please provide a count equal " + limit + " or smaller.");
	}
}
