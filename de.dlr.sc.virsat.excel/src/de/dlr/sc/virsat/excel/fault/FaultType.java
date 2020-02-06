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

public enum FaultType {
	 STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH,
	 STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH,
	 DELETE_COLUMN_CAN_BE_EMPTY_OR_1,
	 
	 //funcelectrical
	 INTERFACE_END_UUID_NOT_FOUND,	 
	 INTERFACE_TYPE_DOES_NOT_EXIST,
	 INTERFACE_UUID_NOT_FOUND,
	 TO_INTERFACE_END_NOT_FOUND,
	 INTERFACE_NAME_IS_NOT_SET,
	 INTERFACE_TYPE_UUID_NOT_FOUND,
	 INTERFACE_TYPE_NAME_IS_NOT_SET,
	 INTERFACE_END_NAME_IS_NOT_SET,
	 CANT_DELETE_NON_EXISTING_INTERFACE_END,
	 CANT_DELETE_NON_EXISTING_INTERFACE_TYPE,
	 CANT_DELETE_NON_EXISTING_INTERFACE,
	 CAN_ONLY_IMPORT_ELEMENT_DEFINITON_OR_INTERFACE_TYPE_COLLECTION,
	 FROM_INTERFACE_END_NOT_FOUND,
	 
	 //statemaschine
	 STATE_NAME_IS_NOT_SET,
	 STATE_UUID_NOT_FOUND,
	 CANT_DELETE_NON_EXISTING_TRANSITION,
	 CANT_DELETE_NON_EXISTING_STATE,
	 TRANSITION_UUID_NOT_FOUND,
	 FROM_STATE_NOT_FOUND,
	 TO_STATE_NOT_FOUND;
}