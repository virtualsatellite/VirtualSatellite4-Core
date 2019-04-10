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

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Class provides functionality to compose several other provider into one common content provider
 * 
 * @author fisc_ph
 *
 */
public class VirSatComposedContentProvider implements ITreeContentProvider {

	private Set<ITreeContentProvider> subContentProvider = new HashSet<>();
	
	/**
	 * Use this method to attach a new SubContentProvider
	 * @param cp the content provider to be added to this composed content provider
	 */
	public void registerSubContentProvider(ITreeContentProvider cp) {
		subContentProvider.add(cp);
	}
	
	@Override
	public void dispose() {
		subContentProvider.forEach((cp) -> cp.dispose());
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		subContentProvider.forEach((cp) -> cp.inputChanged(viewer, oldInput, newInput));
	}

	@Override
	public Object[] getElements(Object inputElement) {
		List<Object> subContentProviderObjects = new LinkedList<>();

		for (ITreeContentProvider cp : subContentProvider) {
			subContentProviderObjects.addAll(Arrays.asList(cp.getElements(inputElement)));
		}
		
		return subContentProviderObjects.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Object> subContentProviderObjects = new LinkedList<>();

		for (ITreeContentProvider cp : subContentProvider) {
			subContentProviderObjects.addAll(Arrays.asList(cp.getChildren(parentElement)));
		}
		
		return subContentProviderObjects.toArray();
	}

	@Override
	public Object getParent(Object element) {
		for (ITreeContentProvider cp : subContentProvider) {
			Object parentObject = cp.getParent(element);
			if (parentObject != null) {
				return parentObject;
			}
		}

		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		for (ITreeContentProvider cp : subContentProvider) {
			boolean hasChildren = cp.hasChildren(element);
			if (hasChildren) {
				return true;
			}
		}

		return false;
	}
}
