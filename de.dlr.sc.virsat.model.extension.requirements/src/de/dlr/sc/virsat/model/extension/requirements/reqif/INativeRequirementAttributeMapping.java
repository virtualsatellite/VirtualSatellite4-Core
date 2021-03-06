/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.reqif;

import org.eclipse.rmf.reqif10.AttributeDefinition;

/**
 * @author fran_tb
 *
 */
public interface INativeRequirementAttributeMapping {
	
	/**
	 * Specify if an imported requirement attribute has a native implementation in our concept
	 * 
	 * If a requirement attribute has a native representation in our concept, it is not added as dynamic attribute in
	 * the corresponding requirement type and its instance values are delegated to the mapping provided by implementations 
	 * of this interface
	 * 
	 * @param reqIFAttDef the reqIF attribute
	 * @return weather the attribute has a native implementation
	 */
	boolean isNativeAttribute(AttributeDefinition reqIFAttDef);
	
	
}
