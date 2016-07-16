package com.goeuro.test.Service.CSV;

/**
 * @author Pezhman Jahanmard
 */
public class CSVGeneratorException extends Exception {
	CSVGeneratorException(String message) {
		super(message);
	}

	CSVGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}
}
