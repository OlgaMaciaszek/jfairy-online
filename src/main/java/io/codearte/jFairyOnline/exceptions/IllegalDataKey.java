package io.codearte.jFairyOnline.exceptions;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
public class IllegalDataKey extends RuntimeException {

	public IllegalDataKey(String dataKey) {
		super("The provided data key does not exist: " + dataKey);
	}
}
