/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;

/**
 * An extension to the structural item provider adapter factory 
 * now using the DVLMCategoryAssignmentItemProvider as Adapter
 * @author lobe_el
 *
 */
public class DVLMStructuralItemProviderAdapterFactory extends StructuralItemProviderAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {

	/**
	 * Constructor for the Factory
	 */
	public DVLMStructuralItemProviderAdapterFactory() {
		super();
	}

	@Override
	public Adapter createStructuralElementInstanceAdapter() {
		if (structuralElementInstanceItemProvider == null) {
			structuralElementInstanceItemProvider = new DVLMStructuralElementInstanceItemProvider(this);
		}
		return structuralElementInstanceItemProvider;
	}
	
	@Override
	public Adapter createStructuralElementAdapter() {
		if (structuralElementItemProvider == null) {
			structuralElementItemProvider = new DVLMStructuralElementItemProvider(this);
		}
		return structuralElementItemProvider;
	}
}
