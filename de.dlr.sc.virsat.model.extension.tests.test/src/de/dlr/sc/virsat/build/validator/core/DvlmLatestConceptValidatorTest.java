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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * TestCase for latest concept validator
 * 
 * @author bell_er
 *
 */
public class DvlmLatestConceptValidatorTest extends AConceptProjectTestCase {
	private static final String CONCEPT_EXTENSION_POINT_ID = "de.dlr.sc.virsat.model.Concept";
	private static final String TEST_CONCEPT_ID = "de.dlr.sc.virsat.model.extension.tests";
	
	VirSatTransactionalEditingDomain domain;
	
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		UserRegistry.getInstance().setSuperUser(true);
		addEditingDomainAndRepository();
		activateCoreConcept();
		domain = editingDomain;
	}
	
	@Test
	public void testValidateLatestConcept() throws CoreException, IOException {
		DvlmLatestConceptValidator validator = new DvlmLatestConceptValidator();
		
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] concepts = registry.getConfigurationElementsFor(CONCEPT_EXTENSION_POINT_ID);
		
		ActiveConceptConfigurationElement acElement = ActiveConceptConfigurationElement.getPropperAddActiveConceptConfigurationElement(concepts, TEST_CONCEPT_ID);
		Command command = acElement.createAddActiveConceptCommand(domain, repository);
		domain.getCommandStack().execute(command);

		assertTrue("concept is up to date", validator.validate(repository));
		// we decrease the version of the concept
		for (Concept concept : repository.getActiveConcepts()) {
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					concept.setVersion("0.001");
				}
			});
			
		}

		assertFalse("concept is not up to date", validator.validate(repository));
	}
}
