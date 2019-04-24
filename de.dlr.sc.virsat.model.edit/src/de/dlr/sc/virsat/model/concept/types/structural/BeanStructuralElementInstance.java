/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.structural;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Convenience class to access CAs and Properties of a Sei more easily
 * @author fisc_ph
 *
 */
public class BeanStructuralElementInstance extends ABeanStructuralElementInstance {

	/**
	 * Default constructor
	 */
	public BeanStructuralElementInstance() {
		
	}
	
	/**
	 * Convenience constructor that directly wraps the passed sei
	 * @param sei the structural element instance to be wrapped by this bean
	 */
	public BeanStructuralElementInstance(StructuralElementInstance sei) {
		setStructuralElementInstance(sei);
	}
	
	@Override
	public String getFullQualifiedSturcturalElementName() {
		return null;
	}
}
