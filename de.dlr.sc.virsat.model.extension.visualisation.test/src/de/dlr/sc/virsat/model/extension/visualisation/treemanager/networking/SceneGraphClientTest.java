/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.treemanager.networking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.dlr.sc.virsat.model.extension.visualisation.treemanager.IVisUpdateHandler;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.TreeManager;
import de.dlr.sc.visproto.VisProto.SceneGraph;

public class SceneGraphClientTest {
	
	@Test
	public void testConstructor() {
		TreeManager treeManager = new TreeManager();
		
		class MockSceneGraphClient extends SceneGraphClient {
			protected boolean sendRequest = false;
			
			MockSceneGraphClient(TreeManager treeManager, IVisUpdateHandler visUpdateHandler) {
				super(treeManager, visUpdateHandler);
			}
			
			@Override
			public void sendSceneGraph(SceneGraph sceneGraph) {
				sendRequest = true;
			}
		}
		
		MockSceneGraphClient sceneGraphClient = new MockSceneGraphClient(treeManager, null);
		
		assertFalse("No send request received yet", sceneGraphClient.sendRequest);
		treeManager.resumeSending();
		assertTrue("Send request received", sceneGraphClient.sendRequest);
	}

}
