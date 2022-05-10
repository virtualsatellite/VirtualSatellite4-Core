/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.excel.importer;

import de.dlr.sc.virsat.excel.fault.IFaultType;

/**
* Enumerator for the possible fault types which can happen during cost import
*/
public enum CostFaultType implements IFaultType {
	COST_EQUIPMENT_UUID_NOT_FOUND,	 
	 COST_TYPE_DOES_NOT_EXIST,
	 COST_TYPE_UUID_NOT_FOUND,
	 COST_TYPE_NAME_IS_NOT_SET,
	 COST_EQUIPMENT_NAME_IS_NOT_SET,
	 CANT_DELETE_NON_EXISTING_COST_EQUIPMENT,
	 CANT_DELETE_NON_EXISTING_COST_TYPE,
	 CAN_ONLY_IMPORT_ELEMENT_DEFINITON_OR_COST_TYPE_COLLECTION,
}
