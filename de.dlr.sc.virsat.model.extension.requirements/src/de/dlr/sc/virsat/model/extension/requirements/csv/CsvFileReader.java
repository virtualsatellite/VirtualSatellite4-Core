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
	
	protected Iterable<CSVRecord> records = null;

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
	 * Parse the given CSV file
	 * @param filePath the path of the file as string
	 * @throws IOException throws an exception if the file could not be loaded
	 */
	public void parseFile(String filePath) throws IOException {
		Path csvFilePath = Paths.get(filePath);
		Reader fr = new FileReader(csvFilePath.toFile());
		records = CSVFormat.EXCEL.withDelimiter(getSeparator().charAt(0)).parse(fr);
		fr.close();
	}
	
	/**
	 * Read the headline of CSV file
	 * 
	 * @param filePath the path to the CSV file
	 * @return the list of columns
	 * @throws IOException throws exception if file could not be read
	 */
	public List<String> readCsvHeadline() {
		return readCsvFile(headerLine, headerLine).get(0);
	}
	
	/**
	 * Read the data within a CSV file
	 * @param filePath the path to the file
	 * @return a matrix of strings
	 * @throws IOException throws IO exception if file could not be read
	 */
	public List<List<String>> readCsvData() {
		return readCsvFile(dataStartLine, dataEndLine);
	}
	
	/**
	 * Reads a CSV values and returns them as matrix. File has to be parsed before.
	 * 
	 * @param startLine the first line number to read
	 * @param endLine the last line number to read
	 * @return the CSV content as matrix of two lists
	 */
	public List<List<String>> readCsvFile(int startLine, int endLine) {
		List<List<String>> csvContentMatrix = new ArrayList<List<String>>();
		
		if (records == null) {
			return csvContentMatrix;
		}
		
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

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public int getHeaderLine() {
		return headerLine;
	}

	public void setHeaderLine(int headerLine) {
		this.headerLine = headerLine;
	}

	public int getDataStartLine() {
		return dataStartLine;
	}

	public void setDataStartLine(int dataLine) {
		this.dataStartLine = dataLine;
	}

	public int getDataEndLine() {
		return dataEndLine;
	}

	public void setDataEndLine(int dataEndLine) {
		this.dataEndLine = dataEndLine;
	}

}
