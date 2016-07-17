package com.goeuro.test.Service.CitySearch;

import com.goeuro.test.API.APIException;
import com.goeuro.test.API.GoEuroAPI;
import com.goeuro.test.API.GoEuroAPIImpl;
import com.goeuro.test.Entity.CityEntity;
import com.goeuro.test.Service.CSV.CSVGeneratorException;
import com.goeuro.test.Service.CSV.CSVGeneratorService;
import com.goeuro.test.Service.CSV.CSVGeneratorServiceImpl;

import java.util.List;

/**
 * Main purpose of this service is to get list of cities from user and search them through GoEuro API
 * Then store them in a CSV file and save them in hard disc.
 *
 * @author Pezhman Jahanmard
 */
public class CitySearchServiceImpl implements CitySearchService {
	//private static final Logger LOGGER = Logger.getLogger(CitySearchServiceImpl.class.getName());

	/**
	 * Look for the given city into GoEuro API and save the result into a CSV file and will return the saved file path
	 * @return saved CSV file path
	 */
	public String find(String cityName) throws APIException, CitySearchServiceException, CSVGeneratorException {
		GoEuroAPI api = new GoEuroAPIImpl();
		List<CityEntity> cities = api.getCities(cityName);
		if (cityName == null || cities.size() == 0) {
			throw new CitySearchServiceException("No records found.");
		}

		return createCSVFile(cityName, cities);
	}

	/**
	 * Create a CSV file from list of cities and will return saved file path
	 *
	 * @param cityName to add as a prefix to fileName
	 * @param cityEntities list of cities (CSV rows)
	 * @return saved file path
	 */
	private String createCSVFile(String cityName, List<CityEntity> cityEntities) throws CSVGeneratorException {
		CSVGeneratorService csvGeneratorService = new CSVGeneratorServiceImpl();
		csvGeneratorService.addHeader("_id", "name", "type", "latitude", "longitude");
		for (CityEntity cityEntity : cityEntities) {
			csvGeneratorService.addRow(
					String.valueOf(cityEntity.get_id()),
					cityEntity.getName(),
					cityEntity.getType(),
					cityEntity.getGeo_position().getLatitude(),
					cityEntity.getGeo_position().getLongitude());
		}

		return csvGeneratorService.generate(cityName);
	}
}
