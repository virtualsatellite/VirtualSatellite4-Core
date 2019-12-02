/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.viewers.ITreeContentProvider;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;

/**
 * this class extends the virsat filtered transcational adapter factory content provider for array instances
 * @author leps_je
 *
 */
public class ArrayInstanceFilteredTransactionalAdapterFactoryContentProvider extends VirSatFilteredWrappedTreeContentProvider {

	/**
	 * constructor for the class array instance filtered transcational adapter factory content provider instantiate the adapter factory
	 * @param adapterFactory the adapter factory
	 */
	public ArrayInstanceFilteredTransactionalAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	/**
	 * Constructor of the Content Provider without adapter factory
	 * @param wrappedContentProvider Composed Adapter factory to be used by the content provider
	*/
	public ArrayInstanceFilteredTransactionalAdapterFactoryContentProvider(ITreeContentProvider wrappedContentProvider) {
		super(wrappedContentProvider);
	}

	@Override
	public Object[] getElements(Object rootObject) {
		if (rootObject instanceof ArrayInstance) {
			Object[] allElements = super.getNonFilteredElements(rootObject);
			ArrayList<Object> returnElements = new ArrayList<>();
			for (Object element : allElements) {
				// In the case of a composed element we have to hand back the category which is actually composed
				// all other elements are handed back directly as they are for the moment
				if (element instanceof ComposedPropertyInstance) {
					ComposedPropertyInstance cpi = (ComposedPropertyInstance) element;
					List<Object> composedObjects = Arrays.asList(super.getElements(cpi));
					
					if (!composedObjects.isEmpty()) {
						returnElements.add(element);
					}
				} else {
					returnElements.add(element);
				}
			}
			return returnElements.toArray();
		}
		return super.getElements(rootObject);
	}
	
	@Override
	public Object[] getChildren(Object parentObject) {
		if (parentObject instanceof ComposedPropertyInstance) {
			ComposedPropertyInstance cpi = (ComposedPropertyInstance) parentObject;
			return super.getElements(cpi.getTypeInstance());
		}
		return super.getChildren(parentObject);
	}
}
