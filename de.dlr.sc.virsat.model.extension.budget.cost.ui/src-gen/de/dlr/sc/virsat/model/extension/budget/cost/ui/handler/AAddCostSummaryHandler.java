/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.ui.handler;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.project.ui.transactional.handler.ATransactionalAddCategoryHandler;
import de.dlr.sc.virsat.model.extension.budget.cost.ui.command.CreateAddCostSummaryCommand;

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Summary of all nested equipment costs
 * 
 */	
public abstract class AAddCostSummaryHandler extends ATransactionalAddCategoryHandler {
	
	@Override
	protected String getConceptName() {
		return "de.dlr.sc.virsat.model.extension.budget.cost";
	}
	
	@Override
	protected Command createAddCommand(EditingDomain editingDomain, EObject owner, Concept activeConcept) {
		return new CreateAddCostSummaryCommand().create(editingDomain, owner, activeConcept);
	}
}
