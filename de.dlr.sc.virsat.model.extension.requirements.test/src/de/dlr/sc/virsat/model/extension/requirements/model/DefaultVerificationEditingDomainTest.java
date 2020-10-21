/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.model;

import static org.junit.Assert.assertEquals;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

// *****************************************************************
// * Import Statements
// *****************************************************************



// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class DefaultVerificationEditingDomainTest extends AConceptProjectTestCase {
	
	public static final String TYPE_NAME = "DType";
	private Concept concept = null;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		super.addEditingDomainAndRepository();
		String conceptXmiPluginPath = "de.dlr.sc.virsat.model.extension.requirements/concept/concept.xmi";
		concept = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
	}

	@Test
	public void testSetVerificationTypeWithEditingDomain() {
		VerificationType type = new VerificationType(concept);
		type.setName(TYPE_NAME);
		DefaultVerification verification = new DefaultVerification(concept);
		Command resultCommand = verification.setVerificationType(editingDomain, type);
		editingDomain.getCommandStack().execute(resultCommand);
		
		assertEquals("Type name should be added to verification isntance", TYPE_NAME, verification.getName());
	}
}
