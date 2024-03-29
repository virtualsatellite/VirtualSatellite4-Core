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
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.rmf.reqif10.AttributeDefinition;
import org.eclipse.rmf.reqif10.AttributeValue;

import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;

/**
 * Mapping implementation that maps the default Doors NG requirement attributes to native attribute
 * implementations in out concept 
 *
 */
public class DoorsNGImportMapping implements INativeRequirementAttributeMapping {

	public static final String DOORS_STATUS_ATTRIBUTE_NAME = "Status";
	public static final String DOORS_NAME_ATTRIBUTE_NAME = "ReqIF.Name";
	public static final String DOORS_ID_ATTRIBUTE_NAME = "ReqIF.ForeignID";
	public static final String DOORS_TEXT_ATTRIBUTE_NAME = "ReqIF.Text";
	
	@Override
	public boolean isNativeAttribute(AttributeDefinition reqIFAttDef) {
		if (reqIFAttDef.getLongName().equals(DOORS_STATUS_ATTRIBUTE_NAME) 
				|| reqIFAttDef.getLongName().equals(DOORS_NAME_ATTRIBUTE_NAME)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isIdentifierAttribute(AttributeDefinition reqIFAttDef) {
		if (reqIFAttDef.getLongName().equals(DOORS_ID_ATTRIBUTE_NAME)) {
			return true;
		}
		return false;
	}

	@Override
	public Command setNativeValues(EditingDomain editingDomain, Requirement requirement, AttributeValue attValue) {
		CompoundCommand cc = new CompoundCommand();
		// Import status attribute
		return cc;
	}
	
	@Override
	public void setNativeValues(Requirement requirement, AttributeValue attValue) {
		// Import status attribute
	}
	
	@Override
	public Integer getNativeIndex(AttributeDefinition reqIfAttDef) {
		if (reqIfAttDef.getLongName().equals(DOORS_TEXT_ATTRIBUTE_NAME)) {
			return 0;
		}
		return null;
	}

	

}
