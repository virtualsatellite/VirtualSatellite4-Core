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

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * A class to read a CSV file and pars it to lists
 *
 */
public class CsvFileReader {

	
	public static final String CSV_DEFAULT_SPLIT_STRING = ";";
	
	private String separator;
	private int headerLine;
	private int dataStartLine;
	private int dataEndLine;

	/**
	 * Default constructor
	 */
	public CsvFileReader() {
		this.separator = CSV_DEFAULT_SPLIT_STRING;
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
		this.separator = separator;
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
	 * @throws IOException throws an IO exception
	 */
	public List<List<String>> readCsvFile(String filePath, int startLine, int endLine) throws IOException {
		List<List<String>> csvContentMatrix = new ArrayList<List<String>>();

		Path csvFilePath = Paths.get(filePath);
		
		Reader fr = new FileReader(csvFilePath.toFile());
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter(getSeparator().charAt(0)).parse(fr);
		
		int lineNumber = 0;
		for (CSVRecord record : records) {
			if (lineNumber >= startLine) {
				List<String> reqData = new ArrayList<String>();
				for (String att : record) {
					reqData.add(att);
				}
				csvContentMatrix.add(reqData);
			}
			lineNumber++;
			if (endLine != -1 && lineNumber > endLine) {
				break;
			}
		}
		
		return csvContentMatrix;

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
		this.separator = separator;
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
