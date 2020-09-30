/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.excel;

/**
 * Abstract class for EXCEL IO that carries information about the Sheet layouts
 */
public abstract class AExcelIo {

	public static final int HEADER_ROW_STRUCTURALELEMENT_UUID = 4;
	public static final int HEADER_ROW_STRUCTURALELEMENT_NAME = 5;
	public static final int HEADER_ROW_STRUCTURALELEMENT_TYPE = 6;
	public static final int HEADER_ROW_USER = 7;
	public static final int HEADER_ROW_DATE = 8;
	public static final int HEADER_ROW_TIME = 9;

	public static final int COMMON_COLUMN_UUID = 0;
	public static final int COMMON_COLUMN_DELETE = 1;
	public static final int COMMON_ROW_START_TABLE = 4;
	public static final String COMMON_DELETEMARK_VALUE = "1.0";

	public static final String TEMPLATE_SHEETNAME_HEADER = "Header";
}
