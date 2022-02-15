/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.thermal.propertyTester;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.core.resources.IProject;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import org.eclipse.emf.ecore.EObject;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.model.dvlm.Repository;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * Concept for thermalents, Phd, Masters, Bachelors and research projects
 * 
 */
public class ConceptEnabledTester extends AConceptEnabledTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		
		// First detect the editing domain
		VirSatTransactionalEditingDomain ed = null;
		if (receiver instanceof EObject) {
			EObject eObject = (EObject) receiver;
			ed = VirSatEditingDomainRegistry.INSTANCE.getEd(eObject);
		} else if (receiver instanceof VirSatProjectResource) {
			VirSatProjectResource resource = (VirSatProjectResource) receiver;
			IProject project = resource.getWrappedProject();
			ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
		}
		
		// In case there is no editing domain than we cannot provide undo and redo
		if (ed == null) {
			return false;
		}
		
		Repository currentRepository = ed.getResourceSet().getRepository();
		ActiveConceptHelper acHelper = new ActiveConceptHelper(currentRepository);
		Concept activeConcept = acHelper.getConcept("de.dlr.sc.virsat.model.extension.thermal");
		boolean hasActiveConcept =  activeConcept != null;
		return hasActiveConcept;
	}
}
