package com.goeuro.test.Service.CitySearch;

/**
 * @author Pezhman Jahanmard
 */
public class CitySearchServiceException extends Exception {
	CitySearchServiceException(String message) {
		super(message);
	}

	CitySearchServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
