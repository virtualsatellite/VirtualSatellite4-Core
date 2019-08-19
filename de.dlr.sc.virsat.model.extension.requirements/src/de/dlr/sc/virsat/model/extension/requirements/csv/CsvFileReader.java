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

	/**
	 * Reads a CSV file and returns it as matrix
	 * 
	 * @param filePath
	 *            the file path to the CSV file
	 * @return the CSV content as matrix of two lists
	 * @throws IOException throws an IO excetption
	 */
	public List<List<String>> readCsvFile(String filePath) throws IOException {
		List<List<String>> csvContentMatrix = new ArrayList<List<String>>();

		Path csvFilePath = Paths.get(filePath);

		String line = "";
		FileReader fr = new FileReader(csvFilePath.toFile());
		BufferedReader br = new BufferedReader(fr);

		// Find out number of columns
		int maxNumberOfColumns = 0;
		while ((line = br.readLine()) != null) {
			int currentSize = line.split(CSV_SPLIT_STRING + REGEX_ESCAPE_SPLIT).length;
			if (currentSize > maxNumberOfColumns) {
				maxNumberOfColumns = currentSize;
			}
		}

		// Assume a data line always has all columns
		fr.close();
		fr = new FileReader(csvFilePath.toFile());
		br = new BufferedReader(fr);
		String currentReqData = "";
		while ((line = br.readLine()) != null) {

			currentReqData += line;
			int numberOfColumns = currentReqData.length() - currentReqData.replaceAll(CSV_SPLIT_STRING + REGEX_ESCAPE_SPLIT, "").length();
			if (numberOfColumns == maxNumberOfColumns - 1) {
				//Only write data into list if data set is complete
				String[] requirement = currentReqData.split(CSV_SPLIT_STRING + REGEX_ESCAPE_SPLIT);
				csvContentMatrix.add(Arrays.asList(requirement));
				currentReqData = "";
			} 

		}
		br.close();
		
		return csvContentMatrix;

	}

}
