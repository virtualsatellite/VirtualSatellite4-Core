/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.Before;


import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

public class VirSatProjectResourceChangeListenerTest extends AProjectTestCase {

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		listener = new TestProjectChangeListener(testProject);
	}
	
	@After
	@Override
	public void tearDown() throws CoreException {
		// Always remove the listener again
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(listener);
		super.tearDown();
	}
	
	protected TestProjectChangeListener listener;
	
	/**
	 * The Listener under test
	 */
	static class TestProjectChangeListener extends AVirSatProjectResourceChangeListener {
		
		int calledPrecondition = 0;
		int calledPostcondition = 0;
		int calledRemoved = 0;
		int calledChanged = 0;
		int calledAdded = 0;
		int calledResourceChanged = 0;
		
		TestProjectChangeListener(IProject virSatProject) {
			super(virSatProject);
		}

		
		@Override
		public void handlePreCondition() {
			calledPrecondition++;
		}
		
		@Override
		public void handlePostCondition() {
			calledPostcondition++;
		}
	
		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			calledResourceChanged++;
			super.resourceChanged(event);
		}
		
		ArrayList<IResource> calledRemovedDvlmResources;
		ArrayList<IResource> calledChangedDvlmResources;
		ArrayList<IResource> calledAddedDvlmResources;
		
		@Override
		public void handleRemovedDvlmResources(List<IResource> removedDvlmResources) {
			calledRemoved++;
			calledRemovedDvlmResources = new ArrayList<>(removedDvlmResources);
		}
		
		@Override
		public void handleChangedDvlmResources(List<IResource> changedDvlmResources) {
			calledChanged++;
			calledChangedDvlmResources = new ArrayList<>(changedDvlmResources);
		}
		
		@Override
		public void handleAddedDvlmResources(List<IResource> addedDvlmResources) {
			calledAdded++;
			calledAddedDvlmResources = new ArrayList<>(addedDvlmResources);
		}
	};
	
	@Test
	public void testHandleClosedProject() throws CoreException {
		
		editingDomain.saveAll();
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener);

		// Close the project and create another file beforehand, No notification should arrive on a closed project
		listener.calledResourceChanged = 0;
		testProject.close(null);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertNotEquals("Listener got called again", 0, listener.calledResourceChanged);

		assertEquals("Called method correct amount of times", 0, listener.calledPrecondition);
		assertEquals("Called method correct amount of times", 0, listener.calledPostcondition);
		assertEquals("Called method correct amount of times", 0, listener.calledAdded);
		assertEquals("Called method correct amount of times", 0, listener.calledRemoved);
		assertEquals("Called method correct amount of times", 0, listener.calledChanged);
	}
	
	@Test
	public void testHandleAddedDvlmResources() throws CoreException {
				
		editingDomain.saveAll();
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener);
		
		// Create a new resource for a SEI in the resourceSet this should issue a notification on the added resources
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		Resource seiResource = editingDomain.getResourceSet().safeGetResource(seiFile, true);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		assertNotNull("Got a resource for the SEI", seiResource);
		
		assertEquals("Called method correct amount of times", 1, listener.calledPrecondition);
		assertEquals("Called method correct amount of times", 1, listener.calledPostcondition);
		assertEquals("Called method correct amount of times", 1, listener.calledAdded);
		assertEquals("Called method correct amount of times", 0, listener.calledRemoved);
		assertEquals("Called method correct amount of times", 0, listener.calledChanged);
		
		assertThat("List contains correct resources", listener.calledAddedDvlmResources, hasItem(seiFile));
		
		// Now add an arbitrary file, which is also part of the resourceSet this should also notify the added resources
		IFile randomFile = testProject.getFile("testfile.ecore");
		Resource randomResource = editingDomain.getResourceSet().safeGetResource(randomFile, true);
		assertNotNull("Got a resource for the random ecore", randomResource);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Called method correct amount of times", 2, listener.calledPrecondition);
		assertEquals("Called method correct amount of times", 2, listener.calledPostcondition);
		assertEquals("Called method correct amount of times", 2, listener.calledAdded);
		assertEquals("Called method correct amount of times", 0, listener.calledRemoved);
		assertEquals("Called method correct amount of times", 0, listener.calledChanged);
		
		assertThat("List contains correct resources", listener.calledAddedDvlmResources, hasItem(randomFile));
		
		// And now add a random file which is not in the ResourceSet. No Added file should be notified
		listener.calledResourceChanged = 0;
		IFile randomNonRsFile = testProject.getFile("random.txt");
		randomNonRsFile.create(new ByteArrayInputStream("test".getBytes()), true, null);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		assertNotEquals("Listener got called again", 0, listener.calledResourceChanged);
		
		assertEquals("Called method correct amount of times", 2, listener.calledPrecondition);
		assertEquals("Called method correct amount of times", 2, listener.calledPostcondition);
		assertEquals("Called method correct amount of times", 2, listener.calledAdded);
		assertEquals("Called method correct amount of times", 0, listener.calledRemoved);
		assertEquals("Called method correct amount of times", 0, listener.calledChanged);
	}

	@Test
	public void testHandleRemovedDvlmResources() throws CoreException {
		editingDomain.saveAll();
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		// Create a new resource for a SEI in the resourceSet this should issue a notification on the added resources
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		Resource seiResource = editingDomain.getResourceSet().safeGetResource(seiFile, true);
		assertNotNull("Got a resource for the SEI", seiResource);
		
		// Create another non dvlm resource in the resourceSet
		IFile randomFile = testProject.getFile("testfile.ecore");
		Resource randomResource = editingDomain.getResourceSet().safeGetResource(randomFile, true);
		assertNotNull("Got a resource for the random ecore", randomResource);
	
		// Crate some random file in the workspace
		IFile randomNonRsFile = testProject.getFile("random.txt");
		randomNonRsFile.create(new ByteArrayInputStream("test".getBytes()), true, null);

		// Now add the listener and remove one file after the other
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener);
		
		// Remove the file of the SEI, this should create a notification
		seiFile.delete(true, null);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Called method correct amount of times", 1, listener.calledPrecondition);
		assertEquals("Called method correct amount of times", 1, listener.calledPostcondition);
		assertEquals("Called method correct amount of times", 0, listener.calledAdded);
		assertEquals("Called method correct amount of times", 1, listener.calledRemoved);
		assertEquals("Called method correct amount of times", 0, listener.calledChanged);
		
		assertThat("List contains correct resources", listener.calledRemovedDvlmResources, hasItem(seiFile));
		
		// Now add an arbitrary file, which is also part of the resourceSet this should also notify the added resources
		randomFile.delete(true, null);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Called method correct amount of times", 2, listener.calledPrecondition);
		assertEquals("Called method correct amount of times", 2, listener.calledPostcondition);
		assertEquals("Called method correct amount of times", 0, listener.calledAdded);
		assertEquals("Called method correct amount of times", 2, listener.calledRemoved);
		assertEquals("Called method correct amount of times", 0, listener.calledChanged);
		
		assertThat("List contains correct resources", listener.calledRemovedDvlmResources, hasItem(randomFile));
		
		// And now add a random file which is not in the ResourceSet. No Added file should be notified
		listener.calledResourceChanged = 0;
		randomNonRsFile.delete(true, null);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		assertNotEquals("Listener got called again", 0, listener.calledResourceChanged);
		
		assertEquals("Called method correct amount of times", 2, listener.calledPrecondition);
		assertEquals("Called method correct amount of times", 2, listener.calledPostcondition);
		assertEquals("Called method correct amount of times", 0, listener.calledAdded);
		assertEquals("Called method correct amount of times", 2, listener.calledRemoved);
		assertEquals("Called method correct amount of times", 0, listener.calledChanged);
	}

	@Test
	public void testHandleChangedDvlmResources() throws CoreException, IOException {
		editingDomain.saveAll();
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		// Create a new resource for a SEI in the resourceSet this should issue a notification on the added resources
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		Resource seiResource = editingDomain.getResourceSet().safeGetResource(seiFile, true);
		executeAsCommand(() -> seiResource.getContents().add(sei));
		editingDomain.saveResourceIgnorePermissions(seiResource);
		assertNotNull("Got a resource for the SEI", seiResource);
		
		// Create another non dvlm resource in the resourceSet
		IFile randomFile = testProject.getFile("testfile.ecore");
		Resource randomResource = editingDomain.getResourceSet().safeGetResource(randomFile, true);
		EPackage pckge = EcoreFactory.eINSTANCE.createEPackage();
		executeAsCommand(() -> randomResource.getContents().add(pckge));
		editingDomain.saveResourceIgnorePermissions(randomResource);
		assertNotNull("Got a resource for the random ecore", randomResource);
	
		// Crate some random file in the workspace
		IFile randomNonRsFile = testProject.getFile("random.txt");
		randomNonRsFile.create(new ByteArrayInputStream("test".getBytes()), true, null);

		// Now add the listener and change one file after the other
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener);
		
		// Remove the file of the SEI, this should create a notification
		executeAsCommand(() -> sei.setName("TestName"));
		editingDomain.saveResourceIgnorePermissions(seiResource);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Called method correct amount of times", 1, listener.calledPrecondition);
		assertEquals("Called method correct amount of times", 1, listener.calledPostcondition);
		assertEquals("Called method correct amount of times", 0, listener.calledAdded);
		assertEquals("Called method correct amount of times", 0, listener.calledRemoved);
		assertEquals("Called method correct amount of times", 1, listener.calledChanged);
		
		assertThat("List contains correct resources", listener.calledChangedDvlmResources, hasItem(seiFile));
		
		// Now add an arbitrary file, which is also part of the resourceSet this should also notify the added resources
		executeAsCommand(() -> pckge.setName("TestPackage"));
		editingDomain.saveResourceIgnorePermissions(randomResource);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Called method correct amount of times", 2, listener.calledPrecondition);
		assertEquals("Called method correct amount of times", 2, listener.calledPostcondition);
		assertEquals("Called method correct amount of times", 0, listener.calledAdded);
		assertEquals("Called method correct amount of times", 0, listener.calledRemoved);
		assertEquals("Called method correct amount of times", 2, listener.calledChanged);
		
		assertThat("List contains correct resources", listener.calledChangedDvlmResources, hasItem(randomFile));
		
		// And now add a random file which is not in the ResourceSet. No Added file should be notified
		listener.calledResourceChanged = 0;
	
		Path randomNonRsPath = new File(randomNonRsFile.getRawLocation().toOSString()).toPath();
		String content = "Changed some content";
		Files.write(randomNonRsPath, content.getBytes(StandardCharsets.UTF_8));
		testProject.refreshLocal(IResource.DEPTH_INFINITE, null);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		assertEquals("Listener got called again", 1, listener.calledResourceChanged);
		
		assertEquals("Called method correct amount of times", 2, listener.calledPrecondition);
		assertEquals("Called method correct amount of times", 2, listener.calledPostcondition);
		assertEquals("Called method correct amount of times", 0, listener.calledAdded);
		assertEquals("Called method correct amount of times", 0, listener.calledRemoved);
		assertEquals("Called method correct amount of times", 2, listener.calledChanged);
	}

}
