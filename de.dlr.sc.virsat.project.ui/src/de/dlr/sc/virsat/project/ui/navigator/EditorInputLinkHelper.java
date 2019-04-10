/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.ILinkHelper;

import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;


/**
 * Link helper that opens the navigator item by selecting the editor and vise versa
 * @author fisc_ph
 *
 */
public class EditorInputLinkHelper implements ILinkHelper {

	@Override
	public IStructuredSelection findSelection(IEditorInput anInput) {
		try {
			EObject selectionObject = getEObjectForFileEditorInput(anInput);
			if (selectionObject != null) {
				return new StructuredSelection(getEObjectForFileEditorInput(anInput));
			}
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "EditorInputLinkHelper: Could not create link from selected editor: " + e.getMessage()));
		}
		return null;
	}

	@Override
	public void activateEditor(IWorkbenchPage aPage, IStructuredSelection aSelection) {
	}

	/**
	 * Method to decide on the EObject that should be selected depending on the editor input
	 * @param editorInput the Editor Input on which to decide what to select
	 * @return the structural element that should be selected
	 */
	private EObject getEObjectForFileEditorInput(IEditorInput editorInput) {

		IFile dvlmFile;
		URI eObjectUri;
		EObject eObject;
		
		// First get the corresponding resource file in the workspace depending on the editor input
		if (editorInput instanceof URIEditorInput) {
			URIEditorInput uriEditorInput = (URIEditorInput) editorInput;
			URI uri = uriEditorInput.getURI();
			dvlmFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
			eObjectUri = uri;
		} else if (editorInput instanceof IFileEditorInput) {
			IFileEditorInput fileEditorInput = (IFileEditorInput) editorInput;
			dvlmFile = fileEditorInput.getFile();
			eObjectUri = URI.createPlatformResourceURI(dvlmFile.getFullPath().toString(), true);
		} else {
			return null;
		}
	
		if (!VirSatProjectCommons.isDvlmFile(dvlmFile)) {
			return null;
		}
		
		// Now try to identify the corresponding ResourceSet and Editing Domain
		IProject project = dvlmFile.getProject();
		VirSatResourceSet resourceSet = VirSatResourceSet.getResourceSet(project);
		VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(resourceSet);

		// and finally use the ResourcecSet to pick the correct EObject from the ResourcecSet
		URI resourceURI = EditUIUtil.getURI(editorInput, editingDomain.getResourceSet().getURIConverter());
		Resource resource = editingDomain.getResourceSet().getResource(resourceURI, false);
		String uriFragment = eObjectUri.fragment();
		if ((uriFragment != null) && (!uriFragment.isEmpty())) {
			eObject = resource.getEObject(uriFragment);
		} else {
			if (resource.getContents().size() == 1) {
				eObject = resource.getContents().get(0);
			} else {
				eObject = null;
			}
		}
		
		return eObject;	
	}
}