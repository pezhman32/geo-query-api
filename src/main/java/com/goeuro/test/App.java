package com.goeuro.test;

import com.goeuro.test.API.APIException;
import com.goeuro.test.API.GoEuroAPI;
import com.goeuro.test.API.GoEuroAPIImpl;
import com.goeuro.test.IO.IO;
import com.goeuro.test.IO.IOImpl;

public class App {

    public static void main(String[] args) {
	    IO io = new IOImpl();
	    String cityName = io.read("Please enter a city name:");

	    GoEuroAPI api = new GoEuroAPIImpl();
	    try {
		    api.getCities(cityName);
	    } catch (APIException e) {
		    e.printStackTrace();
	    }
    }
}
