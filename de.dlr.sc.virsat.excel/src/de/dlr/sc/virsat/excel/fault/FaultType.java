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
* Enumerator for the possible fault types which can happen during the import
*/
public enum FaultType implements IFaultType {
	 STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH,
	 STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH,
	 DELETE_COLUMN_CAN_BE_EMPTY_OR_1;
}
