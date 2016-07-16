package com.goeuro.test.API;

import com.goeuro.test.Entity.CityEntity;
import com.goeuro.test.common.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * API to connect to GoEuro RESTful web service
 *
 * @author Pezhman Jahanmard
 */
public class GoEuroAPIImpl implements GoEuroAPI {

	/**
	 * Lookup for given city name through API and returns found result set
	 */
	public List<CityEntity> getCities(String city) throws APIException {
		try {
			RestAPI restAPI = new RestAPIImpl().setBaseURL(Constants.CITY_API_URL);
			List<CityEntity> cityEntities = restAPI.GET(
					URLEncoder.encode(city, "UTF-8"),
					restAPI.getTypeForList(CityEntity.class));

			return cityEntities;
		} catch (UnsupportedEncodingException e) {
			throw new APIException(e.getMessage(), e);
		}
	}
}
