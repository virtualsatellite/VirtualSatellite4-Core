/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util;

import org.eclipse.emf.edit.command.CommandParameter;


/**
 * This class extends the ApplicableFor checks to be used with EMF CommandParameters
 * @author fisc_ph
 *
 */
public class DVLMCommandParameterApplicableForCheck extends DVLMApplicableForCheck {

	/**
	 * Constructor
	 * @param commandParameter The owning object on which the check should be executed
	 */
	public DVLMCommandParameterApplicableForCheck(CommandParameter commandParameter) {
		super(commandParameter.getOwner(), commandParameter.getEReference() != null && commandParameter.getEReference().isContainment());
	}

	/**
	 * Call this method to check if the current CommandParameter would lead to a conflict with the
	 * Applicable For mechanism in the DVLM data model
	 * @param commandParameter The commandParameter of any EMF COmmand
	 * @return true in case the command parameters will not conflict
	 */
	public boolean isValidCommandParameter(CommandParameter commandParameter) {
		// At the moment we only check commands in case they are trying to add something
		// to containments. For referencing properties we should consider some other mechanism
		// but this is not yet implemented.
		if (commandParameter.getEReference() != null) {
			boolean isValidAsCollection = isValidObjectCollection(commandParameter.getCollection());
			Object valueObject = commandParameter.getValue();
			boolean isValidAsValue = (valueObject ==  null) ? true : isValidObject(valueObject);
			return isValidAsCollection & isValidAsValue;
		} 
		
		// All other references are most likely fine for us
		return true;
	}
}
