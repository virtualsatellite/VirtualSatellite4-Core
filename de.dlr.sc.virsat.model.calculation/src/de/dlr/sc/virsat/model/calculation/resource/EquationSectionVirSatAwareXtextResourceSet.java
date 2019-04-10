/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.ResourceSetReferencingResourceSet;
import org.eclipse.xtext.resource.ResourceSetReferencingResourceSetImpl;

/**
 * This class does some magic. Please comment more.
 * 
 * @author fisc_ph
 *
 */
public class EquationSectionVirSatAwareXtextResourceSet extends ResourceSetReferencingResourceSetImpl implements ResourceSetReferencingResourceSet {

	/**
	 * Method to create an Equation Section Container Resource
	 * @param resUri Resource uri
	 * @param equationSectionContainerResourcce The Resource that originally holds the Reosurce to the Equation
	 * @param equationSectionProvider the provider for the associated equation section 
	 * @return A new resource build on the given URI that references the given resource
	 */
	public Resource createFromEquationSectionContainerResource(URI resUri, Resource equationSectionContainerResourcce, IEquationSectionProvider equationSectionProvider) {
		// First register the ResourceSet from the container of the EquationSection
		// For example the VirSatTransactionalResourceSet that contain the StructuralElementInstance
		ResourceSet equationSectionContainerResourcceSet = equationSectionContainerResourcce.getResourceSet();
		getReferencedResourceSets().add(equationSectionContainerResourcceSet);
		
		// Now use the URI to create a new EquationSectionResource This one is intended
		// to hold a copy of the equations. We may consider to put the copy of the equations
		// into this part of the code. Try to combine it in case of an EquationSsectionXtextResource
		Resource resource = createResource(resUri);
		if (resource instanceof EquationSectionXtextResource) {
			EquationSectionXtextResource equationSectionXtextResource = (EquationSectionXtextResource) resource;
			equationSectionXtextResource.setEquationSectionContainerResource(equationSectionContainerResourcce, equationSectionProvider);
		}
		
		return resource;
	}

	/**
	 * Call this method to add a ResourceSet to the ones which are referenced.
	 * the method checks if the ResourceSet is already referenced. In that case
	 * it wont be added a second time.
	 * @param resourceSet The ResourceSet that should be added to the referenced ones.
	 */
	public void addReferencedResourceSet(ResourceSet resourceSet) {
		// Get all resourceSets and check if the current one is already referenced
		// If it is not add it otherwise forget about it.
		if (!getReferencedResourceSets().contains(resourceSet)) {
			getReferencedResourceSets().add(resourceSet);
		}
	}
}
