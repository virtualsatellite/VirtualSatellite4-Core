/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.verification.build.steps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;

/**
 *
 */
public class VerificationConfigurationElementRunnerTest extends AConceptProjectTestCase {
	
	@Override
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
	}
	
	@Test
	public void testDoVerificationStep() {
		
		DummyCustomVerification.reset();
		assertFalse(DummyCustomVerification.didRun());
		
		VerificationConfigurationElementRunner runner = new VerificationConfigurationElementRunner();
		runner.setEditingDomain(editingDomain);
		runner.doVerificationStep(new DummyCustomVerification(), null, new NullProgressMonitor());
		
		assertTrue(DummyCustomVerification.didRun());
	}

}
