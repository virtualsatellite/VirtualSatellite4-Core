/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Common signature for all bean properties that support units such as INTs
 * floats and also the enumerator beans
 * @author fisc_ph
 *
 */
public interface IBeanUnitProperty {

	/**
	 * Returns the unit name
	 * @return unit name string
	 */
	String getUnit();

	/**
	 * This method allows to set a unit by a given name
	 * @param unitName the name of the unit to be set, do not use the symbol name
	 * @return true in case the unit could be set, false in case the unit was not changed
	 */
	boolean setUnit(String unitName);

	/**
	 * This method creates a command to change the unit of the bean
	 * @param ed the editing domain to be used when creating the command
	 * @param unitName the name of the unit but not the symbol name
	 * @return the command that changes the unit. Can lead to an UnexecutableCommand in case the unit cannot be changed.
	 */
	Command setUnit(EditingDomain ed, String unitName);

}