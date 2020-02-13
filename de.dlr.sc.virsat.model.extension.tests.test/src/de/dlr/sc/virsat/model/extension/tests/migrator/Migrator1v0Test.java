/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.migrator;

// *****************************************************************
// * Import Statements
// *****************************************************************


import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.tests.model.TestCrossLinkedParametersWithCalculation;
import de.dlr.sc.virsat.model.dvlm.DVLMFactory;

import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.util.EcoreUtil;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationDefinition;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedDefinitionInput;
import de.dlr.sc.virsat.model.dvlm.categories.Category;

// *****************************************************************
// * Class Declaration
// *****************************************************************

import org.junit.Test;

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * A pure test concept to check that everything is working as expected
 * 
 */
public class Migrator1v0Test extends AMigrator1v0Test {		
	
	@Test
	public void testMigrator1v0() {
		Migrator1v0 testMigrator1v0 = new Migrator1v0();
		
		Repository repository = DVLMFactory.eINSTANCE.createRepository();
		Concept conceptCurrent = ConceptsFactory.eINSTANCE.createConcept();
		Concept conceptNext = ConceptsFactory.eINSTANCE.createConcept();
		
		repository.getActiveConcepts().add(conceptCurrent);
		
		Concept conceptPrevious = EcoreUtil.copy(conceptCurrent);
		
		testMigrator1v0.migrate(conceptPrevious, conceptCurrent, conceptNext);
	}
	
	@Test
	public void testForProxiedReference() {
		
		// Load the category and check if the concept
		Category category = ActiveConceptHelper.getCategory(conceptMigrateFrom, TestCrossLinkedParametersWithCalculation.FULL_QUALIFIED_CATEGORY_NAME);
		EquationDefinition eq = category.getEquationDefinitions().get(0);
		ReferencedDefinitionInput rdi = (ReferencedDefinitionInput) eq.getExpression();

		IEquationDefinitionInput equationInput = rdi.getReference();
		
		assertTrue("The property referencing into the Maturity concept is still in proxy state", equationInput.eIsProxy());
	}
	
}
