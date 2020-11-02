/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.migrator;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;

import de.dlr.sc.virsat.model.concept.migrator.IMigrator;
import de.dlr.sc.virsat.model.extension.requirements.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.structure.nature.VirSatProjectNature;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * Extension for Requirement Specification
 * 
 */
public class Migrator0v4 extends AMigrator0v4 implements IMigrator {
	
	@Override
	public void dataModelMigration(List<Diff> differences) {
		super.dataModelMigration(differences);
		Diff diff = differences.iterator().next();
		
		if (diff != null) {
			Match match = diff.getMatch();
			EObject affectedEObject = match.getRight();
			IProject containingProject = VirSatEditingDomainRegistry.INSTANCE.getEd(affectedEObject).getResourceSet().getProject();
			try {
				IProjectNature nature = containingProject.getNature(VirSatProjectNature.NATURE_ID);
				
				// Make sure all necessary builders, such as the new verification builder, are active
				if (nature != null) {
					nature.configure();
				}
				
			} catch (CoreException e) {
				Status status = new Status(Status.ERROR, Activator.getPluginId(),
						"Requirements Migrator v0.4: Failed to perform data model migration (Builder configuration) !", e);
				StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
			}
		}
	}

}
