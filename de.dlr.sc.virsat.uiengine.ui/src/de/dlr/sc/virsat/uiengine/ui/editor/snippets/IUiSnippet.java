/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * This is the interface that a UiSnippet for the Generic editor needs to implement
 * @author fisc_ph
 *
 */
public interface IUiSnippet extends ISelectionProvider {

	/**
	 * This method checks whether the snippet is applicable to edit the current model object.
	 * 
	 * @param model The input object of the current editor
	 * @return true if the snippet should be displayed and false otherwise
	 */
	boolean isActive(EObject model);
	
	/**
	 * This method is called to actually generate the SWT
	 * In the most cases it should call the super method!
	 * @param toolkit The toolkit that should be used for the generation
	 * @param editingDomain the editing domain of the current editor
	 * @param parentComposite the parent composite to which the SWT will be created. it is usually already a client area of a form section
	 * @param initModel the data object that should actually be edited by the given SWT
	 */
	void createSwt(FormToolkit toolkit, EditingDomain editingDomain, Composite parentComposite, EObject initModel);
	
	/**
	 * Method to set the databinding, the method gets called by the editor on regular basis as soon as the resource in the background
	 * is reloaded. the data binding needs to be removed from the stale objects and be redirected to the new ones
	 * @param dbCtx the new DataBinding Context
	 * @param editingDomain The editing domain of the editor
	 * @param model the model which should be used for the data binding
	 */
	void setDataBinding(DataBindingContext dbCtx, EditingDomain editingDomain, EObject model);

	/**
	 * This method is used to update the UI depending on their command states.
	 * It is called to allow the editor for updating the enabled state of actions
	 * @param state a boolean that may be used to override the enablements
	 */
	void updateState(boolean state);
	
	/**
	 * Method to save the expansion state of the Editor Snippets in a File 
	 * @param stateStorage The File in which the Info is stored - most likely the Resource of the EditorInput Object
	 */
	void saveExpansionState(IFile stateStorage);

	/**
	 * Method to restore the expansion state of the Editor Snippets in a File 
	 * @param stateStorage The File in which the Info is stored - most likely the Resource of the EditorInput Object
	 */
	void restoreExpansionState(IFile stateStorage);
	
	/**
	 * Method to dispose this snippet and associated resources
	 */
	void dispose();
	
	/**
	 * Use this method to set the WorkbenchSIte of the snippet
	 * This is needed to for registering the context menus correctly.
	 * @param site the IWorkbenchPartSite that can be obtained from the editor
	 * @return returns its own instance
	 */
	IUiSnippet setWorkbenchPartSite(IEditorSite site);
	
	/**
	 * method to check if the snippet shows markers 
	 * @return whether the snippet shows markers
	 */
	boolean hasMarkers();
}
