package com.goeuro.test.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pezhman Jahanmard
 */
public class IOImpl implements IO {
	private static final Logger LOGGER = Logger.getLogger(IOImpl.class.getName());

	public void out(String toOutput) {
		System.out.println(toOutput);
	}

	public String read(String toAsk) {
		out(toAsk);
		return read();
	}

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
