package com.goeuro.test.Service.IO;

/**
 * Some basic user IO operations
 *
 * @author Pezhman Jahanmard
 */
public interface IOService {
	/**
	 * @param toOutput to be printed out
	 */
	void out(String toOutput);

	/**
	 * @param toAsk to be printed out before reading user's input
	 * @return user's input
	 */
	String read(String toAsk);

	/**
	 * @return user's input
	 */
	String read();
}
