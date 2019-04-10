/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.resource.command;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test case for testing the RemoveResourceCommand
 * @author fisc_ph
 *
 */
public class RemoveResourceCommandTest {

	public static final String TEST_MODEL_EXTENSION = "modeltest";

	private ResourceSet resSet;
	private Resource resource1;
	private Resource resource2;
	
	private URI resUri1;
	private URI resUri2;
	
	@Before
	public void setUp() throws Exception {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put(TEST_MODEL_EXTENSION, new XMIResourceFactoryImpl());
	    resSet = new ResourceSetImpl();
	    String wsFullPath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toOSString();
	    resUri1 = URI.createFileURI(wsFullPath + "/removeResourceTest/Component1." + TEST_MODEL_EXTENSION);
	    resUri2 = URI.createFileURI(wsFullPath + "/removeResourceTest/Component2." + TEST_MODEL_EXTENSION);
	    resource1 = resSet.createResource(resUri1);
	    resource2 = resSet.createResource(resUri2);
	    resource1.save(Collections.EMPTY_MAP);
	    resource2.save(Collections.EMPTY_MAP);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecute() {
		assertEquals("There are resources in the resourceSet", 2, resSet.getResources().size());
		
		RemoveResourceCommand command = new RemoveResourceCommand(resSet, resource1);
		command.execute();
		
		assertEquals("There are resources in the resourceSet", 1, resSet.getResources().size());
		assertThat("Still contains the other Reosurce", resSet.getResources(), hasItems(resource2));
	}
	
	@Test
	public void testExecuteWithList() {
		assertEquals("There are resources in the resourceSet", 2, resSet.getResources().size());
		
		List<Resource> resources = new ArrayList<>();
		resources.add(resource1);
		resources.add(resource2);
		
		RemoveResourceCommand command = new RemoveResourceCommand(resSet, resources);
		command.execute();
		
		assertTrue("resources is empty", resSet.getResources().isEmpty());
	}

	@Test
	public void testUndo() {
		assertEquals("There are resources in the resourceSet", 2, resSet.getResources().size());
		
		RemoveResourceCommand command = new RemoveResourceCommand(resSet, resource1);
		command.execute();
		
		assertEquals("There are resources in the resourceSet", 1, resSet.getResources().size());
		assertThat("Still contains the other Reosurce", resSet.getResources(), hasItems(resource2));
		
		command.undo();
		assertEquals("There are resources in the resourceSet", 2, resSet.getResources().size());
		assertThat("Still contains the other Reosurce", resSet.getResources(), hasItems(resource2));
		assertThat("Still contains the other Reosurce", resSet.getResources(), hasItems(resource2, resource1));
	}

	
	@Test
	public void testRedo() {
		assertEquals("There are resources in the resourceSet", 2, resSet.getResources().size());
		
		RemoveResourceCommand command = new RemoveResourceCommand(resSet, resource1);
		command.execute();
		
		assertEquals("There are resources in the resourceSet", 1, resSet.getResources().size());
		assertThat("Still contains the other Reosurce", resSet.getResources(), hasItems(resource2));
		
		command.undo();
		
		assertEquals("There are resources in the resourceSet", 2, resSet.getResources().size());
		assertThat("Still contains the other Reosurce", resSet.getResources(), hasItems(resource2, resource1));
		
		command.redo();
		
		assertEquals("There are resources in the resourceSet", 1, resSet.getResources().size());
		assertThat("Still contains the other Reosurce", resSet.getResources(), hasItems(resource2));
	}

	@Test
	public void testCanExecute() {
		RemoveResourceCommand command = new RemoveResourceCommand(resSet, resource1);
		assertTrue("Command is executable", command.canExecute());
	}

	@Test
	public void testCanUndo() {
		RemoveResourceCommand command = new RemoveResourceCommand(resSet, resource1);
		command.execute();
		
		assertTrue("Command is undoable", command.canUndo());
	}
}
