/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.propertyTester;

import org.eclipse.core.expressions.PropertyTester;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.structure.command.CreateRemoveSeiWithFileStructureCommand;
import de.dlr.sc.virsat.project.ui.navigator.handler.DeleteStructuralElementInstanceHandler;

/**
 * Checks if the user is allowed to remove a selected SEI and all its children
 */

public class RemoveStructuralElementInstanceTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (!(receiver instanceof StructuralElementInstance)) {
			return false;
		}
		
		StructuralElementInstance sei = (StructuralElementInstance) receiver;
		if (sei.eResource() == null) {
			return false;
		}
		
		return CreateRemoveSeiWithFileStructureCommand
			.create(sei, DeleteStructuralElementInstanceHandler.DELETE_RESOURCE_OPERATION_FUNCTION)
			.canExecute();
	}
}
