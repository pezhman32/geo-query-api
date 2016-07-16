package com.goeuro.test.Service.CSV;

/**
 * Simple service to generate a simple CSV file without using any external library
 *
 * @author Pezhman Jahanmard
 */
public interface CSVGeneratorService {
	/**
	 * Adds header items, you can call this method multiple times or one time with multiple number of headers
	 */
	void addHeader(String... headers) throws CSVGeneratorException;

	/**
	 * Adds all columns of a single row
	 */
	void addRow(String... completeRow);

	/**
	 * You can set output location (path) by this method
	 */
	CSVGeneratorService setOutputLocation(String completeLocation) throws CSVGeneratorException;

	/**
	 * returns default location to save the output file (you can use it to suggest it as default value to user)
	 */
	String getDefaultLocationToSave();

	/**
	 * You must call this method in order to generate the CSV file
	 */
	void generate() throws CSVGeneratorException;
}
