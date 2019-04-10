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

/**
 * Interface for defining a Rule that can be registered to the 
 * DVLM ApplicableFor Check. To add some special behaviour or similar.
 * @author fisc_ph
 *
 */
public interface IDVLMExtendedModelCapabilityRule {

	/**
	 * Call this method to check if the objects fit together following the applicableFor metric 
	 * @param owner the owner to which the object should be applied
	 * @param object the object that should be applied to the owner
	 * @return returns true in case the object is applicable for the owner. Otherwise false
	 */
	boolean isValid(Object owner, Object object);
	
	/**
	 * This method simply tells if this rule can be executed
	 * @param owner the owner object handed over to the rule
	 * @param object the object handed over to this rule
	 * @param containment whether the owner contains the object
	 * @return true in case the rule can be executed on the given objects. True does not mean the objects are valid following the applicable for paradigm
	 */
	boolean canExecute(Object owner, Object object, boolean containment);
}
