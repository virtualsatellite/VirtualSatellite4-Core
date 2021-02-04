/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.doors.synchro;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.lyo.client.exception.ResourceNotFoundException;
import org.junit.Test;

public class DoorsSynchroClientTest {

	//mock up connection to DNG server
	String catalogUrl = "https://jazz.server.com:9443/ccm/oslc/workitems/catalog";
	String serviceProviderTitle = "testServiceProvider";
	
	
	@Test
	public static void getServiceProviderUrl() throws ResourceNotFoundException, IOException, URISyntaxException {
	}
	
	@Test 
	public static void getQueryCapability() throws ResourceNotFoundException, IOException, URISyntaxException {
	}
	
	@Test
	public static void getCreationFactory() throws ResourceNotFoundException, IOException, URISyntaxException {
	}
	
	@Test
	public static void getRequirement() {
		
	}
	
	@Test
	public static void readRequirement() {

	}
	
	@Test
	public static void getAllRequirements() {
		
	}
	
	@Test
	public static void updateRequirement() {
		
	}
	
	@Test
	public static void createRequirement() {
		
	}
	
	@Test
	public static void parseXml() {
		
	}
}
