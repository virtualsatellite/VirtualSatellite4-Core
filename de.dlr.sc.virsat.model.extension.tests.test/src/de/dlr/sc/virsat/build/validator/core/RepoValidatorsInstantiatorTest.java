/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.validator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.validator.IRepositoryValidator;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;
import de.dlr.sc.virsat.model.dvlm.validator.RepoValidatorsInstantiator;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.validator.TestsValidator;

/**
 * Tests for RepoValidatorsInstantiator
 */
public class RepoValidatorsInstantiatorTest extends ATestConceptTestCase {
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addResourceSetAndRepository();
		loadConceptAndInstallToRepository(CONCEPT_ID_CORE);
	}

	@Test
	public void testOnlyCoreValidators() {
		RepoValidatorsInstantiator validatorsInstantiator = new RepoValidatorsInstantiator(repository);
		List<IStructuralElementInstanceValidator> seiValidators = validatorsInstantiator.getSeiValidators();
		List<IRepositoryValidator> repoValidators = validatorsInstantiator.getRepoValidators();

		assertFalse("There are core SEI validators", seiValidators.isEmpty());
		assertTrue("Concept-specific validator not included",
				seiValidators.stream().noneMatch(v -> v instanceof TestsValidator));
		assertTrue("There is a core repo validator",
				repoValidators.stream().anyMatch(v -> v instanceof DvlmLatestConceptValidator));
	}

	@Test
	public void testConceptSpecificValidator() {
		loadTestConcept();

		RepoValidatorsInstantiator validatorsInstantiator = new RepoValidatorsInstantiator(repository);
		List<IStructuralElementInstanceValidator> seiValidators = validatorsInstantiator.getSeiValidators();

		assertTrue("Concept-specific validator included",
				seiValidators.stream().anyMatch(v -> v instanceof TestsValidator));
		assertEquals("There are two concept-specific validator", 2,
				seiValidators.stream().filter(
						v -> v.getClass().getName().startsWith(RepoValidatorsInstantiator.CONCEPT_BUNDLE_PREFIX))
						.count());
	}

	@Test
	public void testSuppressedValidator() {
		repository.getSuppressedValidators().add(DvlmLatestConceptValidator.class.getName());

		RepoValidatorsInstantiator validatorsInstantiator = new RepoValidatorsInstantiator(repository);
		List<IRepositoryValidator> repoValidators = validatorsInstantiator.getRepoValidators();

		assertTrue("Suppressed validator missing",
				repoValidators.stream().noneMatch(v -> v instanceof DvlmLatestConceptValidator));
	}
}
