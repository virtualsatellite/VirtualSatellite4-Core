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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
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
public class BoundedValueVerificationTest extends AConceptProjectTestCase {
	
	private static final String REQ_CONCEPT_ID = "de.dlr.sc.virsat.model.extension.requirements";
	private Concept conceptRequirements;
	
	private static final double UPPER_LIMIT = 100;
	private static final double LOWER_LIMIT = 10;
	private static final double VALUE_COMPLIANT = 50;
	private static final double VALUE_TOO_HIGH = 150;
	private static final double VALUE_TOO_LOW = 5;
	
	@Before
	public void setUp() throws CoreException  {
		super.setUp();
		addResourceSetAndRepository();
		loadConceptAndInstallToRepository(CONCEPT_ID_CORE, repository);
		conceptRequirements = loadConceptAndInstallToRepository(REQ_CONCEPT_ID, repository);
	}
	
	public class TestBoundedValueRequirementsVerification extends BoundedValueVerification {
		boolean statusCompliant = false;
		boolean statusOpen = false;
		boolean statusNonCompliant = false;
		boolean statusPartlyCompliant = false;
		
		public TestBoundedValueRequirementsVerification() {
			super(conceptRequirements);
		}
		
		@Override
		protected Command setStatusCompliant(EditingDomain editingDomain) {
			statusCompliant = true;
			return null;
		}
		
		@Override
		protected Command setStatusOpen(EditingDomain editingDomain) {
			statusOpen = true;
			return null;
		}
		
		@Override
		protected Command setStatusNonCompliant(EditingDomain editingDomain) {
			statusNonCompliant = true;
			return null;
		}
		
		@Override
		protected Command setStatusPartlyCompliant(EditingDomain editingDomain) {
			statusPartlyCompliant = true;
			return null;
		}
	}
	
	@Test
	public void testRunCustomVerificationNoTrace() {
		TestBoundedValueRequirementsVerification testObject = new TestBoundedValueRequirementsVerification();
		
		Requirement requirementObject = new Requirement(conceptRequirements);
		
		assertFalse("No status should be set", testObject.statusCompliant);
		assertFalse("No status should be set", testObject.statusOpen);
		assertFalse("No status should be set", testObject.statusNonCompliant);
		assertFalse("No status should be set", testObject.statusPartlyCompliant);
		
		testObject.runCustomVerification(null, requirementObject, null);
		
		assertTrue("As no trace was set, status should be open", testObject.statusOpen);
		
		assertFalse(testObject.statusCompliant);
		assertFalse(testObject.statusNonCompliant);
		assertFalse(testObject.statusPartlyCompliant);
	}
	
	@Test
	public void testRunCustomVerificationCompliant() {
		Requirement requirementObject = new Requirement(conceptRequirements);
		
		// The upper limit verification element is used as random element with a value property for testing 
		// (-> not actually used as verification here)
		UpperLimitVerification someValueElement = new UpperLimitVerification(conceptRequirements);
		someValueElement.setUpperBound(VALUE_COMPLIANT);
		requirementObject.getTrace().getTarget().add(someValueElement);
		
		// Set up the verification element
		TestBoundedValueRequirementsVerification testVerification = new TestBoundedValueRequirementsVerification();
		testVerification.setUpperBound(UPPER_LIMIT);
		testVerification.setLowerBound(LOWER_LIMIT);
		testVerification.setStatus(IVerification.STATUS_Open_NAME);
		ATypeDefinition propertyToBeVerified = someValueElement.getUpperBoundBean().getATypeInstance().getType();
		testVerification.setElementToBeVerified(propertyToBeVerified);
		
		// Run the verification
		testVerification.runCustomVerification(null, requirementObject, null);
		
		// Check results
		assertTrue("As value was in range, status should be compliant", testVerification.statusCompliant);
		assertFalse(testVerification.statusOpen);
		assertFalse(testVerification.statusNonCompliant);
		assertFalse(testVerification.statusPartlyCompliant);
	}
	
	@Test
	public void testRunCustomVerificationTooLow() {
		Requirement requirementObject = new Requirement(conceptRequirements);
		
		// The upper limit verification element is used as random element with a value property for testing 
		// (-> not actually used as verification here)
		UpperLimitVerification someValueElement = new UpperLimitVerification(conceptRequirements);
		someValueElement.setUpperBound(VALUE_TOO_LOW);
		requirementObject.getTrace().getTarget().add(someValueElement);
		
		// Set up the verification element
		TestBoundedValueRequirementsVerification testVerification = new TestBoundedValueRequirementsVerification();
		testVerification.setUpperBound(UPPER_LIMIT);
		testVerification.setLowerBound(LOWER_LIMIT);
		testVerification.setStatus(IVerification.STATUS_Open_NAME);
		ATypeDefinition propertyToBeVerified = someValueElement.getUpperBoundBean().getATypeInstance().getType();
		testVerification.setElementToBeVerified(propertyToBeVerified);
		
		// Run the verification
		testVerification.runCustomVerification(null, requirementObject, null);
		
		// Check results
		assertTrue("As value was not in range, status should non-compliant", testVerification.statusNonCompliant);
		assertFalse(testVerification.statusOpen);
		assertFalse(testVerification.statusCompliant);
		assertFalse(testVerification.statusPartlyCompliant);
	}
	
	
	@Test
	public void testRunCustomVerificationTooHigh() {
		Requirement requirementObject = new Requirement(conceptRequirements);
		
		// The upper limit verification element is used as random element with a value property for testing 
		// (-> not actually used as verification here)
		UpperLimitVerification someValueElement = new UpperLimitVerification(conceptRequirements);
		someValueElement.setUpperBound(VALUE_TOO_HIGH);
		requirementObject.getTrace().getTarget().add(someValueElement);
		
		// Set up the verification element
		TestBoundedValueRequirementsVerification testVerification = new TestBoundedValueRequirementsVerification();
		testVerification.setUpperBound(UPPER_LIMIT);
		testVerification.setLowerBound(LOWER_LIMIT);
		testVerification.setStatus(IVerification.STATUS_Open_NAME);
		ATypeDefinition propertyToBeVerified = someValueElement.getUpperBoundBean().getATypeInstance().getType();
		testVerification.setElementToBeVerified(propertyToBeVerified);
		
		// Run the verification
		testVerification.runCustomVerification(null, requirementObject, null);
		
		// Check results
		assertTrue("As value was not in range, status should non-compliant", testVerification.statusNonCompliant);
		assertFalse(testVerification.statusOpen);
		assertFalse(testVerification.statusCompliant);
		assertFalse(testVerification.statusPartlyCompliant);
	}
	
}
