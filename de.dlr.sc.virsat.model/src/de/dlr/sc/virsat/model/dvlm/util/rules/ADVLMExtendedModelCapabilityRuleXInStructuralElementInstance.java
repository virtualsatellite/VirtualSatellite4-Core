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
 * Abstract Rule for the AppplicableFor Check. It provides base functionality for all rules
 * that check if something is applicable for a StructuralElementInstance
 * @author fisc_ph
 *
 */
public abstract class ADVLMExtendedModelCapabilityRuleXInStructuralElementInstance implements IDVLMExtendedModelCapabilityRule {

	/**
	 * This method hands back the StructuralElement from the owner
	 * @param owner the owner object which should be a StructuralElementInstance
	 * @return the StructuralElement which has been used to Type the instance
	 */
	protected StructuralElement getStructuralElementFromOwner(Object owner) {
		StructuralElementInstance owningStructuralElementInstance = (StructuralElementInstance) owner;
		StructuralElement owningStructuralElement = owningStructuralElementInstance.getType();
			
		DVLMMissingTypeException.checkAndThrowMissingStructuralElement(owningStructuralElementInstance);
	
		return owningStructuralElement;
	}
	
	@Override
	public boolean canExecute(Object owner, Object object, boolean containment) {
		return owner instanceof StructuralElementInstance;
	}
}
