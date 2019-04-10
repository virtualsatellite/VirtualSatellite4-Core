/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.contentProvider;

import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.transaction.RunnableWithResult;

import de.dlr.sc.virsat.project.editingDomain.util.VirSatTransactionalEditingDomainHelper;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatObjectFolderContentProvider;
import de.dlr.sc.virsat.model.extension.visualisation.ui.handler.CreateDeltaModelFolderHandler;

/**
 * This class helps to display all delta models right under the project
 * @author fisc_ph
 *
 */
public class VisualizationDeltaModelFolderContentProvider extends VirSatObjectFolderContentProvider {

	@Override
	public Object[] getChildren(Object element) {		
		if (element instanceof VirSatProjectResource) {
			return VirSatTransactionalEditingDomainHelper.tryRunExclusive(element, new RunnableWithResult.Impl<Object[]>() {
				public void run() {
					VirSatProjectResource vsProject = (VirSatProjectResource) element;
					IFolder scriptFolder = vsProject.getWrappedProject().getFolder(CreateDeltaModelFolderHandler.VIS_DELTA_MODEL_FOLDER);
					if (scriptFolder.exists()) {
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
