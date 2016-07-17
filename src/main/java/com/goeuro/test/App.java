package com.goeuro.test;

import com.goeuro.test.API.APIException;
import com.goeuro.test.Service.CSV.CSVGeneratorException;
import com.goeuro.test.Service.CitySearch.CitySearchService;
import com.goeuro.test.Service.CitySearch.CitySearchServiceException;
import com.goeuro.test.Service.CitySearch.CitySearchServiceImpl;
import com.goeuro.test.Service.IO.IOService;
import com.goeuro.test.Service.IO.IOServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	private static IOService ioService = new IOServiceImpl();
	private static String argLogsOff = "logs=off";

    public static void main(String[] args) {
	    List<String> argsArray = new ArrayList<>(Arrays.asList(args));
	    setLogLevel(argsArray);

	    String savedFilePath = null;
	    String toAsk = "Please enter a city name (required):";
	    do {
		    try {
			    String userInput = getUserInput(argsArray, toAsk);
			    CitySearchService citySearchService = new CitySearchServiceImpl();
			    savedFilePath = citySearchService.find(userInput);
		    } catch (APIException | CitySearchServiceException | CSVGeneratorException e) {
			    LOGGER.log(Level.WARNING, e.getMessage(), e);

			    ioService.out("Unfortunately there was an error during the process: " + e.getMessage());
			    toAsk = "Please re-enter a city name to search:";
			    argsArray = null; //null out the argsArray so we can get new input from user
		    }

		    //if savedFilePath is blank it means we had some errors and we can ask user to re-enter a city name
	    } while (StringUtils.isBlank(savedFilePath));

	    ioService.out("File saved to " + savedFilePath);
    }

	/**
	 * if logs=off we set log levels = off and remove argument from argsArray
	 */
	private static void setLogLevel(List<String> argsArray) {
		if (argsArray.contains(argLogsOff)) {
			Logger rootLog = Logger.getLogger("");
			rootLog.setLevel(Level.OFF);
			rootLog.getHandlers()[0].setLevel(Level.OFF);
			//remove the arg so we won't use it as city name later
			argsArray.remove(argLogsOff);
		}
	}

	/**
	 * Checks args[] and if it was empty then get input from user
	 * @param output something to ask user
	 * @return user input
	 */
	private static String getUserInput(List<String> args, String output) {
		String userInput = args == null || args.size() == 0 ? null : args.get(0);
		while (StringUtils.isBlank(userInput)) {
			userInput = ioService.read(output);
		}

		return userInput;
	}
}
