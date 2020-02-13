/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.ui.handler;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.project.ui.transactional.handler.ATransactionalAddCategoryHandler;
import de.dlr.sc.virsat.model.extension.tests.ui.command.CreateAddEReferenceTestCommand;

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * 
 * 
 */	
public abstract class AAddEReferenceTestHandler extends ATransactionalAddCategoryHandler {
	
	@Override
	protected String getConceptName() {
		return "de.dlr.sc.virsat.model.extension.tests";
	}
	
	@Override
	protected Command createAddCommand(EditingDomain editingDomain, EObject owner, Concept activeConcept) {
		return new CreateAddEReferenceTestCommand().create(editingDomain, owner, activeConcept);
	}
}
