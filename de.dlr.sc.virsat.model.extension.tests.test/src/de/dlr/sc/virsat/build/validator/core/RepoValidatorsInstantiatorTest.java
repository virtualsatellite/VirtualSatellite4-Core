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
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.build.validator.RepoValidatorsInstantiator;
import de.dlr.sc.virsat.build.validator.external.IRepositoryValidator;
import de.dlr.sc.virsat.build.validator.external.IStructuralElementInstanceValidator;
import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.extension.tests.validator.StructuralElementInstanceValidator;

/**
 * Tests for RepoValidatorsInstantiator
 */
public class RepoValidatorsInstantiatorTest extends AConceptProjectTestCase {
	private static final String CONCEPT_EXTENSION_POINT_ID = "de.dlr.sc.virsat.model.Concept";
	private static final String TEST_CONCEPT_ID = "de.dlr.sc.virsat.model.extension.tests";

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		activateCoreConcept();
	}

	@Test
	public void testOnlyCoreValidators() {
		RepoValidatorsInstantiator validatorsInstantiator = new RepoValidatorsInstantiator(repository);
		List<IStructuralElementInstanceValidator> seiValidators = validatorsInstantiator.getSeiValidators();
		List<IRepositoryValidator> repoValidators = validatorsInstantiator.getRepoValidators();

		assertTrue("There are core SEI validators", !seiValidators.isEmpty());
		assertTrue("Concept-specific validator not included",
				seiValidators.stream().noneMatch(v -> v instanceof StructuralElementInstanceValidator));
		assertTrue("There is a core repo validator",
				repoValidators.stream().anyMatch(v -> v instanceof DvlmLatestConceptValidator));
	}

	@Test
	public void testConceptSpecificValidator() {
		addTestConceptToRepository();

		RepoValidatorsInstantiator validatorsInstantiator = new RepoValidatorsInstantiator(repository);
		List<IStructuralElementInstanceValidator> seiValidators = validatorsInstantiator.getSeiValidators();

		assertTrue("Concept-specific validator included",
				seiValidators.stream().anyMatch(v -> v instanceof StructuralElementInstanceValidator));
		assertEquals("There is only one concept-specific validator", 1,
				seiValidators.stream().filter(
						v -> v.getClass().getName().startsWith(RepoValidatorsInstantiator.CONCEPT_BUNDLE_PREFIX))
						.count());
	}

	@Test
	public void testSuppressedValidator() {
		Command addSuppressedValidatorCommand = AddCommand.create(editingDomain, repository,
				DVLMPackage.eINSTANCE.getRepository_SuppressedValidators(), DvlmLatestConceptValidator.class.getName());
		editingDomain.getCommandStack().execute(addSuppressedValidatorCommand);

		RepoValidatorsInstantiator validatorsInstantiator = new RepoValidatorsInstantiator(repository);
		List<IRepositoryValidator> repoValidators = validatorsInstantiator.getRepoValidators();

		assertTrue("Suppressed validator missing",
				repoValidators.stream().noneMatch(v -> v instanceof DvlmLatestConceptValidator));
	}

	/**
	 * Adds test concept to active concepts of this test project
	 */
	private void addTestConceptToRepository() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] concepts = registry.getConfigurationElementsFor(CONCEPT_EXTENSION_POINT_ID);

		ActiveConceptConfigurationElement acElement = ActiveConceptConfigurationElement
				.getPropperAddActiveConceptConfigurationElement(concepts, TEST_CONCEPT_ID);
		Command command = acElement.createAddActiveConceptCommand(editingDomain, repository);
		editingDomain.getCommandStack().execute(command);
	}
}
