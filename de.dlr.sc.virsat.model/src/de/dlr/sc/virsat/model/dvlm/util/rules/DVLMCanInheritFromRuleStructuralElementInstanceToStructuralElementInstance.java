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
 * This rule checks if a new StructuralElementInstance Can inherit from the other
 * given StructuralElementInstance. This class is used in the Extended Model
 * Capability Rule Checker which is used in specialized EMF related Lists.
 * 
 * @author fisc_ph
 *
 */
public class DVLMCanInheritFromRuleStructuralElementInstanceToStructuralElementInstance extends ADVLMExtendedModelCapabilityRuleXInStructuralElementInstance implements IDVLMExtendedModelCapabilityRule {

	@Override
	public boolean isValid(Object owner, Object object) {
		StructuralElement owningStructuralElement = getStructuralElementFromOwner(owner);
		
		StructuralElementInstance addStructuralElementInstance = (StructuralElementInstance) object;
		StructuralElement addStructuralElement = addStructuralElementInstance.getType();
		
		DVLMMissingTypeException.checkAndThrowMissingStructuralElement(addStructuralElementInstance);
	
		if (owningStructuralElement.isIsCanInheritFromAll()) {
			return true;
		} else {			
			return owningStructuralElement.getCanInheritFrom().contains(addStructuralElement);
		}
	}

	@Override
	public boolean canExecute(Object owner, Object object, boolean containment) {
		return super.canExecute(owner, object, containment) && object instanceof StructuralElementInstance;
	}
}
