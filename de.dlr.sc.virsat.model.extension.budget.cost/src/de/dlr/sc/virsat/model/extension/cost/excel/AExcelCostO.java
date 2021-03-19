/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.cost.excel;

import de.dlr.sc.virsat.excel.AExcelIo;

public abstract class AExcelCostO extends AExcelIo {

	public static final int INTERFACE_COLUMN_INTERFACE_TO = 4;

	public static final int INTERFACEEND_COLUMN_INTERFACEEND_NAME = 2;
	public static final int INTERFACEEND_COLUMN_INTERFACEEND_TYPE = 3;
	public static final int INTERFACEEND_COLUMN_INTERFACEEND_FQN = 4;
	
	public static final int INTERFACE_COLUMN_INTERFACE_NAME = 2;
	public static final int INTERFACE_COLUMN_INTERFACE_FROM = 3;
	public static final int INTERFACETYPES_COLUMN_INTERFACETYPE_NAME = 2;
	
	public static final String TEMPLATE_SHEETNAME_INTERFACETYPES = "InterfaceTypes";
	public static final String TEMPLATE_SHEETNAME_INTERFACEENDS = "InterfaceEnds";
	public static final String TEMPLATE_SHEETNAME_INTERFACES = "Interfaces";
}
