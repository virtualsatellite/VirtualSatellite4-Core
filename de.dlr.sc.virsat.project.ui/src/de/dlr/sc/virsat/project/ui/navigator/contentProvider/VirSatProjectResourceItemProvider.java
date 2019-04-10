/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.contentProvider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;

/**
 * This class acts as a non-EMF intermediary object representing a virsat project containing
 * a repository, unit management and role management.
 * @author muel_s8
 *
 */

public class VirSatProjectResourceItemProvider extends ItemProviderAdapter
	implements
		ITreeItemContentProvider {

	/**
	 * Standard constructor.
	 * @param adapterFactory the adapter factory
	 * @param repository the repository
	 */
	public VirSatProjectResourceItemProvider(AdapterFactory adapterFactory, Repository repository) {
		super(adapterFactory);
		repository.eAdapters().add(this);
	}
	
	@Override
	protected Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(DVLMPackage.Literals.REPOSITORY__UNIT_MANAGEMENT);
			childrenFeatures.add(DVLMPackage.Literals.REPOSITORY__ROLE_MANAGEMENT);
		}
		return childrenFeatures;
	}
	
	@Override
	public Object getParent(Object object) {
		return super.getParent(target);
	}
	
	@Override
	public Collection<?> getChildren(Object object) {
		Collection<Object> children = new ArrayList<Object>();
		children.addAll(super.getChildren(target));
		children.add(target);
		
		return children;
	}

}
