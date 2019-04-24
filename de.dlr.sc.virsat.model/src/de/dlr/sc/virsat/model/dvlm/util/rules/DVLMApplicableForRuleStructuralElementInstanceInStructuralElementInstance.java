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

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.exception.DVLMMissingTypeException;

/**
 * This rule checks if a new StructuralElementInstance is applicable for a given StructuralElementInstance
 * @author fisc_ph
 *
 */
public class DVLMApplicableForRuleStructuralElementInstanceInStructuralElementInstance extends ADVLMExtendedModelCapabilityRuleXInStructuralElementInstance implements IDVLMExtendedModelCapabilityRule {

	@Override
	public boolean isValid(Object owner, Object object) {
		StructuralElement owningStructuralElement = getStructuralElementFromOwner(owner);
		
		StructuralElementInstance addStructuralElementInstance = (StructuralElementInstance) object;
		StructuralElement addStructuralElement = addStructuralElementInstance.getType();
		
		DVLMMissingTypeException.checkAndThrowMissingStructuralElement(addStructuralElementInstance);
	
		if (addStructuralElement.isIsApplicableForAll()) {
			return true;
		} else {			
			return addStructuralElement.getApplicableFor().contains(owningStructuralElement);
		}
	}

	@Override
	public boolean canExecute(Object owner, Object object, boolean containment) {
		return super.canExecute(owner, object, containment) && object instanceof StructuralElementInstance && containment;
	}
}
