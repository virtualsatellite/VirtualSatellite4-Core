/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.mass.verification;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassEquipment;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassRequirementsVerification;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassSummary;
import de.dlr.sc.virsat.model.extension.requirements.model.BoundedValueVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;

public class MassRequirementsVerificationImplTest extends AConceptProjectTestCase {
	
	private static final String MASS_CONCEPT_ID = "de.dlr.sc.virsat.model.extension.budget.mass";
	private static final String REQ_CONCEPT_ID = "de.dlr.sc.virsat.model.extension.requirements";
	private Concept conceptMass;
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
		conceptMass = loadConceptAndInstallToRepository(MASS_CONCEPT_ID, repository);
	}
	
	public class TestMassRequirementsVerification extends MassRequirementsVerificationImpl {
		boolean statusCompliant = false;
		boolean statusOpen = false;
		boolean statusNonCompliant = false;
		boolean statusPartlyCompliant = false;
		
		@Override
		protected void setStatusCompliant() {
			statusCompliant = true;
		}
		
		@Override
		protected void setStatusOpen() {
			statusOpen = true;
		}
		
		@Override
		protected void setStatusNonCompliant() {
			statusNonCompliant = true;
		}
		
		@Override
		protected void setStatusPartlyCompliant() {
			statusPartlyCompliant = true;
		}
	}
	
	@Test
	public void testRunCustomVerificationNoTrace() {
		TestMassRequirementsVerification testObject = new TestMassRequirementsVerification();
		
		MassRequirementsVerification massVerification = new MassRequirementsVerification(conceptMass);
		Requirement requirementObject = new Requirement(conceptRequirements);
		
		assertFalse("No status should be set", testObject.statusCompliant);
		assertFalse("No status should be set", testObject.statusOpen);
		assertFalse("No status should be set", testObject.statusNonCompliant);
		assertFalse("No status should be set", testObject.statusPartlyCompliant);
		
		testObject.runCustomVerification(null, massVerification, requirementObject, null);
		
		assertTrue("As no trace was set, status should be open", testObject.statusOpen);
		
		assertFalse(testObject.statusCompliant);
		assertFalse(testObject.statusNonCompliant);
		assertFalse(testObject.statusPartlyCompliant);
	}
	
	@Test
	public void testRunCustomVerificationCompliant() {
		TestMassRequirementsVerification testObject = new TestMassRequirementsVerification();
		
		MassRequirementsVerification massVerification = new MassRequirementsVerification(conceptMass);
		massVerification.setUpperBound(UPPER_LIMIT);
		massVerification.setLowerBound(LOWER_LIMIT);
		massVerification.setStatus(IVerification.STATUS_Open_NAME);
		
		Requirement requirementObject = new Requirement(conceptRequirements);
		
		MassEquipment someMassEquipment = new MassEquipment(conceptMass);
		someMassEquipment.setMassWithMargin(VALUE_COMPLIANT);
		requirementObject.getTrace().getTarget().add(someMassEquipment);
		
		testObject.runCustomVerification(null, massVerification, requirementObject, null);
		
		assertTrue("As value was in range, status should be compliant", testObject.statusCompliant);
		
		assertFalse(testObject.statusOpen);
		assertFalse(testObject.statusNonCompliant);
		assertFalse(testObject.statusPartlyCompliant);
	}
	
	@Test
	public void testRunCustomVerificationTooLow() {
		TestMassRequirementsVerification testObject = new TestMassRequirementsVerification();
		
		MassRequirementsVerification massVerification = new MassRequirementsVerification(conceptMass);
		massVerification.setUpperBound(UPPER_LIMIT);
		massVerification.setLowerBound(LOWER_LIMIT);
		massVerification.setStatus(IVerification.STATUS_FullyCompliant_NAME);
		
		Requirement requirementObject = new Requirement(conceptRequirements);
		
		MassEquipment someMassEquipment = new MassEquipment(conceptMass);
		someMassEquipment.setMassWithMargin(VALUE_TOO_LOW);
		requirementObject.getTrace().getTarget().add(someMassEquipment);
		
		testObject.runCustomVerification(null, massVerification, requirementObject, null);
		
		assertTrue("As value was not in range, status should not be compliant", testObject.statusNonCompliant);
		
		assertFalse(testObject.statusOpen);
		assertFalse(testObject.statusCompliant);
		assertFalse(testObject.statusPartlyCompliant);
	}
	
	@Test
	public void testRunCustomVerificationTooHigh() {
		TestMassRequirementsVerification testObject = new TestMassRequirementsVerification();
		
		MassRequirementsVerification massVerification = new MassRequirementsVerification(conceptMass);
		massVerification.setUpperBound(UPPER_LIMIT);
		massVerification.setLowerBound(LOWER_LIMIT);
		massVerification.setStatus(IVerification.STATUS_FullyCompliant_NAME);
		
		Requirement requirementObject = new Requirement(conceptRequirements);
		
		MassEquipment someMassEquipment = new MassEquipment(conceptMass);
		someMassEquipment.setMassWithMargin(VALUE_TOO_HIGH);
		requirementObject.getTrace().getTarget().add(someMassEquipment);
		
		testObject.runCustomVerification(null, massVerification, requirementObject, null);
		
		assertTrue("As value was not in range, status should not be compliant", testObject.statusNonCompliant);
		
		assertFalse(testObject.statusOpen);
		assertFalse(testObject.statusCompliant);
		assertFalse(testObject.statusPartlyCompliant);
	}
	
	@Test
	public void testRunCustomVerificationOtherVerification() {
		TestMassRequirementsVerification testObject = new TestMassRequirementsVerification();
		BoundedValueVerification otherVerification = new BoundedValueVerification(conceptRequirements);
		Requirement reqiurement = new Requirement(conceptRequirements);
		
		testObject.runCustomVerification(null, otherVerification, reqiurement, null); // Well, and this one should not throw an exception
		
		assertFalse("No status should be set", testObject.statusCompliant);
		assertFalse("No status should be set", testObject.statusOpen);
		assertFalse("No status should be set", testObject.statusNonCompliant);
		assertFalse("No status should be set", testObject.statusPartlyCompliant);
	}
	
	@Test
	public void testRunCustomVerificationMassSummary() {
		TestMassRequirementsVerification testObject = new TestMassRequirementsVerification();
		
		MassRequirementsVerification massVerification = new MassRequirementsVerification(conceptMass);
		massVerification.setUpperBound(UPPER_LIMIT);
		massVerification.setLowerBound(LOWER_LIMIT);
		massVerification.setStatus(IVerification.STATUS_Open_NAME);
		
		Requirement requirementObject = new Requirement(conceptRequirements);
		
		MassSummary someMassEquipment = new MassSummary(conceptMass);
		someMassEquipment.setMassWithMargin(VALUE_COMPLIANT);
		requirementObject.getTrace().getTarget().add(someMassEquipment);
		
		testObject.runCustomVerification(null, massVerification, requirementObject, null);
		
		assertTrue("As value was in range, status should be compliant", testObject.statusCompliant);
		
		assertFalse(testObject.statusOpen);
		assertFalse(testObject.statusNonCompliant);
		assertFalse(testObject.statusPartlyCompliant);
	}

}
