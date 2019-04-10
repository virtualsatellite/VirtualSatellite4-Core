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

import org.eclipse.emf.common.notify.AdapterFactory;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptItemProvider;

/**
 * This item provider makes sure to override the getText method 
 * to return it with the version number of the concept
 * @author muel_s8
 *
 */
public class DVLMConceptItemProvider extends ConceptItemProvider {

	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory 
	 */
	public DVLMConceptItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		String label = super.getText(object);
		Concept concept = (Concept) object;
		if (concept.getVersion() != null && !concept.getVersion().equals("")) {
			label += " [" + concept.getVersion()  + "]";
		}
		return label;  
	}
}
