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

import org.eclipse.lyo.client.query.OslcQueryResult;
import org.eclipse.lyo.oslc.domains.rm.Requirement;
import org.eclipse.lyo.oslc4j.core.model.ResourceShape;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.mockito.Mockito;

public class TestHelper {
	
	private static ServiceProvider mockedServiceProvider = null;
	private static Requirement mockedRequirement = null;
	private static OslcQueryResult mockedOslcQueryResult = null;
	private static ResourceShape mockedResourceShape = null;
	
	public void init() {
		mockedServiceProvider = mockServiceProvider();
		mockedRequirement = mockRequirement();
		mockedResourceShape = mockResourceShape();
	}

	public ServiceProvider mockServiceProvider() {
		mockedServiceProvider = Mockito.mock(ServiceProvider.class);
		Mockito.when(mockedServiceProvider.getTitle()).thenReturn("TestServiceProvider");
		return mockedServiceProvider;
	}
	
	public Requirement mockRequirement() {
		mockedRequirement = Mockito.mock(Requirement.class);
		Mockito.when(mockedRequirement.getTitle()).thenReturn("TestRequirement");
		return mockedRequirement;
	}
	
	public ResourceShape mockResourceShape() {
		mockedResourceShape = Mockito.mock(ResourceShape.class);
		return mockedResourceShape;
	}
	
	public OslcQueryResult mockQueryResult() {
		mockedOslcQueryResult = Mockito.mock(OslcQueryResult.class);
		return mockedOslcQueryResult;
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public Response mockClientResponse() {
		Response mockedResponse = Mockito.mock(Response.class);
		Mockito.when(mockedResponse.readEntity(ServiceProvider.class)).thenReturn(mockedServiceProvider);
		Mockito.when(mockedResponse.readEntity(Requirement.class)).thenReturn(mockedRequirement);
		Mockito.when(mockedResponse.readEntity(ResourceShape.class)).thenReturn(mockedResourceShape );
		return mockedResponse;
	}

	public URI mockURI() {
		URI uri = Mockito.mock(URI.class);
		return uri;
	}
}
