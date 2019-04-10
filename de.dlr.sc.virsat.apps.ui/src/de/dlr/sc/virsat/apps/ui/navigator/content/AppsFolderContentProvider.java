/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.ui.navigator.content;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.transaction.RunnableWithResult;

import de.dlr.sc.virsat.apps.initializer.VirsatAppsInitializer;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.util.VirSatTransactionalEditingDomainHelper;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatObjectFolderContentProvider;

/**
 * This class helps to display all scripts under the repository entry in the navigator
 * @author fisc_ph
 *
 */
public class AppsFolderContentProvider extends VirSatObjectFolderContentProvider {

	@Override
	public Object[] getChildren(Object element) {		
		if (element instanceof Repository) {
			return VirSatTransactionalEditingDomainHelper.tryRunExclusive(element, new RunnableWithResult.Impl<Object[]>() {
				public void run() {
					Repository repo = (Repository) element;
					VirSatTransactionalEditingDomain virSatEd = VirSatEditingDomainRegistry.INSTANCE.getEd(repo);
					if (virSatEd != null) {
						IProject currentProject = virSatEd.getResourceSet().getProject();
						IFolder scriptFolder = currentProject.getFolder(VirsatAppsInitializer.FOLDER_NAME_APPS);
						IFolder [] documentFolders = new IFolder[1];
						documentFolders[0] = scriptFolder;
						setResult(documentFolders);
					} else {
						setResult(new IFolder[0]);
					}
				}
			});
		}
		return super.getChildren(element);
	}
}
