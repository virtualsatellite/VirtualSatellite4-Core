/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util.rules;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.exception.DVLMMissingTypeException;

/**
 * This rule is used to check if a StructuralElementInstance can be nested as root object to a given Repository
 * @author fisc_ph
 *
 */
public class DVLMApplicableForRuleStructuralElementInstanceInRepository implements IDVLMExtendedModelCapabilityRule {

	@Override
	public boolean isValid(Object owner, Object object) {
		// In case the owner is a repository it should only be added if it is set to
		// be a root structural element as well
		StructuralElementInstance addStructuralElementInstance = (StructuralElementInstance) object;
		StructuralElement addStructuralElement = addStructuralElementInstance.getType();
		
		DVLMMissingTypeException.checkAndThrowMissingStructuralElement(addStructuralElementInstance);
		
		// In case nothing is defined in the ApplicableFor attribute, The StructuralElement
		// can be assigned everywhere. 
		return addStructuralElement.isIsRootStructuralElement();
	}

	@Override
	public boolean canExecute(Object owner, Object object, boolean containment) {
		return (owner instanceof Repository) && (object instanceof StructuralElementInstance) && (!containment);
	}
}
