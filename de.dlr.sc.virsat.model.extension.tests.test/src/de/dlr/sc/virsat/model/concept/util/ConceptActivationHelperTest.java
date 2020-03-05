/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.extension.tests.model.TestCrossLinkedParametersWithCalculation;

/**
 * Test class for concept activation helper
 */
public class ConceptActivationHelperTest extends AConceptProjectTestCase {

	public static final String TEST_CONCEPT_NAME = "de.dlr.sc.virsat.model.extension.tests";
	public static final String MATURITY_CONCEPT_NAME = "de.dlr.sc.virsat.model.extension.maturity";

	@Before
	public void setup() {
		addEditingDomainAndRepository();
	}

	@Test
	public void testActivateConcepts() {
		Concept testConcept = loadConceptFromPlugin(TEST_CONCEPT_NAME);
		Concept maturityConcept = loadConceptFromPlugin(MATURITY_CONCEPT_NAME);
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add(maturityConcept);
		concepts.add(testConcept);

		ConceptActivationHelper activationHelper = new ConceptActivationHelper(repository);

		activationHelper.activateConcepts(concepts, editingDomain, new NullProgressMonitor());

		assertTrue("Concept has been added", repository.getActiveConcepts().size() == 2);
		assertEquals("Maturity concept added", repository.getActiveConcepts().get(0).getName(), MATURITY_CONCEPT_NAME);
		assertEquals("Test concept added", repository.getActiveConcepts().get(1).getName(), TEST_CONCEPT_NAME);
	}
	
	@Test
	public void testActivateConceptsAndMigrate() {
		ConceptActivationHelper activationHelper = new ConceptActivationHelper(repository);
		Concept maturityConcept = loadConceptFromPlugin(MATURITY_CONCEPT_NAME);
		Concept testConceptOld = ConceptXmiLoader.loadConceptFromPlugin(TEST_CONCEPT_NAME + "/concept/concept_v1_0.xmi");
		activationHelper.activateConcepts(Collections.singletonList(maturityConcept), editingDomain, new NullProgressMonitor());
		activationHelper.activateConcepts(Collections.singletonList(testConceptOld), editingDomain, new NullProgressMonitor());
		
		assertEquals("Test concept version is old", repository.getActiveConcepts().get(1).getVersion(), testConceptOld.getVersion());
		
		Concept testConcept = loadConceptFromPlugin(TEST_CONCEPT_NAME);
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add(maturityConcept);
		concepts.add(testConcept);

		

		activationHelper.activateConcepts(concepts, editingDomain, new NullProgressMonitor());

		assertEquals("Concept has been added", 2, repository.getActiveConcepts().size());
		assertEquals("Maturity concept added", repository.getActiveConcepts().get(0).getName(), MATURITY_CONCEPT_NAME);
		assertEquals("Test concept added", repository.getActiveConcepts().get(1).getName(), TEST_CONCEPT_NAME);
		assertEquals("Test concept has been updated", repository.getActiveConcepts().get(1).getVersion(), testConcept.getVersion());
	}

	@Test
	public void testGetActiveType() {

		ConceptActivationHelper activationHelper = new ConceptActivationHelper(repository);
		ActiveConceptHelper conceptHelper = new ActiveConceptHelper(repository);
		Concept activeMaturityConcept = executeAsCommand(
				() -> loadConceptAndInstallToRepository(MATURITY_CONCEPT_NAME));
		Concept activeTestConcept = executeAsCommand(() -> loadConceptAndInstallToRepository(TEST_CONCEPT_NAME));

		assertEquals("Active concept should be in repository", activeMaturityConcept.eResource(),
				repository.eResource());
		assertEquals("Active concept should be in repository", activeTestConcept.eResource(), repository.eResource());

		Concept testConcept = loadConceptFromPlugin(TEST_CONCEPT_NAME);
		EObject activeCategory = activationHelper.getActiveType(testConcept.getCategories().get(0));
		assertNotEquals("Non-activate concept should not be in reposiotry", testConcept.eResource(),
				repository.eResource());
		assertEquals("Element of active concept should be in repository", activeCategory.eResource(),
				repository.eResource());

		Category activatedCategroyWithCrossRef = conceptHelper.getCategory(TEST_CONCEPT_NAME,
				TestCrossLinkedParametersWithCalculation.FULL_QUALIFIED_CATEGORY_NAME);
		assertEquals("Element of active concept should be in repository", activatedCategroyWithCrossRef.eResource(),
				repository.eResource());

	}

}
