/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.verification.build;

import static org.junit.Assert.assertEquals;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.requirements.model.DefaultVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.IVerification;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;

/**
 *
 */
public class RequirementsStatusUpdaterTest extends AConceptProjectTestCase {
	
	private Concept requirementsConcept;
	private Requirement requirement;
	private DefaultVerification verification;
	private RequirementsStatusUpdater updater;
	private RequirementsSpecification specification;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		updater = new RequirementsStatusUpdater();
		requirementsConcept = loadConceptFromPlugin("de.dlr.sc.virsat.model.extension.requirements");
		specification = new RequirementsSpecification(requirementsConcept);
		requirement = new Requirement(requirementsConcept);
		verification = new DefaultVerification(requirementsConcept);
		requirement.getVerification().add(verification);
		specification.getRequirements().add(requirement);
	}
	
	@Test
	public void testComputeStatusSingleOpen() {
		verification.setStatus(IVerification.STATUS_Open_NAME);
		updater.execute(specification, editingDomain, new NullProgressMonitor());
		
		assertEquals(Requirement.STATUS_Open_NAME, requirement.getStatus());
	}
	
	@Test
	public void testComputeStatusSingleNonCompliant() {
		verification.setStatus(IVerification.STATUS_NonCompliant_NAME);
		updater.execute(specification, editingDomain, new NullProgressMonitor());
		
		assertEquals(Requirement.STATUS_NonCompliant_NAME, requirement.getStatus());
	}
	
	@Test
	public void testComputeStatusSingleCompliant() {
		verification.setStatus(IVerification.STATUS_FullyCompliant_NAME);
		updater.execute(specification, editingDomain, new NullProgressMonitor());
		
		assertEquals(Requirement.STATUS_FullyCompliant_NAME, requirement.getStatus());
	}
	
	@Test
	public void testComputeStatusSinglePartlyCompliant() {
		verification.setStatus(IVerification.STATUS_PartialCompliant_NAME);
		updater.execute(specification, editingDomain, new NullProgressMonitor());
		
		assertEquals(Requirement.STATUS_PartialCompliant_NAME, requirement.getStatus());
	}
	
	@Test
	public void testComputeStatusMultipleMixed() {
		DefaultVerification secondVerification = new DefaultVerification(requirementsConcept);
		requirement.getVerification().add(secondVerification);
		verification.setStatus(IVerification.STATUS_NonCompliant_NAME);
		secondVerification.setStatus(IVerification.STATUS_FullyCompliant_NAME);
		
		updater.execute(specification, editingDomain, new NullProgressMonitor());
		
		assertEquals(Requirement.STATUS_PartialCompliant_NAME, requirement.getStatus());
	}
	
	@Test
	public void testComputeStatusMultipleMixedOpen() {
		DefaultVerification secondVerification = new DefaultVerification(requirementsConcept);
		requirement.getVerification().add(secondVerification);
		verification.setStatus(IVerification.STATUS_Open_NAME);
		secondVerification.setStatus(IVerification.STATUS_FullyCompliant_NAME);
		
		updater.execute(specification, editingDomain, new NullProgressMonitor());
		
		assertEquals(Requirement.STATUS_Open_NAME, requirement.getStatus());
	}
	
	@Test
	public void testComputeStatusMultipleMixedPartlyPositive() {
		DefaultVerification secondVerification = new DefaultVerification(requirementsConcept);
		requirement.getVerification().add(secondVerification);
		verification.setStatus(IVerification.STATUS_PartialCompliant_NAME);
		secondVerification.setStatus(IVerification.STATUS_FullyCompliant_NAME);
		
		updater.execute(specification, editingDomain, new NullProgressMonitor());
		
		assertEquals(Requirement.STATUS_PartialCompliant_NAME, requirement.getStatus());
	}
	
	@Test
	public void testComputeStatusMultipleMixedPartlyNegative() {
		DefaultVerification secondVerification = new DefaultVerification(requirementsConcept);
		requirement.getVerification().add(secondVerification);
		verification.setStatus(IVerification.STATUS_PartialCompliant_NAME);
		secondVerification.setStatus(IVerification.STATUS_NotApplicable_NAME);
		
		updater.execute(specification, editingDomain, new NullProgressMonitor());
		
		assertEquals(Requirement.STATUS_PartialCompliant_NAME, requirement.getStatus());
	}

}
