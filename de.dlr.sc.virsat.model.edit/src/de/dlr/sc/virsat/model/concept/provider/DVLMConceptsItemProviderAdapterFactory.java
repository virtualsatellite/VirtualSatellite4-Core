/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;

import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;

/**
 * An extension to the concept item provider adapter factory 
 * now using the DVLMCConceptAssignmentItemProvider as Adapter
 * @author muel_s8
 *
 */
public class DVLMConceptsItemProviderAdapterFactory extends ConceptsItemProviderAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {

	/**
	 * Constructor for the Factory
	 */
	public DVLMConceptsItemProviderAdapterFactory() {
		super();
	}

	@Override
	public Adapter createConceptAdapter() {
		if (conceptItemProvider == null) {
			conceptItemProvider = new DVLMConceptItemProvider(this);
		}

		return conceptItemProvider;
	}
}
