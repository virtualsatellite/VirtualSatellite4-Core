/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.server.auth.LoginServiceFactoryTest;
import de.dlr.sc.virsat.server.auth.filter.CorsFilterTest;
import de.dlr.sc.virsat.server.configuration.RepositoryConfigurationTest;
import de.dlr.sc.virsat.server.configuration.ServerConfigurationTest;
import de.dlr.sc.virsat.server.controller.RepoManagementControllerTest;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtilityTest;
import de.dlr.sc.virsat.server.dataaccess.ServerConceptTest;
import de.dlr.sc.virsat.server.dataaccess.TransactionalJsonProviderTest;
import de.dlr.sc.virsat.server.jetty.VirSatJettyServerTest;
import de.dlr.sc.virsat.server.repository.ServerRepoHelperTest;
import de.dlr.sc.virsat.server.repository.ServerRepositoryTest;
import de.dlr.sc.virsat.server.resources.ModelAccessResourceTest;
import de.dlr.sc.virsat.server.resources.ProjectManagementResourceTest;
import de.dlr.sc.virsat.server.resources.modelaccess.CategoryAssignmentResourceTest;
import de.dlr.sc.virsat.server.resources.modelaccess.DisciplineResourceTest;
import de.dlr.sc.virsat.server.resources.modelaccess.PropertyResourceTest;
import de.dlr.sc.virsat.server.resources.modelaccess.QudvResourceTest;
import de.dlr.sc.virsat.server.resources.modelaccess.StructuralElementInstanceResourceTest;
import junit.framework.JUnit4TestAdapter;

/**
 * 
 * test Suite for the VirSat Server
 */
@RunWith(Suite.class)

@SuiteClasses({
	ServerRepositoryTest.class,
	ServerConfigurationTest.class,
	ServerRepoHelperTest.class,
	RepoManagementControllerTest.class,
	RepositoryConfigurationTest.class,
	ProjectManagementResourceTest.class,
	LoginServiceFactoryTest.class,
	ModelAccessResourceTest.class,
	PropertyResourceTest.class,
	CategoryAssignmentResourceTest.class,
	StructuralElementInstanceResourceTest.class,
	TransactionalJsonProviderTest.class,
	RepositoryUtilityTest.class,
	VirSatJettyServerTest.class,
	CorsFilterTest.class,
	ServerConceptTest.class,
	QudvResourceTest.class,
	DisciplineResourceTest.class
})

public class AllTests {

	/**
	 * the constructor
	 */
	private AllTests() {
	}
	/**
	 * initializes the test suite
	 * @return a newt JUnit test adapter
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}