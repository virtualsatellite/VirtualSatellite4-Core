/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.migrator;

import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;

/**
 * Tests the methods of the class CreateMigrateConceptToLatestCommand
 * @author muel_s8
 *
 */

public class CreateMigrateConceptToLatestCommandTest {

	@Test
	public void testCreate() throws CoreException {
	
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		Command migrationCommand = CreateMigrateConceptToLatestCommand.create(concept, null, new NullProgressMonitor());
		
		assertTrue("Migration command can execute", migrationCommand.canExecute());
	}

}
