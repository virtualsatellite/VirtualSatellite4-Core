/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.calculation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;

/**
 * This class tests the QualifiedEquationObjectHelper
 * @author muel_s8
 *
 */

public class QualifiedEquationObjectHelperTest {

	@Test
	public void testGetFullQualifiedName() {
		
		final String CONCEPT_NAME = "Concept";
		final String CATEGORY_NAME = "Category";
		
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.setName(CONCEPT_NAME);
		
		Category category = CategoriesFactory.eINSTANCE.createCategory();
		category.setName(CATEGORY_NAME);
		
		concept.getCategories().add(category);
		
		EquationDefinition eqDefinition = CalculationFactory.eINSTANCE.createEquationDefinition();
		category.getEquationDefinitions().add(eqDefinition);
		
		String fqn = QualifiedEquationObjectHelper.getFullQualifiedName(eqDefinition);
		final String EXCTECTED_FQN = "Concept.Category.equationDefinitions[0].EquationDefinition";
		assertEquals("Full Qualified Name is correct", EXCTECTED_FQN, fqn);
	}

}
