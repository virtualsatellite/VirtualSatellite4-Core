/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.excel.importer;

import de.dlr.sc.virsat.excel.fault.IFaultType;

/**
* Enumerator for the possible fault types which can happen during statemaschine import
*/
public enum StatFaultType implements IFaultType {
		 STATE_NAME_IS_NOT_SET,
		 STATE_UUID_NOT_FOUND,
		 CANT_DELETE_NON_EXISTING_TRANSITION,
		 CANT_DELETE_NON_EXISTING_STATE,
		 TRANSITION_UUID_NOT_FOUND,
		 FROM_STATE_NOT_FOUND,
		 TO_STATE_NOT_FOUND;
}
