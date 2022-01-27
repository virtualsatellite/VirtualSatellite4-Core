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
import java.net.URISyntaxException;

import javax.ws.rs.core.Response;

import org.eclipse.lyo.client.IOslcClient;
import org.eclipse.lyo.client.OslcClient;
import org.eclipse.lyo.client.exception.ResourceNotFoundException;

public class TestOslcClient extends OslcClient implements IOslcClient {

	private static final  String SERVICE_PROVIDER_URL = "https://test.doors-server.de/rm/services.xml";
	private static final String QUERY_CAPABILITY_URL = "https://test.doors-server.de/rm/query-capability";

	@Override
	public Response getResource(String url, String mediaType) {
		TestHelper testHelper = new TestHelper();
		Response response = testHelper.mockClientResponse();
		return response;
	}

	@Override
	public String lookupServiceProviderUrl(String catalogUrl, String serviceProviderTitle)
			throws IOException, URISyntaxException, ResourceNotFoundException {
		return SERVICE_PROVIDER_URL;
	}
	
	@Override
	public String lookupQueryCapability(String serviceProviderUrl, String oslcDomain, String oslcResourceType)
			throws IOException, URISyntaxException, ResourceNotFoundException {
		return QUERY_CAPABILITY_URL;
	}
}
