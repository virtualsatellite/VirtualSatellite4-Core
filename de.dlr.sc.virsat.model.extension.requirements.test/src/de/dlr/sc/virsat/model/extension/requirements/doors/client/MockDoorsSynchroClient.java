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
import org.eclipse.lyo.client.query.OslcQueryParameters;
import org.eclipse.lyo.client.query.OslcQueryResult;
import org.eclipse.lyo.oslc.domains.rm.Requirement;

public class MockDoorsSynchroClient extends DoorsSynchroClient {

	public MockOslcQuery getOslcQuery(OslcClient client, String queryCapabilty, OslcQueryParameters queryParams) {
		return new MockOslcQuery(client, queryCapabilty, queryParams);
	}
	
	public Requirement getReqFromMembers(OslcQueryResult result) {
		TestHelper helper = new TestHelper();
		Requirement req = helper.mockRequirement();
		return req;
	}
}
