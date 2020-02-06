/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.excel.fault;

/**
* Class for the possible faults which can happen during the import
*/
public class Fault {
	private FaultType faultType;
	private int lineNumber;
	private int sheetNumber;

	/**
	* Constructor for faults
	* @param faultType the type of the fault
	* @param sheetNumber the number of the sheet which fault has occurred
	* @param lineNumber the number of the line which fault has occurred
	*/
	public Fault(FaultType faultType, int sheetNumber, int lineNumber) {
		this.faultType = faultType;
		this.lineNumber = lineNumber;
		this.sheetNumber = sheetNumber;
	}

	/**
	* getter for fault type
	* @return faultType returns fault type
	*/
	public FaultType getFaultType() {
		return faultType;
	}

	/**
	* setter for fault type
	* @param faultType fault type to be set
	*/
	public void setFaultType(FaultType faultType) {
		this.faultType = faultType;
	}

	/**
	* getter for line number
	* @return lineNumber returns line Number
	*/
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	* setter for line number
	* @param lineNumber line number to be set
	*/
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	* getter for sheet number
	* @return sheetNumber returns sheet number
	*/
	public int getSheetNumber() {
		return sheetNumber;
	}

	/**
	* setter for sheet number
	* @param sheetNumber sheet number to be set
	*/
	public void setSheetNumber(int sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((faultType == null) ? 0 : faultType.hashCode());
		result = prime * result + lineNumber;
		result = prime * result + sheetNumber;
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Fault && object != null) {
			Fault other = (Fault) object;
			return faultType == other.faultType && lineNumber == other.lineNumber && sheetNumber == other.sheetNumber;
		}

		return false;
	}

	@Override
	public String toString() {
		return "Type: " + faultType + ", Sheet: " + sheetNumber + ", Line: " + lineNumber;
	}
}