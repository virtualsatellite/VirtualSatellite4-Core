/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.excel;

import de.dlr.sc.virsat.excel.AExcelIo;

public abstract class AExcelCostIO extends AExcelIo {


	public static final int COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_NAME = 2;
	public static final int COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_TYPE = 3;
	public static final int COSTEQUIPMENT_CULUMN_COSTEQUIPMENT_COST = 4;
	public static final int COSTEQUIPMENT_CULUMN_COSTEQUIPMENT_COSTWITHMARGIN = 5;
	public static final int COSTEQUIPMENT_CULUMN_COSTEQUIPMENT_COSTMARGIN = 6;
	public static final int COSTEQUIPMENT_CULUMN_COSTEQUIPMENT_MARGIN = 7;
	public static final int COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_FQN = 8;
	

	public static final int COSTTYPES_COLUMN_COSTTYPE_NAME = 2;
	
	public static final String TEMPLATE_SHEETNAME_COSTTYPES = "CostTypes";
	public static final String TEMPLATE_SHEETNAME_COSTEQUIPMENTS = "CostEquipments";

}
