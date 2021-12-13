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

import org.eclipse.lyo.client.OslcClient;
import org.eclipse.lyo.client.query.OslcQuery;
import org.eclipse.lyo.client.query.OslcQueryParameters;
import org.eclipse.lyo.client.query.OslcQueryResult;

public class MockOslcQuery extends OslcQuery {

	public MockOslcQuery(OslcClient oslcClient, String capabilityUrl, OslcQueryParameters oslcQueryParams) {
		super(oslcClient, capabilityUrl, oslcQueryParams);
	}
	
	@Override
	public OslcQueryResult submit() {
		TestHelper testHelper = new TestHelper();
		OslcQueryResult result = testHelper.mockQueryResult();
		
		return result;	
	}
}
