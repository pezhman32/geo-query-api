package com.goeuro.test.API;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Simple Rest API to support basic operations
 *
 * @author Pezhman Jahanmard
 */
public interface RestAPI {
	/**
	 * Without setting base url, API calls will result in APIException
	 */
	RestAPI setBaseURL(String url) throws APIException;

	/**
	 * Calls the API using GET method
	 * @param relativeURL to add after base URL
	 */
	<T> T GET(String relativeURL, Type returnType) throws APIException;

	/**
	 * Calls the API using POST method
	 * @param relativeURL to add after base URL
	 */
	<T> T POST(String relativeURL, Type returnType, HashMap<String, String> data);
}
