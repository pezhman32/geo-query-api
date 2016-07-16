package com.goeuro.test.API;

import com.goeuro.test.Entity.CityEntity;

import java.util.List;

/**
 * API to connect to GoEuro RESTful web service
 *
 * @author Pezhman Jahanmard
 */
public interface GoEuroAPI {
	/**
	 * Lookup for given city name through API and returns found result set
	 */
	List<CityEntity> getCities(String city) throws APIException;
}
