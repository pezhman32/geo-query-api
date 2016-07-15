package com.goeuro.test.IO;

/**
 * Some basic user IO operations
 *
 * @author Pezhman Jahanmard
 */
public interface IO {
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
