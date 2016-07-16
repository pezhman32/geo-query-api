package com.goeuro.test.API;

import java.util.HashMap;

/**
 * @author Pezhman Jahanmard
 */
public interface RestAPI {
	RestAPI setBaseURL(String url) throws APIException;
	<T> T GET(String relativeURL, Class<T> returnType) throws APIException;
	<T> T POST(String relativeURL, Class<T> returnType, HashMap<String, String> data);
}
