/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.thermal.ui.handler;

import org.eclipse.core.commands.IHandler;

import de.dlr.sc.virsat.project.ui.transactional.handler.ATransactionalAddStructuralElementHandler;

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Maximum mesh element sizes for components can be defined here.
 * 
 */	
public abstract class  AAddStructuralElementInstanceThermalAnalysisResultsHandler extends ATransactionalAddStructuralElementHandler implements IHandler {

	@Override
	protected String getConceptName() {
		return "de.dlr.sc.virsat.model.extension.thermal";
	}

	@Override
	protected String getStructuralElementName() {
		return "ThermalAnalysisResults";
	}
}
