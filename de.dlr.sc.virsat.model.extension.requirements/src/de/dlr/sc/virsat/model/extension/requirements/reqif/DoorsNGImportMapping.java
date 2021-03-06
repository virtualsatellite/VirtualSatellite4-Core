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
 * Mapping implementation that maps the default Doors NG requirement attributes to native attribute
 * implementations in out concept 
 *
 */
public class DoorsNGImportMapping implements INativeRequirementAttributeMapping {

	public static final String DOORS_STATUS_ATTRIBUTE_NAME = "Status";
	
	@Override
	public boolean isNativeAttribute(AttributeDefinition reqIFAttDef) {
		if (reqIFAttDef.getLongName().equals(DOORS_STATUS_ATTRIBUTE_NAME)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isIdentifierAttribute(AttributeDefinition reqIFAttDef) {
		return false;
	}

	@Override
	public Command setNativeValues(EditingDomain editingDomain, Requirement requirement, AttributeValue attValue) {
		// TODO Import status attribute
		return null;
	}
	
	


}
