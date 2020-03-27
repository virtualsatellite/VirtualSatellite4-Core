/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;

/**
 * This class gives some help to extract the correct selections in a command handler
 * @author fisc_ph
 *
 */
public class VirSatSelectionHelper {

	private IStructuredSelection structuredSelection;
	
	/**
	 * Constructor to wrap the selection of the platform or the command handler
	 * @param selection the current selection
	 */
	public VirSatSelectionHelper(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			structuredSelection = (IStructuredSelection) selection;
		}
	}
	
	/**
	 * Gets the first eObject in the selection
	 * @return null in case the first selection is not eObject
	 */
	public EObject getFirstEObject() {
		if (structuredSelection != null) {
			Object firstObject = structuredSelection.getFirstElement();
			if (firstObject instanceof EObject) {
				return (EObject) firstObject;
			}
		}
		return null;
	}
	

	/**
	 * Gets the number of selected items
	 * @return number of selected items
	 */
	public int getSelectionSize() {
		return structuredSelection.size();
	}

	/**
	 * Gets all selected EObjects
	 * @return ArrayList with all selected EObjects
	 */
	public ArrayList<EObject> getAllSelectedEObjects() {
		Object[] selectedItems = structuredSelection.toArray();
		ArrayList<EObject> selectedEObjects = new ArrayList<>();
		for (Object selectedItem : selectedItems) {
			if (selectedItem instanceof EObject) {
				selectedEObjects.add((EObject) selectedItem);
			}
		}
		return selectedEObjects;
	}
	
	/**
	 * Gets all selected EObjects of a given Type
	 * @param clazz The Type to filter for
	 * @param <TYPE> generic Type extending EObject
	 * @return List with all selected EObjects of the given Type
	 */
	@SuppressWarnings("unchecked")
	public <TYPE extends EObject> List<TYPE> getAllSelectedEObjectsOfType(Class<TYPE> clazz) {
		List<EObject> allEObjects = getAllSelectedEObjects();
		List<TYPE> listOfTypeEObjects = new ArrayList<>();
		
		allEObjects.forEach(eObject -> {
			if (clazz.isAssignableFrom(eObject.getClass())) {
				listOfTypeEObjects.add((TYPE) eObject);
			}
		});
		
		return listOfTypeEObjects;
	}
	
	/**
	 * Returns the project resource from the selection
	 * @return The selected project
	 */
	public IProject getProjectResource() {
		if (structuredSelection == null) {
			return null;
		}
		
		Object firstObject = structuredSelection.getFirstElement();
		if (firstObject instanceof VirSatProjectResource) {
			VirSatProjectResource virsatProjectResource = (VirSatProjectResource) firstObject;
			return virsatProjectResource.getWrappedProject();
		} else if (firstObject instanceof EObject) {
			IFile fileResource = getFileResource();
			if (fileResource != null) {
				IProject fileProjectResource = fileResource.getProject();
				return fileProjectResource;
			}
		}  
		
		return null;
	}
	
	/**
	 * Returns all projects of selection
	 * @return selected projects
	 */
	public Set<IProject> getAllProjectResouces() {
		Set<IProject> selectedProjects = new HashSet<>();
		
		for (Object object : structuredSelection.toList()) {
			if (object instanceof EObject) {
				VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd((EObject) object);
				IProject project = ed.getResourceSet().getProject();
				selectedProjects.add(project);
			} else if (object instanceof VirSatProjectResource) {
				// Project root object
				IProject project = ((VirSatProjectResource) object).getWrappedProject();
				selectedProjects.add(project);
			}
		}
		
		return selectedProjects;
	}

	/**
	 * Returns the selected file resource
	 * @return the selected file resource
	 */
	public IFile getFileResource() {
		if (structuredSelection == null) {
			return null;
		}

		Object firstObject = structuredSelection.getFirstElement();
		if (firstObject instanceof EObject) {
			EObject eObject = (EObject) firstObject;
			IFile fileResource = getFileForEObject(eObject);
			return fileResource;
		}
		
		return null;
	}
	
	/**
	 * This method hands back the file on the file system which is associated with the selected eObject
	 * @param eObject The eObject of which to get the file on the file system for
	 * @return the file in which this eObject is stored in.
	 */
	private IFile getFileForEObject(EObject eObject) {
		if ((eObject != null) && (eObject.eResource() != null) && (!eObject.eIsProxy())) {
			Resource resource = eObject.eResource();
			URI resourceUri = resource.getURI();
			String platformPath = resourceUri.toPlatformString(true);
			IFile fileResource = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformPath));
			return fileResource;
		} 
		return null;
	}
	
	/**
	 * This method hands back the Editing Domain for the current Selection.
	 * The method tries to identify the ED either via a selected project or
	 * by checking for a selected EObjectc.
	 * @return the VirSatEditingDomain, or null in case it could not be found
	 */
	public VirSatTransactionalEditingDomain getEditingDomain() {
		IProject project = getProjectResource();
		EObject eObject = getFirstEObject();
		if (project != null) {
			return VirSatEditingDomainRegistry.INSTANCE.getEd(project);
		} else if ((eObject != null) && (eObject.eResource() != null) && (!eObject.eIsProxy())) {
			return VirSatEditingDomainRegistry.INSTANCE.getEd(eObject);
		} else {
			return null;
		}
	}
	
	/**
	 * This method checks for the current selection and hands back the
	 * corresponding Editing Domain if it exists.
	 * @param selectionService The current selection service to be used to determine the current selection
	 * @return The Editing Domain in case it exists.
	 */
	public static VirSatTransactionalEditingDomain getEditingDomainViaSlectionService(ISelectionService selectionService) {
		ISelection selection = selectionService.getSelection();
	
		if (selection != null && !selection.isEmpty()) {
			return new VirSatSelectionHelper(selection).getEditingDomain();
		}
		return null;
	}
}
