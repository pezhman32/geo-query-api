package com.goeuro.test.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple Rest API to support basic operations
 *
 * @author Pezhman Jahanmard
 */
public class RestAPIImpl implements RestAPI {
	private static final Logger LOGGER = Logger.getLogger(RestAPIImpl.class.getName());
	private String url;

	/**
	 * Without setting base url, API calls will result in APIException
	 */
	public RestAPI setBaseURL(String url) throws APIException {
		this.url = url;
		checkBaseURL();

		return this;
	}

	/**
	 * Calls the API using GET method
	 * @param relativeURL to add after base URL
	 */
	public <T> T GET(String relativeURL, Type returnType) throws APIException {
		checkBaseURL();

		try {
			String completeURL = url + relativeURL;
			LOGGER.log(Level.INFO, "request: [GET] " + completeURL);
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(completeURL);

			// add request header like OAuth? request.addHeader(...);
			HttpResponse response = httpClient.execute(request);
			LOGGER.log(Level.INFO, "response code:" + response.getStatusLine().getStatusCode());

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));

			StringBuilder result = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}

			return convert(result.toString(), returnType);
		} catch (IOException e) {
			throw new APIException(e.getMessage(), e);
		}
	}

	/**
	 * Calls the API using POST method
	 * @param relativeURL to add after base URL
	 */
	public <T> T POST(String relativeURL, Type returnType, HashMap<String, String> data) {
		return null;
	}

	/**
	 * returns Type of List<tClass> in order to use for Gson
	 */
	public <T> Type getTypeForList(Class<T> tClass) {
		return new TypeToken<ArrayList<T>>(){}.getType();
	}

	/**
	 * Convert json string to object using Gson.
	 * We can use reader instead of string but for debugging purpose we convert it to string and then to object.
	 */
	private <T> T convert(String json, Type resultObject) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(json, resultObject);
	}

	private void checkBaseURL() throws APIException {
		if (StringUtils.isBlank(url)) {
			throw new APIException("URL cannot be empty.");
		}
	}
}
