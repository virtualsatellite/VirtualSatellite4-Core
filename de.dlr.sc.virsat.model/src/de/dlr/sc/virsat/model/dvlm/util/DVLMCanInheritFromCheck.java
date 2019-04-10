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

import de.dlr.sc.virsat.model.dvlm.util.rules.DVLMCanInheritFromRuleStructuralElementInstanceToStructuralElementInstance;

/**
 * This class implements some methods to check for the "ApplicableFor" modeling paradigm in the DVLM.
 * For example if we have to Structural Elements Type A and Type B. And a Category C which is set to be 
 * Applicable for A. Then it should only be instantiable/attachable A. B should not accept it. This class helps
 * to determine the compatibility of objects following the given logic by the DVLM data model 
 * @author fisc_ph
 *
 */
public class DVLMCanInheritFromCheck extends ADVLMExtendedModelCapabilityCheck {
	
	/**
	 * Constructor of the ValidityChecker place the object which is he container here.
	 * @param owner The Container Object in most cases the StructuralElementInstance
	 * @param containment set to true if the owner is considered the container for the tested objects
	 */
	public DVLMCanInheritFromCheck(Object owner, boolean containment) {
		super(owner, containment);
		rules.add(new DVLMCanInheritFromRuleStructuralElementInstanceToStructuralElementInstance());
	}
}
