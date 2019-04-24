/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package testConcept.ui.handler;

import org.eclipse.core.commands.IHandler;

import de.dlr.sc.virsat.project.ui.transactional.handler.ATransactionalAddStructuralElementHandler;

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * 
 * 
 */	
public abstract class  AAddStructuralElementInstanceTestStructuralElementHandler extends ATransactionalAddStructuralElementHandler implements IHandler {

	@Override
	protected String getConceptName() {
		return "testConcept";
	}

	@Override
	protected String getStructuralElementName() {
		return "testStructuralElement";
	}
}

