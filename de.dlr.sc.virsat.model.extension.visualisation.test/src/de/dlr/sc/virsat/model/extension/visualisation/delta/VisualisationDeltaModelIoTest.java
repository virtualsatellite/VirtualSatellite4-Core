/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.delta;


import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;
import de.dlr.sc.virsat.model.extension.visualisation.shape.VisualisationShape;

/**
 * Test class to verify the Delta Model IO into XML
 * @author fisc_ph
 *
 */
public class VisualisationDeltaModelIoTest {

	private static final String TEST_PROJECT_NAME = "DeltaVisIO";
	private static final String TEST_FILE_NAME = "DeltaModel.xml";

	private File testFile;
	
	@Before
	public void setUp() throws Exception {
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
	
		/**
		 * Create a project in the workspace
		 */
		IProject testProject = wsRoot.getProject(TEST_PROJECT_NAME);
		if (!testProject.exists()) {
			testProject.create(null);
		}
		testProject.open(null);
		
		testFile = new File(testProject.getLocation().toOSString() + "/" + TEST_FILE_NAME);
	}

	@After
	public void tearDown() throws Exception {
	}

	// test case fails on tycho, when being executed. tycho does not get the classes for the model from the class path.
	@Ignore
	@Test
	public void testReadWriteModel() throws FileNotFoundException {
		
		VisualisationDeltaModel vdm = new VisualisationDeltaModel();
		
		//CHECKSTYLE:OFF
		ColorDelta cd1 = new ColorDelta(new VirSatUuid().toString(), 0xFFFFFF);
		ColorDelta cd2 = new ColorDelta(new VirSatUuid().toString(), 0x010101);
		//CHECKSTYLE:ON
		
		vdm.colorDeltas.put(cd1.shapeId, cd1);
		vdm.colorDeltas.put(cd2.shapeId, cd2);
		
		Shape shape = new Shape();
		shape.shape = VisualisationShape.BOX;
		shape.id = new VirSatUuid().toString();
		
		CloneShapeDelta csd = new CloneShapeDelta(shape);
		vdm.cloneShapeDeltas.add(csd);
		
		GhostShapeDelta gsd = new GhostShapeDelta("1234", shape);
		vdm.ghostShapeDeltas.add(gsd);
		
		// Write the model to XML
		VisualisationDeltaModelIo.writeModel(vdm, new FileOutputStream(testFile));
		
		// Read the model from XML
		VisualisationDeltaModel newVdm = VisualisationDeltaModelIo.readModel(new FileInputStream(testFile));
		
		// Check the content of the model
		assertEquals("has same amount of ColorDeltas", vdm.colorDeltas.size(), newVdm.colorDeltas.size());
		assertThat("Color Deltas are contained in read model", newVdm.colorDeltas.values(), hasItems(cd1, cd2));
	
		assertThat("Clone Deltas are contained in read model", newVdm.cloneShapeDeltas, hasItems(csd));
		
		assertThat("Ghost Deltas are contained in read model", newVdm.ghostShapeDeltas, hasItems(gsd));
	}
}
