package com.goeuro.test;

import com.goeuro.test.API.APIException;
import com.goeuro.test.Service.CSV.CSVGeneratorException;
import com.goeuro.test.Service.CitySearch.CitySearchService;
import com.goeuro.test.Service.CitySearch.CitySearchServiceException;
import com.goeuro.test.Service.CitySearch.CitySearchServiceImpl;
import com.goeuro.test.Service.IO.IOService;
import com.goeuro.test.Service.IO.IOServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	private static IOService ioService = new IOServiceImpl();

    public static void main(String[] args) {
	    String savedFilePath = null;
	    String toAsk = "Please enter a city name (required):";
	    do {
		    try {
			    String userInput = getUserInput(args, toAsk);
			    CitySearchService citySearchService = new CitySearchServiceImpl();
			    savedFilePath = citySearchService.find(userInput);
		    } catch (APIException | CitySearchServiceException | CSVGeneratorException e) {
			    LOGGER.log(Level.WARNING, e.getMessage(), e);
			    toAsk = "Unfortunately there was an error during the process, Please re-enter a city name to search:";
		    }

		    //if savedFilePath is blank it means we had some errors and we can ask user to re-enter a city name
	    } while (StringUtils.isBlank(savedFilePath));
    }

	/**
	 * Checks args[] and if it was empty then get input from user
	 * @param output something to ask user
	 * @return user input
	 */
	private static String getUserInput(String[] args, String output) {
		String userInput = args == null || args.length == 0 ? null : args[0];
		while (StringUtils.isBlank(userInput)) {
			userInput = ioService.read(output);
		}

		return userInput;
	}
}
