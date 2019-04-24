/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.dialog.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;

import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;

/**
 * This class is intended to provide some better access to objects that can be referenced within the model
 * This content provider tries to display a ResourceSet in the known view of Repository, UnitManagement etc
 * @author fisc_ph
 *
 */
public class ReferenceSelectionFilteredTransactionalContentProvider extends VirSatFilteredWrappedTreeContentProvider {

	/**
	 * Standard Constructor that needs an AdapterFactory for COntentProviders
	 * @param adapterFactory EMF Compatible ContentProvider
	 */
	public ReferenceSelectionFilteredTransactionalContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		setCheckContainedForFilter(true);
	}

	@Override
	public Object[] getElements(Object rootObject) {
		if (rootObject instanceof VirSatResourceSet) {
			VirSatResourceSet virSatResSet = (VirSatResourceSet) rootObject;
			List<Object> objects = new ArrayList<>();
			
			objects.addAll(virSatResSet.getRepository().getRootEntities());
			Object[] filteredClasses = filterClasses(objects.toArray(), filterElementClasses);
			Object[] filteredInstances = filterIds(filteredClasses, filterElementCategoryIds, filterElementStructuralElementIds);
			
			return filteredInstances;
		} else {
			return super.getElements(rootObject);
		}
	}
}
