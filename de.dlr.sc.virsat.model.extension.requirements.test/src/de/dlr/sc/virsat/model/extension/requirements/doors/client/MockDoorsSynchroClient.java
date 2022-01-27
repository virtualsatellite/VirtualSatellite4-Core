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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.Response;

import org.eclipse.lyo.client.OslcClient;
import org.eclipse.lyo.client.exception.ResourceNotFoundException;
import org.eclipse.lyo.client.query.OslcQueryParameters;
import org.eclipse.lyo.client.query.OslcQueryResult;
import org.eclipse.lyo.oslc.domains.rm.Requirement;
import org.eclipse.lyo.oslc4j.core.model.ResourceShape;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;

public class MockDoorsSynchroClient extends DoorsSynchroClient {

	
	private static OslcClient client = new OslcClient();
	
	public MockOslcQuery getOslcQuery(OslcClient client, String queryCapabilty, OslcQueryParameters queryParams) {
		return new MockOslcQuery(client, queryCapabilty, queryParams);
	}
	
	public static Requirement getReqFromMembers(OslcQueryResult result) {
		TestHelper helper = new TestHelper();
		Requirement req = helper.mockRequirement();
		return req;
	}
	
	public static Requirement getRequirementById(String id)
			throws ResourceNotFoundException, IOException, URISyntaxException {
		String queryCapability = lookUpQueryCapability();
		OslcQueryParameters queryParams = new OslcQueryParameters();
		queryParams.setSelect("*");
		queryParams.setPrefix(
				"rdf=<http://www.w3.org/1999/02/22-rdf-syntax-ns%23>,dcterms=<http://purl.org/dc/terms/>,oslc_rm=<http://open-services.net/ns/rm%23");
		queryParams.setWhere("dcterms:identifier=" + id);
		MockOslcQuery queryReqCollections = new MockOslcQuery(client, queryCapability, queryParams);
		OslcQueryResult result = queryReqCollections.submit();
		Requirement req = getReqFromMembers(result);
		return req;
	}
	
	public static ResourceShape getResourceShape(Requirement req) {
		TestHelper testHelper = new TestHelper();
		Response response = testHelper.mockClientResponse();
		ResourceShape resourceShape = response.readEntity(ResourceShape.class);
		if (resourceShape != null) {
			return resourceShape;
		}
		return null;
	}
	
	public static URI getURIFromInstanceShape(Requirement req) {
		URI reqLink = URI.create("https://test.req-link.de");
		return reqLink;
	}
	
	public static ServiceProvider getServiceProvider()
			throws ResourceNotFoundException, IOException, URISyntaxException {
		TestHelper testHelper = new TestHelper();
		ServiceProvider serviceProvider = testHelper.mockServiceProvider();
		return serviceProvider;
	}

}
