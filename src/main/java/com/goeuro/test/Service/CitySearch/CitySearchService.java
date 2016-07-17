package com.goeuro.test.Service.CitySearch;

import com.goeuro.test.API.APIException;
import com.goeuro.test.Service.CSV.CSVGeneratorException;

/**
 * Main purpose of this service is to get list of cities from user and search them through GoEuro API
 * Then store them in a CSV file and save them in hard disc.
 *
 * @author Pezhman Jahanmard
 */
public interface CitySearchService {

	/**
	 * Look for the given city into GoEuro API and save the result into a CSV file and will return the saved file path
	 * @return saved CSV file path
	 */
	String find(String cityName) throws APIException, CitySearchServiceException, CSVGeneratorException;
}
