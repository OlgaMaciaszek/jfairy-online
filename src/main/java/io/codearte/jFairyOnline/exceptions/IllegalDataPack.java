package io.codearte.jFairyOnline.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
@ResponseStatus(BAD_REQUEST)
public class IllegalDataPack extends RuntimeException {

	public IllegalDataPack(String dataPackId) {
		super("Data Pack with the id " + dataPackId + " either does not exist or has already been processed.");
	}
}
