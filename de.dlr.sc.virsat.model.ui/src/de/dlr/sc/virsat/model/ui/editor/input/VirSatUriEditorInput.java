/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ui.editor.input;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.model.ui.Activator;

/**
 * Specific EditorInput for Virtual Satellite. Fixes issues with display of the edited component name.
 * @author fisc_ph
 *
 */
public class VirSatUriEditorInput extends URIEditorInput {

	// This is basically a pre-definition of the Editor ID that we will use later on in
	// Virtual Satellite for the Custom Editor. We need this id for example in the handlers 
	// for creating Structural Elements or CAs, so the editors can be opened on creation if set
	// in the user preferences.
	public static final String EDITOR_ID = "de.dlr.sc.virsat.model.DVLM.Structural.presentation.CustomEditorID";

	private static final String NAME_SHORT_NAME = "sName";
	private String sName;
	
	/**
	 * Constructor for Editor Input
	 * @param uri URI of object to be opened
	 * @param iInstance the Instance object to be opened
	 */
	public VirSatUriEditorInput(URI uri, IInstance iInstance) {
		super(uri, iInstance.getFullQualifiedInstanceName());
		if (iInstance instanceof IName) {
			sName = ((IName) iInstance).getName();
		}
	}

	/**
	 * Editor input in case the iInstance cannot be determined
	 * @param uri the uri of the object to be opened
	 */
	public VirSatUriEditorInput(URI uri) {
		super(uri);
	}

	/**
	 * Constructor with memento to correctly restore the editor input
	 * @param memento the memento in which the editor input was previously stored
	 */
	public VirSatUriEditorInput(IMemento memento) {
		super(memento);
	}
	
	@Override
	public void saveState(IMemento memento) {
		super.saveState(memento);
		memento.putString(NAME_SHORT_NAME, sName);
	}
	
	@Override
	public String getToolTipText() {
		return super.getName();
	}

	@Override
	public String getName() {
		if (sName != null) {
			return sName + " -> " + super.getName();
		}
		return super.getName();
	}
		
	@Override
	protected void loadState(IMemento memento) {
		super.loadState(memento);
		sName = memento.getString(NAME_SHORT_NAME);
	}
	
	@Override
	protected String getBundleSymbolicName() {
		return Activator.getDefault().getBundle().getSymbolicName();
	}
	
	/**
	 * Opens another editor on the given type instance. This call tries to open the VirSat Customized Editor 
	 * @param object for which to try to open a Virtual Satellite Editor.
	 */
	public static void openDrillDownEditor(Object object) {
		if (object instanceof EObject) {
			openDrillDownEditor((EObject) object);
		}
	}
	
	/**
	 * Opens another editor on the given type instance. The object to be opened needs to be of type IINstance.
	 * @param eObject the typeInstance for which to open an editor
	 */
	public static void openDrillDownEditor(EObject eObject) {
		Resource resource = eObject.eResource();
		
		if (resource == null) {
			// If the resource is null, then we don't allow for opening the editor
			return;
		}
		
		URI resourceUri = resource.getURI();
		if (!VirSatEcoreUtil.isRootComponentofResource(eObject)) {
			resourceUri = resourceUri.appendFragment(resource.getURIFragment(eObject));
		}
		
		try {
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
			if (eObject instanceof IInstance) {
				IInstance iInstance = (IInstance) eObject;
				URIEditorInput editorInput = new VirSatUriEditorInput(resourceUri, iInstance);
				workbenchPage.openEditor(editorInput, EDITOR_ID);
			} else {
				workbenchPage.openEditor(new VirSatUriEditorInput(resourceUri), EDITOR_ID);
			}
		} catch (PartInitException ex) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Could not open Editor for: " + resourceUri, ex));
		}
	}
}
