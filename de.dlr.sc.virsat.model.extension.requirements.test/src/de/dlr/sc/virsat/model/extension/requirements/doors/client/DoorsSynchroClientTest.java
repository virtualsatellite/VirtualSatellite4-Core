/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.requirements.doors.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.lyo.client.exception.ResourceNotFoundException;
import org.eclipse.lyo.oslc.domains.rm.Requirement;
import org.junit.Before;
import org.junit.Test;


public class DoorsSynchroClientTest {

	private static final String TEST_PROJECT = "TestProject";
	private static final String SERVICE_PROVIDER_URL = "https://test.doors-server.de/rm/services.xml";
	private static final String QUERY_CAPABILITY = "https://test.doors-server.de/rm/query-capability"; 
	private static final String SERVICE_PROVIDER_NAME = "TestServiceProvider";
	private static final String REQ_ID = "123";
	
	@Before
	public void prepareMockups() {
		TestHelper helper = new TestHelper();
		helper.init();
	}
	
	@Test
	public void testLookUpServiceProvider() throws ResourceNotFoundException, IOException, URISyntaxException {
		String expectedServiceProvider = SERVICE_PROVIDER_URL;
		TestOslcClient testOslcClient = new TestOslcClient();
		DoorsSynchroClient.setOslcClient(testOslcClient);
		String serviceProvider = DoorsSynchroClient.lookUpServiceProviderUrl(TEST_PROJECT);
		
		assertEquals("The server returns the serviceProvider", expectedServiceProvider, serviceProvider);
	}
	
	@Test
	public void testlookUpQueryCapabilty() throws ResourceNotFoundException, IOException, URISyntaxException {
		String expectedQueryCapabilty = QUERY_CAPABILITY;
		TestOslcClient testOslcClient = new TestOslcClient();
		DoorsSynchroClient.setOslcClient(testOslcClient);
		String queryCapabilty = DoorsSynchroClient.lookUpQueryCapability();
				
		assertEquals("The server returns the queryCapability", expectedQueryCapabilty, queryCapabilty);		
	}
	
	@Test
	public void testGetRequirementById() throws ResourceNotFoundException, IOException, URISyntaxException {
		TestOslcClient testOslcClient = new TestOslcClient();
		MockDoorsSynchroClient synchroClient = new MockDoorsSynchroClient();
		MockDoorsSynchroClient.setDoorsSynchroClient(synchroClient);
		MockDoorsSynchroClient.setOslcClient(testOslcClient);
		Requirement req = MockDoorsSynchroClient.getRequirementById(REQ_ID);
		
		assertNotNull("Req given by server not null", req);				
	}
	
	@Test
	public void testGetServiceProvider() throws ResourceNotFoundException, IOException, URISyntaxException {
		TestOslcClient testOslcClient = new TestOslcClient();
		MockDoorsSynchroClient synchroClient = new MockDoorsSynchroClient();
		MockDoorsSynchroClient.setDoorsSynchroClient(synchroClient);
		MockDoorsSynchroClient.setOslcClient(testOslcClient);
		String expectedServiceProviderName = SERVICE_PROVIDER_NAME;
		String serviceProviderName = MockDoorsSynchroClient.getServiceProvider().getTitle();

		assertEquals(expectedServiceProviderName, serviceProviderName);
	}
	
	@Test
	public void testGetResourceShape() {
		
	}
	
}
