/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.excel;

import de.dlr.sc.virsat.excel.AExcelIo;

public abstract class AExcelStatIO extends AExcelIo {
	
	public static final int STATE_COLUMN_STATE_NAME = 2;

	public static final int TRANSITION_COLUMN_TRANSITION_NAME = 2;
	public static final int TRANSITION_COLUMN_TRANSITION_FROM = 3;
	public static final int TRANSITION_COLUMN_TRANSITION_TO = 4;
	
	public static final String TEMPLATE_SHEETNAME_STATES = "States";
	public static final String TEMPLATE_SHEETNAME_TRANSITIONS = "Transitions";
}
