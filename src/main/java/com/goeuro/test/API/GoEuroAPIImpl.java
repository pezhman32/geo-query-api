package com.goeuro.test.API;

import com.goeuro.test.Entity.CityEntity;
import com.goeuro.test.common.Constants;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
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
					URLEncoder.encode(city, "UTF-8"), getTypeForCityList());

			return cityEntities;
		} catch (UnsupportedEncodingException e) {
			throw new APIException(e.getMessage(), e);
		}
	}

	private Type getTypeForCityList() {
		return new TypeToken<ArrayList<CityEntity>>(){}.getType();
	}
}
