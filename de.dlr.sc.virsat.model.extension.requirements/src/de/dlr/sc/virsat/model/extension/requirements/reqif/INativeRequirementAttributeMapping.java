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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.rmf.reqif10.AttributeDefinition;
import org.eclipse.rmf.reqif10.AttributeValue;

import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;

/**
 * Interface for mapping implementations of imported requirement attributes to native attributes
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
	
	/**
	 * Specify if the attribute is an identifying attribute
	 * 
	 * @param reqIFAttDef the attribute under question
	 * @return true iff the attribute is an identifier attribute
	 */
	boolean isIdentifierAttribute(AttributeDefinition reqIFAttDef);
	
	/**
	 * Enables to fix a specific attribute to some table column
	 * 
	 * @param reqIfAttDef the ReqIf attribute definition
	 * @return the integer index at which the attribute should be located or null if not relevant
	 */
	Integer getNativeIndex(AttributeDefinition reqIfAttDef);
	
	/**
	 * Import a requirement value into its native requirement implementation
	 * 
	 * @param editingDomain the editing domain for the import
	 * @param requirement the requirement in which values should be imported
	 * @param attValue the attribute value
	 * @return the command to be executed
	 */
	Command setNativeValues(EditingDomain editingDomain, Requirement requirement, AttributeValue attValue);
	
	/**
	 * Import a requirement value into its native requirement implementation
	 * 
	 * @param requirement the requirement in which values should be imported
	 * @param attValue the attribute value
	 */
	void setNativeValues(Requirement requirement, AttributeValue attValue);
	
}
