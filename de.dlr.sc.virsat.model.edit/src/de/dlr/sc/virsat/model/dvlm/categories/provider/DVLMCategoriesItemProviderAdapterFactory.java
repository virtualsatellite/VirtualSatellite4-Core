/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;

/**
 * An extension to the categories item provider adapter factory 
 * now using the DVLMCategoryAssignmentItemProvider as Adapter
 * @author lobe_el
 *
 */
public class DVLMCategoriesItemProviderAdapterFactory extends CategoriesItemProviderAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {

	/**
	 * Constructor for the Factory
	 */
	public DVLMCategoriesItemProviderAdapterFactory() {
		super();
	}

	@Override
	public Adapter createCategoryAssignmentAdapter() {
		if (categoryAssignmentItemProvider == null) {
			categoryAssignmentItemProvider = new DVLMCategoryAssignmentItemProvider(this);
		}

		return categoryAssignmentItemProvider;
	}
	
	@Override
	public Adapter createCategoryAdapter() {
		if (categoryItemProvider == null) {
			categoryItemProvider = new DVLMCategoryItemProvider(this);
		}
		return categoryItemProvider;
	}
}
