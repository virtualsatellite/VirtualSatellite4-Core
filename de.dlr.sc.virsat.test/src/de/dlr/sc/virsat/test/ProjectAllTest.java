/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * 
 * @author fisc_ph
 *
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
				de.dlr.sc.virsat.commons.test.AllTests.class,
				de.dlr.sc.virsat.build.test.AllTests.class,
				de.dlr.sc.virsat.apps.test.AllTests.class,
				de.dlr.sc.virsat.excel.test.AllTests.class,
				de.dlr.sc.virsat.graphiti.test.AllTests.class,
				de.dlr.sc.virsat.project.test.AllTests.class,
				de.dlr.sc.virsat.server.test.AllTests.class,
				de.dlr.sc.virsat.team.test.AllTests.class,
				de.dlr.sc.virsat.model.test.AllTests.class,
				de.dlr.sc.virsat.model.edit.test.AllTests.class,
				de.dlr.sc.virsat.model.concept.test.AllTests.class,
				de.dlr.sc.virsat.model.ext.core.test.AllTests.class,
				de.dlr.sc.virsat.model.ext.core.test.AllTestsGen.class,
				de.dlr.sc.virsat.model.ext.core.edit.test.AllTests.class,
				de.dlr.sc.virsat.model.calculation.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.tests.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.tests.test.AllTestsGen.class,
				de.dlr.sc.virsat.model.extension.tests.edit.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.ps.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.ps.test.AllTestsGen.class,
				de.dlr.sc.virsat.model.extension.maturity.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.maturity.test.AllTestsGen.class,
				de.dlr.sc.virsat.model.extension.mechanical.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.mechanical.test.AllTestsGen.class,
				de.dlr.sc.virsat.model.extension.budget.mass.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.budget.mass.test.AllTestsGen.class,
				de.dlr.sc.virsat.model.extension.budget.power.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.budget.power.test.AllTestsGen.class,
				de.dlr.sc.virsat.model.extension.visualisation.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.visualisation.test.AllTestsGen.class,
				de.dlr.sc.virsat.model.extension.requirements.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.requirements.test.AllTestsGen.class,
				de.dlr.sc.virsat.model.extension.statemachines.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.statemachines.test.AllTestsGen.class,
				de.dlr.sc.virsat.model.extension.funcelectrical.test.AllTests.class,
				de.dlr.sc.virsat.model.extension.funcelectrical.test.AllTestsGen.class,
				})

/**
 * Test Class
 */
public class ProjectAllTest {   
}