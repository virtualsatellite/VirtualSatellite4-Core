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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.dlr.sc.virsat.model.dvlm.util.rules.IDVLMExtendedModelCapabilityRule;

/**
 * This class provides the general functionality to encapsulate extended model rules into some
 * static checks that can be handed over to the generator. This class is used for the applicable for
 * or can inherit from functionalities that we provide in our data model
 * 
 * @author fisc_ph
 *
 */
public abstract class ADVLMExtendedModelCapabilityCheck {

	protected final Object owner;
	protected final boolean containment;
	protected final Set<IDVLMExtendedModelCapabilityRule> rules;

	/**
	 * @param owner the owning object for the rule
	 * @param containment if it is a containment
	 */
	public ADVLMExtendedModelCapabilityCheck(Object owner, boolean containment) {
		this.owner = owner;
		this.containment = containment;
		this.rules = new HashSet<>();
	}

	/**
	 * This method checks a whole collection of objects if they are applicable for the current owner.
	 * @param collection the collection of objects to be checked
	 * @return true in case they are ALL applicable to the owner
	 */
	public boolean isValidObjectCollection(Collection<?> collection) {
		if (collection == null) {
			return true;
		}
		
		// If only one object is not valid we consider the whole collection to be invalid
		for (Object object :  collection) {
			if (!isValidObject(object)) {
				return false;
			}
		}
		// At this point we know that the whole collection is valid.
		return true;
	}

	/**
	 * Use this method to check if the given object is applicable for the current owner
	 * @param object the object which should be checked if it is applicable or not. Usually a CategoryAssignment or RelationInstance  is placed here
	 * @return true in case it is applicable, false if not. But this method returns true in case no rule was executed
	 */
	public boolean isValidObject(Object object) {
		// In case we want to add objects to a StructuralElementInstance
		// We have to check if they are actually allowed to be placed there
		// we will have to check each rule for the given combination of objects
		for (IDVLMExtendedModelCapabilityRule rule : rules) {
			if (rule.canExecute(owner, object, this.containment)) {
				if (!rule.isValid(owner, object)) {
					return false;
				}
			}
		}
		
		return true;
	}

}