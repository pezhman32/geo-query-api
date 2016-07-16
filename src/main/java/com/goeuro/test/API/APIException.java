package com.goeuro.test.API;

/**
 * @author Pezhman Jahanmard
 */
public class APIException extends Exception {
	APIException(String message) {
		super(message);
	}

	APIException(String message, Throwable cause) {
		super(message, cause);
	}
}
