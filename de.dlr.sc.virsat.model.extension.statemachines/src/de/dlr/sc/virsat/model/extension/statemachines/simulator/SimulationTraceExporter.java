/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.simulator;

import java.util.Date;
import java.util.PriorityQueue;
import java.io.File;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class SimulationTraceExporter {
	
	private static final String FILE_NAME = "SimulationTrace_";
	private static final String STATE_COL = "Current Id";
	private static final String STATENAME_COL = "State";
	private XSSFSheet spreadsheet; 
	private XSSFWorkbook workbook;
	
	/**
	 * Creates template for simulation Sequence export
	 */
	public SimulationTraceExporter() {
		workbook = new XSSFWorkbook();
		spreadsheet = workbook.createSheet(FILE_NAME);
		XSSFRow row = spreadsheet.createRow(0);
		Cell stateNumber = row.createCell(0);
		stateNumber.setCellValue(STATE_COL);
		Cell stateName = row.createCell(1);
		stateName.setCellValue(STATENAME_COL);

	}

	/**
	 * Exports a simulation sequence
	 * @param simulationhistory
	 */
	public void export(PriorityQueue<String> simulationhistory) {
		int statecounter = 1;
		for (String state : simulationhistory) {
			XSSFRow row = spreadsheet.createRow(statecounter);
            Cell stateNumber = row.createCell(0);
            stateNumber.setCellValue("State " + statecounter);
            Cell stateName = row.createCell(1);
            stateName.setCellValue(state);
            statecounter++;
        }
		
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File(FILE_NAME + new Date().getTime() + ".xlsx"));
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
