/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.contentProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class ResourceFilteredWrappedTreeContentProvider extends VirSatFilteredWrappedTreeContentProvider implements ITreeContentProvider {

	private Collection<String> fileEndings = new ArrayList<String>();
	
	public ResourceFilteredWrappedTreeContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public ResourceFilteredWrappedTreeContentProvider(ITreeContentProvider wrappedContentProvider) {
		super(wrappedContentProvider);
	}

	public void addSupportedFileEnding(String fileEnding) {
		fileEndings.add(fileEnding);
	}
	
	public void addSupportedFileEndings(Set<String> fileEndings) {
		this.fileEndings.addAll(fileEndings);
	}
	
	@Override
	public Object[] getElements(Object rootObject) {
		return filterResourceFiles(super.getElements(rootObject));
	}
	
	@Override
	public Object[] getChildren(Object parentObject) {
		return filterResourceFiles(super.getChildren(parentObject));
	}
	
	/**
	 * Filter tree elements according to their resource's file extension
	 * @param allObjects the list of objects to filter
	 * @return the filtered array
	 */
	protected Object[] filterResourceFiles(Object[] allObjects) {
		Set<Object> filteredList = new HashSet<Object>();
		
		for (Object object : Arrays.asList(allObjects)) {
			if (object instanceof Resource) {
				if (evaluateResource((Resource) object)) {
					filteredList.add(object);
				}
			} else if (object instanceof EObject) {
				if (evaluateResource(((EObject) object).eResource())) {
					filteredList.add(object);
				}
			} else {
				//Cannot evaluate resource
				filteredList.add(object);
			}
		}
		
		return filteredList.toArray();
	}
	
	/**
	 * Check if resource is in list of supported file extensions
	 * @param resource the resource
	 * @return if the resource is contained in allowed resources file endings
	 */
	protected boolean evaluateResource(Resource resource) {
		return fileEndings.contains((resource).getURI().fileExtension());
	}
	
}
