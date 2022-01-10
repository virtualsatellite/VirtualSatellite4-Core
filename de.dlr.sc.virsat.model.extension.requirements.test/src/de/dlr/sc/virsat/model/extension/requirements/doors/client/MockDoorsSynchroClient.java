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

import java.net.URI;

import javax.ws.rs.core.Response;

import org.eclipse.lyo.client.OSLCConstants;
import org.eclipse.lyo.client.OslcClient;
import org.eclipse.lyo.client.query.OslcQueryParameters;
import org.eclipse.lyo.client.query.OslcQueryResult;
import org.eclipse.lyo.oslc.domains.rm.Requirement;
import org.eclipse.lyo.oslc4j.core.model.ResourceShape;

public class MockDoorsSynchroClient extends DoorsSynchroClient {

	
	private static OslcClient client;
	
	public MockOslcQuery getOslcQuery(OslcClient client, String queryCapabilty, OslcQueryParameters queryParams) {
		return new MockOslcQuery(client, queryCapabilty, queryParams);
	}
	
	public Requirement getReqFromMembers(OslcQueryResult result) {
		TestHelper helper = new TestHelper();
		Requirement req = helper.mockRequirement();
		return req;
	}
	
	public static ResourceShape getResourceShape(Requirement req) {
		URI reqLink = getURIFromInstanceShape(req);
		Response response = client.getResource(reqLink.toString(), OSLCConstants.CT_RDF);
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
}
