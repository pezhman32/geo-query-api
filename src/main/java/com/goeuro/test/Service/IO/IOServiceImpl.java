package com.goeuro.test.Service.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pezhman Jahanmard
 */
public class IOServiceImpl implements IOService {
	private static final Logger LOGGER = Logger.getLogger(IOServiceImpl.class.getName());

	/**
	 * @param toOutput to be printed out
	 */
	public void out(String toOutput) {
		System.out.println(toOutput);
	}

	/**
	 * @param toAsk to be printed out before reading user's input
	 * @return user's input
	 */
	public String read(String toAsk) {
		out(toAsk);
		return read();
	}

	/**
	 * @return user's input
	 */
	public String read() {
		String input = "";
		try{
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			input = bufferRead.readLine();
		} catch(IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		return input;
	}
}
