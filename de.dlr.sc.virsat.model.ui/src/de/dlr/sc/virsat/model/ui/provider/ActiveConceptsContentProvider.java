/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ui.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;

/**
 * A content provider to display all active concepts in a repository
 * @author fisc_ph
 *
 */
public class ActiveConceptsContentProvider extends ExtensionPointContentProvider {

	private Repository repository;

	/**
	 * Constructor to initialize the provider with the repository that contains the active concepts
	 */
	public ActiveConceptsContentProvider() {
		super(ActiveConceptConfigurationElement.EXTENSION_POINT_ID_CONCEPT);
	}
	
	/**
	 * Sets a repository for this content provider.
	 * All concepts enabled in the repository will be filtered out
	 * @param repository the repository
	 */
	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	private Map<IConfigurationElement, ActiveConceptConfigurationElement> activeConceptConfigurationElementMap = new HashMap<>();
	
	@Override
	public Object[] getElements(Object inputElement) {
		IConfigurationElement[] configurationElementsArray = (IConfigurationElement[]) super.getElements(inputElement);
		List<IConfigurationElement> configurationElements = (List<IConfigurationElement>) Arrays.asList(configurationElementsArray);
		List<ActiveConceptConfigurationElement> activeConceptConfigurationElements = new ArrayList<>();

		// Now start reducing the IConfigurationElements by the ones that are
		// actually present as active concepts
		List<Concept> activeConcepts = repository != null ? repository.getActiveConcepts() : null;
		for (IConfigurationElement configurationElement : configurationElements) {
			ActiveConceptConfigurationElement acce;
			if (activeConceptConfigurationElementMap.containsKey(configurationElement)) {
				acce = activeConceptConfigurationElementMap.get(configurationElement);
			} else {
				acce = new ActiveConceptConfigurationElement(configurationElement);
				activeConceptConfigurationElementMap.put(configurationElement, acce);
			}
			
			boolean conceptIsInRepository = false;
			
			if (repository != null) {
				// Now check if the activeConcept is represented by one of the given
				// configuration Elements
				for (Concept concept : activeConcepts) {
					conceptIsInRepository |= acce.registersConcept(concept);
				}
			}
			
			if (!conceptIsInRepository) {
				activeConceptConfigurationElements.add(acce);
			}
		}

		return activeConceptConfigurationElements.toArray();
	}
}
