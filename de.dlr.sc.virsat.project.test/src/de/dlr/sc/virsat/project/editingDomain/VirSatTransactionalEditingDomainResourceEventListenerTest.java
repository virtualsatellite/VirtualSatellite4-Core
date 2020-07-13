/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.Before;


import org.junit.Test;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain.IResourceEventListener;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * This class is intended to test the ResourceListener as implemented in the editing domain
 * and the according notifications.
 */
public class VirSatTransactionalEditingDomainResourceEventListenerTest extends AProjectTestCase {

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		listener = new TestResourceEventListener();
		interlockedListener = new InterlockedExecutionListener();
		VirSatTransactionalEditingDomain.addResourceEventListener(interlockedListener);
	}

	@Override
	protected void addEditingDomainAndRepository() {
		addEditingDomainAndRepository(true);
	}
	
	@After
	@Override
	public void tearDown() throws CoreException {
		// Always remove the listener again
		VirSatTransactionalEditingDomain.removeResourceEventListener(listener);
		VirSatTransactionalEditingDomain.removeResourceEventListener(interlockedListener);
		super.tearDown();
	}
	
	InterlockedExecutionListener interlockedListener;
	TestResourceEventListener listener;
	
	/**
	 * A listener to check if the WorkspaceResourceChangeListener of the TransactionalEditingDomain
	 * is issuing the correct notifications as expected.
	 */
	static class TestResourceEventListener implements IResourceEventListener {
		
		private int calledResourceEventCount = 0;
		private int previousResourceEventType = 0;
		private int calledResourceEventType = 0;
		private Set<Resource> calledResourceEventResources;
		private Set<Resource> previousResourceEventResources;
		
		public synchronized int getCalledResourceEventCount() {
			return calledResourceEventCount;
		}

		public synchronized int getPreviousResourceEventType() {
			return previousResourceEventType;
		}

		public synchronized int getCalledResourceEventType() {
			return calledResourceEventType;
		}

		public synchronized Set<Resource> getCalledResourceEventResources() {
			return calledResourceEventResources;
		}

		public synchronized Set<Resource> getPreviousResourceEventResources() {
			return previousResourceEventResources;
		}

		@Override
		public synchronized void resourceEvent(Set<Resource> resources, int event) {
			// Remember the current and the previous resource event.
			calledResourceEventCount++;
			previousResourceEventType = calledResourceEventType;
			calledResourceEventType = event;
			previousResourceEventResources = calledResourceEventResources;
			calledResourceEventResources = new HashSet<>(resources);
			this.notifyAll();
		}
		
		public synchronized void waitForEventCount(int expectedEvent) throws InterruptedException {
			while (calledResourceEventCount < expectedEvent) {
				this.wait();
			}
		}
		
	};
	
	/**
	 * A listener to be able to execute code inter-locked with the notification of events.
	 * This is needed to ensure notifications are delivered, before the execution continues.
	 */
	static class InterlockedExecutionListener implements IResourceEventListener {

		int notificationCounter = 0;
		
		@Override
		public synchronized void resourceEvent(Set<Resource> resources, int event) {
			notificationCounter++;
			this.notifyAll();
		}
		
		/**
		 *  Execute some code inter-locked with the notifications. Takes a workspace lock first
		 *  before entering the synchronization to avoid dead-locks with the workspace synchronizer
		 *  job which maybe fires notifications.
		 * @param runnable the runnable holding the code to be executed.
		 * @param expectedNotifications amount of notifications that should be detected
		 * @throws InterruptedException
		 * @throws CoreException
		 */
		public void executeInterocked(Runnable runnable, int expectedNotifications) throws InterruptedException, CoreException {
			VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
			ResourcesPlugin.getWorkspace().run((monitor) -> {
				try {
					doExecuteInterlocked(runnable, expectedNotifications);
				} catch (InterruptedException e) {
					Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "VirSatTransactionalEditingDomainResourceEventListenerTest: Got interrupted while waiting ", e));
				}
			}, null);
			VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		}
		
		private synchronized void doExecuteInterlocked(Runnable runnable, int expectedNotifications) throws InterruptedException {
			runnable.run();
			while (notificationCounter < expectedNotifications) {
				this.wait();
			}
			notificationCounter = 0;
		}
	}
	
	@Test
	public void testHandleExternalyAddedResource() throws CoreException, InterruptedException {
	
		// --------------------------------------------
		// Add a new resource externally
		// Expected result is a reload on all resources
		interlockedListener.executeInterocked(() -> editingDomain.saveAll(), 1);
		VirSatTransactionalEditingDomain.addResourceEventListener(listener);
		
		// Create a new resource for a SEI in the resourceSet this should issue a notification on the added resources
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei);
		Resource seiResource = editingDomain.getResourceSet().safeGetResource(seiFile, true);
		listener.waitForEventCount(1);
		assertNotNull("Got a resource for the SEI", seiResource);
		
		assertEquals("Called listener correct amount of times", 1, listener.getCalledResourceEventCount());
		assertEquals("Called listener with correct type", VirSatTransactionalEditingDomain.EVENT_RELOAD, listener.getCalledResourceEventType());
		
		seiResource = editingDomain.getResourceSet().getStructuralElementInstanceResource(sei);
		Resource rmResource = editingDomain.getResourceSet().getRoleManagementResource();
		Resource umResource = editingDomain.getResourceSet().getUnitManagementResource();
		Resource repoResource = editingDomain.getResourceSet().getRepositoryResource();
		
		assertThat("List contains correct resources", listener.getCalledResourceEventResources(), containsInAnyOrder(seiResource, rmResource, umResource, repoResource));
	}

	@Test
	public void testHandleExternalAndInternalModelChanges() throws IOException, CoreException, InterruptedException {
		
		// -------------------------------------------------------------
		// Here we create a SEI, and we issue a name change
		// This should only trigger a notification on the SEI's resource
		interlockedListener.executeInterocked(() -> editingDomain.saveAll(), 1);
		
		// Create a new resource for a SEI in the resourceSet this should issue a notification on the added resources
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei);
		Resource seiResource = editingDomain.getResourceSet().safeGetResource(seiFile, true);
		executeAsCommand(() -> seiResource.getContents().add(sei));
		interlockedListener.executeInterocked(() -> editingDomain.saveAll(), 1);
		VirSatTransactionalEditingDomain.addResourceEventListener(listener);

		assertEquals("SEI is still the same as in the potentially relaoded reosurce", sei, seiResource.getContents().get(0));
		
		// Now change the name which expects as change on the resource containing the SEI
		executeAsCommand(() -> sei.setName("NameNumberOne"));
		listener.waitForEventCount(1);
		
		assertEquals("Called listener correct amount of times", 1, listener.getCalledResourceEventCount());
		assertEquals("Called listener with correct type", VirSatTransactionalEditingDomain.EVENT_CHANGED, listener.getCalledResourceEventType());
		assertThat("List contains correct resources", listener.getCalledResourceEventResources(), containsInAnyOrder(seiResource));
		
		// Now saving the resource which triggers a change notification again.
		editingDomain.saveAll();
		listener.waitForEventCount(2);
		assertEquals("Called listener correct amount of times", 2, listener.getCalledResourceEventCount());
		assertEquals("Called listener with correct type", VirSatTransactionalEditingDomain.EVENT_CHANGED, listener.getCalledResourceEventType());
		assertThat("List contains correct resources", listener.getCalledResourceEventResources(), containsInAnyOrder(seiResource));
		
		
		// ----------------------------------------------
		// Now we are changing the name externally
		// This should trigger a full reload of the model
	
		// Wait some time for executing test cases on linux and tycho. Otherwise file system changes, won't be detected
		waitSomeTime();
		testProject.isSynchronized(IResource.DEPTH_INFINITE);
		Path seiFilePath = new File(seiFile.getRawLocation().toOSString()).toPath();
		String content = Files.readAllLines(seiFilePath, StandardCharsets.UTF_8).toString();
		content = content.replace("NameNumberOne", "NameNumberTwo");
		Files.write(seiFilePath, content.getBytes(StandardCharsets.UTF_8));
		testProject.refreshLocal(IResource.DEPTH_INFINITE, null);
		
		//CHECKSTYLE:OFF
		listener.waitForEventCount(3);
		assertEquals("Called listener correct amount of times", 3, listener.getCalledResourceEventCount());
		assertEquals("Called listener with correct type", VirSatTransactionalEditingDomain.EVENT_RELOAD, listener.getCalledResourceEventType());
		Resource seiResourceReload = editingDomain.getResourceSet().getStructuralElementInstanceResource(sei);
		//CHECKSTYLE:ON
		
		Resource rmResourceReload = editingDomain.getResourceSet().getRoleManagementResource();
		Resource umResourceReload = editingDomain.getResourceSet().getUnitManagementResource();
		Resource repoResourceReload = editingDomain.getResourceSet().getRepositoryResource();
		
		assertThat("List contains correct resources", listener.getCalledResourceEventResources(), containsInAnyOrder(seiResourceReload, rmResourceReload, umResourceReload, repoResourceReload));
	}
	
	@Test
	public void testHandleExternalyRemovedResource() throws CoreException, InterruptedException {
	
		// --------------------------------------------
		// Add a new resource externally
		// Expected result is a reload on all resources
		
		interlockedListener.executeInterocked(() -> editingDomain.saveAll(), 1);
		
		// Create a new resource for a SEI in the resourceSet this should issue a notification on the added resources
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei);
		Resource seiResource = editingDomain.getResourceSet().safeGetResource(seiFile, true);
		interlockedListener.executeInterocked(() -> editingDomain.saveAll(), 1);
		VirSatTransactionalEditingDomain.addResourceEventListener(listener);
		
		// Now delete the resource externally and check that a full reload is triggered
		seiFile.delete(true, null);
		listener.waitForEventCount(2);
		
		assertEquals("Called listener correct amount of times", 2, listener.getCalledResourceEventCount());
		
		// Received two events. First the unload, than the reload
		assertEquals("Called listener frist with correct type", VirSatTransactionalEditingDomain.EVENT_UNLOAD, listener.getPreviousResourceEventType());
		assertThat("List contains correct resources", listener.getPreviousResourceEventResources(), containsInAnyOrder(seiResource));

		// Now verify the second event.
		assertEquals("Called listener with correct type", VirSatTransactionalEditingDomain.EVENT_RELOAD, listener.getCalledResourceEventType());
		
		Resource rmResource = editingDomain.getResourceSet().getRoleManagementResource();
		Resource umResource = editingDomain.getResourceSet().getUnitManagementResource();
		Resource repoResource = editingDomain.getResourceSet().getRepositoryResource();
		
		assertThat("List contains correct resources", listener.getCalledResourceEventResources(), containsInAnyOrder(rmResource, umResource, repoResource));
		assertThat("List contains correct resources", listener.getCalledResourceEventResources(), not(hasItem(seiResource)));
	}
	
	@Test
	public void testHandleInternallyRemovedResource() throws CoreException, InterruptedException {
	
		// --------------------------------------------
		// Add a new resource internally
		// Expected result is a notification about 
		// unloading the resource
		
		interlockedListener.executeInterocked(() -> editingDomain.saveAll(), 1);
		
		waitSomeTime();
		// Create a new resource for a SEI in the resourceSet this should issue a notification on the added resources
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei);
		Resource seiResource = editingDomain.getResourceSet().safeGetResource(seiFile, true);
		interlockedListener.executeInterocked(() -> editingDomain.saveAll(), 1);
		VirSatTransactionalEditingDomain.addResourceEventListener(listener);
		
		// Now delete the resource internally and check that a full reload is triggered
		editingDomain.removeResource(seiResource);
		listener.waitForEventCount(1);
		
		assertEquals("Called listener correct amount of times", 1, listener.getCalledResourceEventCount());
		
		// Received two events. First the unload, than the reload
		assertEquals("Called listener frist with correct type", VirSatTransactionalEditingDomain.EVENT_UNLOAD, listener.getCalledResourceEventType());
		assertThat("List contains correct resources", listener.getCalledResourceEventResources(), containsInAnyOrder(seiResource));
	}
}
