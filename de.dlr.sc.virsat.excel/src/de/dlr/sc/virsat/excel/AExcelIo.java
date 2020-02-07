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

	public static final int HEADER_ROW_STRUCTURALELEMENTUUID = 4;
	public static final int HEADER_ROW_STRUCTURALELEMENTNAME = 5;
	public static final int HEADER_ROW_STRUCTURALELEMENTTYPE = 6;
	public static final int HEADER_ROW_USER = 7;
	public static final int HEADER_ROW_DATE = 8;
	public static final int HEADER_ROW_TIME = 9;

	public static final int COMMON_COLUMN_UUID = 0;
	public static final int COMMON_COLUMN_DELETE = 1;
	public static final int COMMON_ROW_START_TABLE = 4;
	public static final String COMMON_DELETEMARK_VALUE = "1.0";

	public static final int INTERFACE_COLUMN_INTERFACE_NAME = 2;
	public static final int INTERFACE_COLUMN_INTERFACE_FROM = 3;
	public static final int INTERFACE_COLUMN_INTERFACE_TO = 4;

	public static final int INTERFACEEND_COLUMN_INTERFACEEND_NAME = 2;
	public static final int INTERFACEEND_COLUMN_INTERFACEEND_TYPE = 3;

	public static final int INTERFACETYPES_COLUMN_INTERFACETYPE_NAME = 2;

	public static final int STATE_COLUMN_STATE_NAME = 2;

	public static final int TRANSITION_COLUMN_TRANSITION_NAME = 2;
	public static final int TRANSITION_COLUMN_TRANSITION_FROM = 3;
	public static final int TRANSITION_COLUMN_TRANSITION_TO = 4;

	public static final String TEMPLATE_SHEETNAME_HEADER = "Header";
	public static final String TEMPLATE_SHEETNAME_INTERFACETYPES = "InterfaceTypes";
	public static final String TEMPLATE_SHEETNAME_INTERFACEENDS = "InterfaceEnds";
	public static final String TEMPLATE_SHEETNAME_INTERFACES = "Interfaces";
	public static final String TEMPLATE_SHEETNAME_STATES = "States";
	public static final String TEMPLATE_SHEETNAME_TRANSITIONS = "Transitions";
}