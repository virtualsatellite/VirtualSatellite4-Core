/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.editingDomain.propertyTester;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;

/**
 * Abstract PropertyTester for implementing property testers that need to get EditingDomain
 */
public abstract class EditingDomainPropertyTester extends PropertyTester {

	/**
	 * Gets VirSatTransactionalEditingDomain for the given receiver
	 * @param receiver object that is being property tested (usually current selection)
	 * @return editing domain
	 */
	protected VirSatTransactionalEditingDomain getEditingDomainFromReceiver(Object receiver) {
		VirSatTransactionalEditingDomain ed = null;
		if (receiver instanceof EObject) {
			EObject eObject = (EObject) receiver;
			ed = VirSatEditingDomainRegistry.INSTANCE.getEd(eObject);
		} else if (receiver instanceof VirSatProjectResource) {
			VirSatProjectResource resource = (VirSatProjectResource) receiver;
			IProject project = resource.getWrappedProject();
			ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
		}
		
		return ed;
	}
}
