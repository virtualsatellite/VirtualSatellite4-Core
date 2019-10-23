/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.core.infrastructure;

import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.core.Activator;
import de.dlr.sc.virsat.model.extension.core.model.GenericCategory;

/**
 * @author fran_tb
 *
 */
public class ConceptLanguageImplicitSuperTypeHandler {
	
	private static final String CONCEPT_XMI_PATH = "/concept/concept.xmi";
	
	/**
	 * Update a concept so that all categories extend the generic category
	 * @param concept the concept to be edited
	 * @return a copy of the concept with updated categories
	 */
	public Concept addImplicitSuperType(Concept concept) {
		Concept langaugeCoreConcept = loadConceptFromPlugin();
		
		//Check that the generic category does not get itself as super type
		if (concept.equals(langaugeCoreConcept)) {
			return concept;
		}
		
		Concept conceptWithImplicitSuperType = EcoreUtil.copy(concept);
		Category genericCategory = ActiveConceptHelper.
				getCategory(langaugeCoreConcept, GenericCategory.FULL_QUALIFIED_CATEGORY_NAME);
		for (Category category : concept.getCategories()) {
			if (category.getExtendsCategory() == null) {
				category.setExtendsCategory(genericCategory);
			}
		}
		
		return conceptWithImplicitSuperType;
	}
	
	/**
	 * Load the language core concept model from this plugin
	 * @return the language core concept
	 */
	public Concept loadConceptFromPlugin() {
		return ActiveConceptConfigurationElement.loadConceptFromPlugin(Activator.getPluginId() + CONCEPT_XMI_PATH);
	}

}
