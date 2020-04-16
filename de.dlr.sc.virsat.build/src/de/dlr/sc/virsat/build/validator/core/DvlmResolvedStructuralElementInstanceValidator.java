/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.validator.core;

import org.eclipse.core.resources.IMarker;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;

/**
 * Implementation of a validator that checks if an element cannot be resolved.
 * @author muel_s8
 *
 */

public class DvlmResolvedStructuralElementInstanceValidator extends ADvlmCoreValidator implements IStructuralElementInstanceValidator {

	@Override
	public boolean validate(StructuralElementInstance sei) {
		boolean validationSuccessful = !sei.eIsProxy();
		
		if (!validationSuccessful) {
			vvmHelper.createEMFValidationMarker(IMarker.SEVERITY_ERROR, "The StructuralElementInstance \'" + sei.getFullQualifiedInstanceName() + "\' cannot be resolved.", sei, null);
		} 
		
		return validationSuccessful;
	}

}
