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

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.project.ui.transactional.handler.ATransactionalAddCategoryHandler;
import de.dlr.sc.virsat.model.extension.thermal.ui.command.CreateAddSingleFaceRadiationListCommand;

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Modeling of separate emissivities for different faces
 * 
 */	
public abstract class AAddSingleFaceRadiationListHandler extends ATransactionalAddCategoryHandler {
	
	@Override
	protected String getConceptName() {
		return "de.dlr.sc.virsat.model.extension.thermal";
	}
	
	@Override
	protected Command createAddCommand(EditingDomain editingDomain, EObject owner, Concept activeConcept) {
		return new CreateAddSingleFaceRadiationListCommand().create(editingDomain, owner, activeConcept);
	}
}
