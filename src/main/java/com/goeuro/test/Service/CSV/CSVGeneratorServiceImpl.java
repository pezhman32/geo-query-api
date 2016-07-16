package com.goeuro.test.Service.CSV;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple service to generate a simple CSV file without using any external library
 *
 * @author Pezhman Jahanmard
 */
public class CSVGeneratorServiceImpl implements CSVGeneratorService {
	private static final Logger LOGGER = Logger.getLogger(CSVGeneratorServiceImpl.class.getName());

	private List<String> headers = new ArrayList<String>();
	private ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
	private String locationToSave;

	public CSVGeneratorServiceImpl() {
	}

	public CSVGeneratorServiceImpl(String completeLocationToSave) throws CSVGeneratorException {
		setOutputLocation(completeLocationToSave);
	}

	/**
	 * Adds header items, you can call this method multiple times or one time with multiple number of headers
	 */
	public void addHeader(String... headers) throws CSVGeneratorException {
		if (headers == null) {
			throw new CSVGeneratorException("Cannot add null header");
		}

		this.headers.addAll(Arrays.asList(headers));
	}

	/**
	 * Adds all columns of a single row
	 */
	public void addRow(String... completeRow) {
		ArrayList row = new ArrayList();
		row.addAll(Arrays.asList(completeRow));
		rows.add(row);
	}

	/**
	 * You can set output location (path) by this method
	 */
	public CSVGeneratorService setOutputLocation(String path) throws CSVGeneratorException {
		if (!new File(path).isDirectory()) {
			throw new CSVGeneratorException("Given path must be a directory.");
		}

		locationToSave = path;
		return this;
	}

	/**
	 * returns default location to save the output file (you can use it to suggest it as default value to user)
	 */
	public String getDefaultLocationToSave() {
		return System.getProperty("user.dir");
	}

	/**
	 * You must call this method in order to generate the CSV file
	 */
	public void generate() throws CSVGeneratorException {
		if (headers.size() == 0) {
			throw new CSVGeneratorException("You need to have some headers.");
		}

		if (rows.size() == 0) {
			throw new CSVGeneratorException("You need to have some rows.");
		}

		String outputLocation = locationToSave;
		if (StringUtils.isBlank(outputLocation)) {
			outputLocation = getDefaultLocationToSave();
		}
		outputLocation += File.separator + fileNameGenerator();

		//add headers:
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < headers.size(); i++) {
			if (i != 0) {
				builder.append(",");
			}
			builder.append(headers.get(i));
		}

		builder.append("\n");
		//add rows:
		for (ArrayList<String> row : rows) {
			//add columns:
			for (int i = 0; i < row.size(); i++) {
				if (i != 0) {
					builder.append(",");
				}
				builder.append(row.get(i) != null ? row.get(i).replace(",", "\\,") : "");
			}
		}

		LOGGER.log(Level.INFO, "Generate output CSV to " + outputLocation);
		File output = new File(outputLocation);
		try {
			FileOutputStream outputStream = new FileOutputStream(output);
			outputStream.write(builder.toString().getBytes("UTF-8"));
			outputStream.close();
		} catch (Exception e) {
			throw new CSVGeneratorException(e.getMessage(), e);
		}

		LOGGER.log(Level.INFO, "File saved!");
	}

	private String fileNameGenerator() {
		return String.valueOf(new java.util.Date().getTime()) + ".csv";
	}
}
