/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class to read a CSV file and pars it to lists
 *
 */
public class CsvFileReader {

	private static final String CSV_SPLIT_STRING = ";";
	
	//Regex to escape separators within double qoutes 
	private static final String REGEX_ESCAPE_SPLIT = "(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
	
	private String separator;
	private int headerLine;
	private int dataStartLine;
	private int dataEndLine;

	/**
	 * Default constructor
	 */
	public CsvFileReader() {
		this.separator = CSV_SPLIT_STRING + REGEX_ESCAPE_SPLIT;
		this.headerLine = 0;
		this.dataStartLine = 1;
		this.dataEndLine = -1;
	}
	
	/**
	 * Constructor with CSV parameters
	 * @param separator the separator String
	 * @param headerLine the number of the headline (starting from 0)
	 * @param dataStartLine the number of the first data line (starting from 0)
	 * @param dataEndLine the number of the last data line (starting from 0)
	 */
	public CsvFileReader(String separator, int headerLine, int dataStartLine, int dataEndLine) {
		this.separator = separator + REGEX_ESCAPE_SPLIT;
		this.headerLine = headerLine;
		this.dataStartLine = dataStartLine;
		this.dataEndLine = dataEndLine;
	}
	
	/**
	 * Read the headline of CSV file
	 * 
	 * @param filePath the path to the CSV file
	 * @return the list of columns
	 * @throws IOException throws exception if file could not be read
	 */
	public List<String> readCsvHeadline(String filePath) throws IOException {
		return readCsvFile(filePath, headerLine, headerLine).get(0);
	}
	
	/**
	 * Read the data within a CSV file
	 * @param filePath the path to the file
	 * @return a matrix of strings
	 * @throws IOException throws IO exception if file could not be read
	 */
	public List<List<String>> readCsvData(String filePath) throws IOException {
		return readCsvFile(filePath, dataStartLine, dataEndLine);
	}
	
	/**
	 * Reads a CSV file and returns it as matrix
	 * 
	 * @param filePath
	 *            the file path to the CSV file
	 * @param startLine the first line number to read
	 * @param endLine the last line number to read
	 * @return the CSV content as matrix of two lists
	 * @throws IOException throws an IO excetption
	 */
	public List<List<String>> readCsvFile(String filePath, int startLine, int endLine) throws IOException {
		List<List<String>> csvContentMatrix = new ArrayList<List<String>>();

		Path csvFilePath = Paths.get(filePath);
		
		// Find out number of columns
		int maxNumberOfColumns = getMaxNumberColumns(csvFilePath);
		FileReader fr = new FileReader(csvFilePath.toFile());
		BufferedReader br = new BufferedReader(fr);
		String currentReqData = "";
		
		int currentLineNumber = 0;
		String line = "";
		while ((line = br.readLine()) != null) {

			if (currentLineNumber >= startLine) {
				currentReqData += line;
				int numberOfColumns = currentReqData.length() - currentReqData.replaceAll(separator, "").length();
				if (numberOfColumns >= maxNumberOfColumns - 1) {
					//Only write data into list if data set is complete
					String[] requirement = currentReqData.split(separator);
					csvContentMatrix.add(Arrays.asList(requirement));
					currentReqData = "";
				} 
			}
			
			currentLineNumber++;
			
			if (endLine != -1 && currentLineNumber > endLine) {
				break;
			}
			
		}
		br.close();
		
		return csvContentMatrix;

	}
	
	/**
	 * Get the max number of columns within this file
	 * @param filePath the file path
	 * @return the number of columns
	 * @throws IOException throws IOexception
	 */
	protected int getMaxNumberColumns(Path filePath) throws IOException {
		FileReader fr = new FileReader(filePath.toFile());
		BufferedReader br = new BufferedReader(fr);
		String line = "";

		// Find out number of columns
		int maxNumberOfColumns = 0;
		while ((line = br.readLine()) != null) {
			int currentSize = line.length() - line.replaceAll(separator, "").length();
			maxNumberOfColumns = Integer.max(currentSize, maxNumberOfColumns);
		}

		// Assume a data line always has all columns
		br.close();
		return maxNumberOfColumns + 1; // Number of columns is one higher than number of separators
	}

	/**
	 * @return the separator
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * @param separator the separator to set
	 */
	public void setSeparator(String separator) {
		this.separator = separator + REGEX_ESCAPE_SPLIT;
	}

	/**
	 * @return the headerLine
	 */
	public int getHeaderLine() {
		return headerLine;
	}

	/**
	 * @param headerLine the headerLine to set
	 */
	public void setHeaderLine(int headerLine) {
		this.headerLine = headerLine;
	}

	/**
	 * @return the dataLine
	 */
	public int getDataStartLine() {
		return dataStartLine;
	}

	/**
	 * @param dataLine the dataLine to set
	 */
	public void setDataStartLine(int dataLine) {
		this.dataStartLine = dataLine;
	}

	/**
	 * @return the dataEndLine
	 */
	public int getDataEndLine() {
		return dataEndLine;
	}

	/**
	 * @param dataEndLine the dataEndLine to set
	 */
	public void setDataEndLine(int dataEndLine) {
		this.dataEndLine = dataEndLine;
	}

	
}
