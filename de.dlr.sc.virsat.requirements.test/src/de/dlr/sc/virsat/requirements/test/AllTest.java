/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.dlr.sc.virsat.requirements.tracing.test.AllTests;
import de.dlr.sc.virsat.requirements.tracing.traceModel.tests.TraceModelAllTests;

/**
 * 
 * @author fisc_ph
 *
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
				AllTests.class,
				TraceModelAllTests.class
				})

/**
 * Test Class
 */
public class AllTest {   
}