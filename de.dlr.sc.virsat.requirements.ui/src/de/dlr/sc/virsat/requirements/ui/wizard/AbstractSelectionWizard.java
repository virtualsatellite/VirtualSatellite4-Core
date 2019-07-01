/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.ui.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public abstract class AbstractSelectionWizard extends Wizard implements INewWizard {

	protected ISelection requirementSelection;
	
	/**
	 * Initialize wizard
	 * @param workbench the workbench
	 * @param selection the current selection
	 */
	public abstract void init(IWorkbench workbench, IStructuredSelection selection);

	/**
	 * Method to perform finish of the wizard
	 * @return return if it worked
	 */
	public abstract boolean performFinish();
	
	/**
	 * Create a new export wizard
	 */
	public AbstractSelectionWizard() {
		super();
		setWindowTitle("Select Artifact");	
	}
	
	/**
	 * Set the selection for the new window
	 * @param selection to be set
	 */
	public void setSelection(ISelection selection) {
		this.requirementSelection = selection;
	}

	/**
	 * get the selection for the new window
	 * @return the selection
	 */
	public ISelection getSelection() {
		return requirementSelection;
	}
	
	/**
	 * @return the current project
	 */
	protected IProject getProject() {
		URI fileURI = getSpecHierarchy().eResource().getURI();
		Path path = new Path(fileURI.toPlatformString(false));			
		IResource iResource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);		
		IProject project = iResource.getProject();
		return project;
	}
	
	/**
	 * @return the current project
	 * @param traceElement the traceElement
	 */
	protected IProject getProject(EObject traceElement) {
		URI fileURI = traceElement.eResource().getURI();
		Path path = new Path(fileURI.toPlatformString(false));			
		IResource iResource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);		
		IProject project = iResource.getProject();
		return project;
	}
	
	/**
	 * @return the specHierarchy
	 */
	protected SpecHierarchy getSpecHierarchy() {
		IStructuredSelection selection = (IStructuredSelection) requirementSelection;		
		SpecHierarchy specHierarchy = (SpecHierarchy) selection.getFirstElement();
		return specHierarchy;	
	}

}
