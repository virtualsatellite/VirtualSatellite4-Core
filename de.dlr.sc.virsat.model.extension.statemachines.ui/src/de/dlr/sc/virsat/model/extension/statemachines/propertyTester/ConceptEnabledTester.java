/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.propertyTester;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.statemachines.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;



// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * Concept for modelling State Diagrams
 * 
 */
public class ConceptEnabledTester extends AConceptEnabledTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		VirSatTransactionalEditingDomain ed = getEditingDomainFromReceiver(receiver);
		
		// In case there is no editing domain than we cannot provide undo and redo
		if (ed == null) {
			return false;
		}
		
		Repository currentRepository = ed.getResourceSet().getRepository();
		ActiveConceptHelper acHelper = new ActiveConceptHelper(currentRepository);
		Concept activeConcept = acHelper.getConcept(Activator.getPluginId());
		boolean hasActiveConcept =  activeConcept != null;
		return hasActiveConcept;
	}
}
