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

import java.util.List;

import org.eclipse.rmf.reqif10.AttributeValue;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.rmf.reqif10.SpecObject;

/**
 *
 *
 */
public interface IReqIFReader {
	
	/**
	 * Returns unique identifier of the requirement
	 * @param requirement the requirement 
	 * @return the unique identifier as string
	 */
	String getReqIdentifier(SpecHierarchy requirement);
	
	/**
	 * Returns name of the requirement
	 * @param requirement the requirement 
	 * @return the name as string
	 */
	String getReqName(SpecHierarchy requirement);
	
	/**
	 * Returns description of the requirement
	 * @param requirement the requirement 
	 * @return the description as string
	 */
	String getReqDescription(SpecHierarchy requirement);
	
	/**
	 * Returns the values of the requirement
	 * @param requirement the requirement 
	 * @return the values as list of atrributes
	 */
	List<AttributeValue> getReqValues(SpecHierarchy requirement);
	
	/**
	 * @param specObject the requirement
	 * @param columnName the desired attribute
	 * @return the value of the attribute with given column name
	 */
	String getValueWithColumnName(SpecObject specObject, String columnName);

}