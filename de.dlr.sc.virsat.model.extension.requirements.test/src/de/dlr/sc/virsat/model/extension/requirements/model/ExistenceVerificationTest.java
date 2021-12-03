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
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.Activator;

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
public class ExistenceVerificationTest extends AConceptProjectTestCase {

	private Concept concept;
	
	@Override
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		activateCoreConcept();
		concept = executeAsCommand(() -> loadConceptAndInstallToRepository(Activator.getPluginId()));
	}
	
	@Test
	public void testRunCustomVerification() {
		ExistenceVerification eV = new ExistenceVerification(concept);
		eV.setStatusPartlyCompliant();
		Requirement requirement = new Requirement(concept);
		
		editingDomain.getCommandStack().execute(eV.runCustomVerification(editingDomain, requirement, null));
		
		assertEquals("No trace give, status should be open", IVerification.STATUS_Open_NAME, eV.getStatus());
	}
	
	@Test
	public void testRunCustomVerificationTraced() {
		ExistenceVerification eV = new ExistenceVerification(concept);
		eV.setStatusOpen();
		Requirement requirement = new Requirement(concept);
		requirement.getTrace().getTarget().add(eV);
		
		editingDomain.getCommandStack().execute(eV.runCustomVerification(editingDomain, requirement, null));
		
		assertEquals("Added trace, status should be partly compliant now", IVerification.STATUS_PartialCompliant_NAME, eV.getStatus());
	}
	
	@Test
	public void testStatusSetter() {
		ExistenceVerification eV = new ExistenceVerification(concept);
		
		eV.setStatusOpen();
		assertEquals(IVerification.STATUS_Open_NAME, eV.getStatus());
		
		eV.setStatusCompliant();
		assertEquals(IVerification.STATUS_FullyCompliant_NAME, eV.getStatus());
		
		eV.setStatusNonCompliant();
		assertEquals(IVerification.STATUS_NonCompliant_NAME, eV.getStatus());
		
		eV.setStatusPartlyCompliant();
		assertEquals(IVerification.STATUS_PartialCompliant_NAME, eV.getStatus());
		
		editingDomain.getCommandStack().execute(eV.setStatusOpen(editingDomain));
		assertEquals(IVerification.STATUS_Open_NAME, eV.getStatus());
		
		editingDomain.getCommandStack().execute(eV.setStatusCompliant(editingDomain));
		assertEquals(IVerification.STATUS_FullyCompliant_NAME, eV.getStatus());
		
		editingDomain.getCommandStack().execute(eV.setStatusNonCompliant(editingDomain));
		assertEquals(IVerification.STATUS_NonCompliant_NAME, eV.getStatus());
		
		editingDomain.getCommandStack().execute(eV.setStatusPartlyCompliant(editingDomain));
		assertEquals(IVerification.STATUS_PartialCompliant_NAME, eV.getStatus());
	}

}
