/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.command.CutToClipboardCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.command.DeleteStructuralElementInstanceCommand;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatCopyToClipboardCommand;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatCutToClipboardCommand;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatPasteFromClipboardCommand;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.resources.VirSatResourceSetUtil;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * This class tests the Transactional Editing Domain for Virtual Satellite
 */
public class VirSatTransactionalEditingDomainTest extends AProjectTestCase {
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
	}

	/**
	 * Test class that can be added as a resource listener to the editing domain
	 */
	private class ResourceEventCounter implements VirSatTransactionalEditingDomain.IResourceEventListener {
		protected Set<Resource> triggeredResources = new HashSet<>();
		protected Resource firstResource;
		protected int triggeredEvent;
		protected int counter = 0;

		protected List<List<StackTraceElement>> stackTraces = new ArrayList<>();
		
		@Override
		public void resourceEvent(Set<Resource> resources, int event) {
			synchronized (this) {
				triggeredResources.addAll(resources);
				triggeredEvent = event;
				counter++;
				firstResource = resources.iterator().next();
				
				List<StackTraceElement> stackTrace = Arrays.asList(Thread.currentThread().getStackTrace());
				stackTraces.add(stackTrace);
				this.notify();
			}
		}
	}

	@Test
	public void testSaveAll() {
		Resource repoRes = rs.getRepositoryResource();
		Resource umRes = rs.getUnitManagementResource();
		Resource rmRes = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		
		ResourceEventCounter eventCounter = new ResourceEventCounter() {
			
			@Override
			public void resourceEvent(Set<Resource> resources, int event) {
				super.resourceEvent(resources, event);
				
				assertThat("Event has correctResourceInformation", firstResource, anyOf(equalTo(repoRes), equalTo(rmRes), equalTo(umRes)));
				assertEquals("Receiving correct Event Type", VirSatTransactionalEditingDomain.EVENT_CHANGED, event);
			}
		};
		
		VirSatTransactionalEditingDomain.addResourceEventListener(eventCounter);
		
		editingDomain.saveAll();
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(cmd);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Event has been fired as often as expected", 1, eventCounter.counter);
		
		editingDomain.saveAll();
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Event has been fired as often as expected", 2, eventCounter.counter);
		
		Discipline discipline2 = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd2 = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline2);
		editingDomain.getCommandStack().execute(cmd2);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		editingDomain.saveAll(false, false);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		final int EXPECTED_4_CHANGES = 4;
		assertEquals("Event has been fired as often as expected", EXPECTED_4_CHANGES, eventCounter.counter);
	}
	
	@Test
	public void testSaveResource() {
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		editingDomain.saveResource(rmResource);

		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		ResourceEventCounter eventCounter = new ResourceEventCounter() {
			@Override
			public void resourceEvent(Set<Resource> resources, int event) {
				super.resourceEvent(resources, event);
				assertEquals("Got a trigger for the correct resource", rmResource, firstResource);
				assertEquals("Receiving correct Event Type", VirSatTransactionalEditingDomain.EVENT_CHANGED, event);
			}
		};
		
		VirSatTransactionalEditingDomain.addResourceEventListener(eventCounter);
		
		editingDomain.saveResource(rmResource);
		assertEquals("Event has been fired as often as expected", 0, eventCounter.counter);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(cmd);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Event has been fired as often as expected", 1, eventCounter.counter);
		
		editingDomain.saveResource(rmResource);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Event has been fired as often as expected", 2, eventCounter.counter);
	}
	
	@Test
	public void testSaveResourceRemoveDanglingReference() {
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ReferencePropertyInstance rpi = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		rpi.setReference(ca);
		
		Resource rmResource = rs.getRoleManagementResource();
		Resource repoResource = rs.getRepositoryResource();
	
		editingDomain.saveResource(rmResource);
		
		RecordingCommand recCmd = new RecordingCommand(editingDomain) {
			@Override
			public void doExecute() {
				rmResource.getContents().clear();
				repoResource.getContents().clear();
				rmResource.getContents().add(ca);
				repoResource.getContents().add(rpi);
			}
		};
		editingDomain.getCommandStack().execute(recCmd);

		// Save the RPI but not the CA
		editingDomain.saveResource(repoResource);
		
		// Now start reloading, which will cause the RPI in the RepoResource to have a dangling proxy 
		// to the non saved CA in the other resource.
		VirSatResourceSet.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();
		
		rs = VirSatResourceSet.getResourceSet(testProject, false);
		editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
		
		Resource repoResource2 = rs.getRepositoryResource();
		ReferencePropertyInstance danglingRpi = (ReferencePropertyInstance) repoResource2.getContents().get(0);
		
		// This call will actually create the Error
		danglingRpi.getReference();
		
		assertEquals("We have errors", 1, repoResource2.getErrors().size());
		
		// now save it and tell to remove the dangling references
		editingDomain.saveResource(repoResource2);
		
		// Now load it a third time and make sure there is no dangling reference
		VirSatResourceSet.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();
		
		rs = VirSatResourceSet.getResourceSet(testProject, false);
		editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
		
		repoResource2 = rs.getRepositoryResource();
		
		// Trigger EMF to try to resolve the reference 
		repoResource2.getContents().get(0);
		
		assertTrue("No errors anymore", repoResource2.getErrors().isEmpty());
	}
	
	@Test
	public void testSaveResourceNoWriteAccess() {
		// Create empty role management
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		editingDomain.saveResource(rmResource);
		
		// Add a discpline
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(cmd);
		
		UserRegistry.getInstance().setSuperUser(false);
		editingDomain.saveResource(rmResource);
		
		boolean isChanged = VirSatResourceSetUtil.isChanged(rmResource, Collections.EMPTY_MAP, Collections.EMPTY_MAP);
		assertTrue("Resource is still changed since we have no write permission", isChanged);
	}

	
	@Test
	public void testIsDirtyResource() {
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		
		editingDomain.saveResource(rmResource);
		assertFalse("The resource is sotred, therefore it is not dirty", editingDomain.isDirty(rmResource));

		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		// Now attach a listener to track that the command on the command stack is firing a
		// resource Event as well. By this we can receive information that the resource is now in
		// a Dirty/Stale state.
		ResourceEventCounter eventCounter = new ResourceEventCounter() {
			@Override
			public void resourceEvent(Set<Resource> resources, int event) {
				super.resourceEvent(resources, event);
				assertEquals("Got a trigger for the correct resource", rmResource, firstResource);
				assertEquals("Receiving correct Event Type", VirSatTransactionalEditingDomain.EVENT_CHANGED, event);
			}
		};
		VirSatTransactionalEditingDomain.addResourceEventListener(eventCounter);
		assertEquals("Listener is just added", 0, eventCounter.counter);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(cmd);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertThat("The roleManagement has been changed", rm.getDisciplines(), hasItem(discipline));  
		assertEquals("The Listener should ahve been triggered by now", 1, eventCounter.counter);
		assertTrue("The resource is changed, therefore it is dirty", editingDomain.isDirty(rmResource));
		
		editingDomain.saveResource(rmResource);
		assertFalse("The resource is sotred, therefore it is not dirty", editingDomain.isDirty(rmResource));
	}
	
	@Test
	public void testIsDirty() {
		Resource rmResource = rs.getRoleManagementResource();
		
		RoleManagement rm = rs.getRoleManagement();
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(cmd);
		
		assertThat("The roleManagement has been changed", rm.getDisciplines(), hasItem(discipline));  
		assertTrue("One of the resources is dirty, therefore the editing domain is dirty", editingDomain.isDirty());
		editingDomain.saveResource(rmResource);
		assertFalse("All resources are saved, therefore the editing domain is not dirty", editingDomain.isDirty());
	}

	@Test
	public void testAddResourceEventListener() {
		ResourceEventCounter eventCounter1 = new ResourceEventCounter();
		ResourceEventCounter eventCounter2 = new ResourceEventCounter();

		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		editingDomain.saveResource(rmResource);

		VirSatTransactionalEditingDomain.addResourceEventListener(eventCounter1);
		assertEquals("Listener is just added", 0, eventCounter1.counter);
		assertEquals("Listener is just added", 0, eventCounter2.counter);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(cmd);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Listener is just added", 1, eventCounter1.counter);
		assertEquals("Listener is just added", 0, eventCounter2.counter);

		VirSatTransactionalEditingDomain.addResourceEventListener(eventCounter2);
		Discipline discipline2 = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd2 = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline2);
		editingDomain.getCommandStack().execute(cmd2);

		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Listener is just added", 2, eventCounter1.counter);
		assertEquals("Listener is just added", 1, eventCounter2.counter);
	}

	@Test
	public void testRemoveResourceEventListener() {
		ResourceEventCounter eventCounter1 = new ResourceEventCounter();
		ResourceEventCounter eventCounter2 = new ResourceEventCounter();

		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		editingDomain.saveResource(rmResource);

		VirSatTransactionalEditingDomain.addResourceEventListener(eventCounter1);
		VirSatTransactionalEditingDomain.addResourceEventListener(eventCounter2);
		assertEquals("Listener is just added", 0, eventCounter1.counter);
		assertEquals("Listener is just added", 0, eventCounter2.counter);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(cmd);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Listener is just added", 1, eventCounter1.counter);
		assertEquals("Listener is just added", 1, eventCounter2.counter);

		VirSatTransactionalEditingDomain.removeResourceEventListener(eventCounter1);
		Discipline discipline2 = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd2 = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline2);
		editingDomain.getCommandStack().execute(cmd2);

		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Listener is just added", 1, eventCounter1.counter);
		assertEquals("Listener is just added", 2, eventCounter2.counter);
	}

	@Test
	public void testGetResourceSet() {
		ResourceSet testRs = editingDomain.getResourceSet();
		assertEquals("ResourceSet is correctly attached to Editing Domain", rs, testRs);
	}
	
	@Test
	public void testCreateCommand() {
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		editingDomain.saveResource(rmResource);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(cmd);
		
		// First Check for the CutCommand
		Command command = CutToClipboardCommand.create(editingDomain, rm.getDisciplines());
		assertTrue("Got correct VirSat Command", command instanceof VirSatCutToClipboardCommand);

		command = CopyToClipboardCommand.create(editingDomain, rm.getDisciplines());
		assertTrue("Got correct VirSat Command", command instanceof VirSatCopyToClipboardCommand);
		
		editingDomain.getCommandStack().execute(command);
		
		command = PasteFromClipboardCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES);
		assertTrue("Got correct VirSat Command", command instanceof VirSatPasteFromClipboardCommand);
		
		command = DeleteCommand.create(editingDomain, rm.getDisciplines());
		assertTrue("Got correct VirSat Command", command instanceof DeleteStructuralElementInstanceCommand);
	}
	
	class ResourceEventTypeCounter extends ResourceEventCounter {
		
		protected Thread expectedThread = Thread.currentThread();
		
		protected boolean hasChangeEvents = false;
		protected boolean hasReloadEvents = false;
		
		@Override
		public void resourceEvent(Set<Resource> resources, int event) {
			synchronized (this) {
				// We had event changed messages in the past, which should not be fired.
				// try to make sure they are not fired when all resources are reloaded
				hasChangeEvents = (event == VirSatTransactionalEditingDomain.EVENT_CHANGED) ? true : hasChangeEvents;
				hasReloadEvents = (event == VirSatTransactionalEditingDomain.EVENT_RELOAD) ? true : hasReloadEvents;
				
				assertEquals("matching expected thread", expectedThread, Thread.currentThread());
				
				super.resourceEvent(resources, event);
			}
		}
	};
	
	@Test
	public void testReloadAll() {
		ResourceEventTypeCounter eventCheck = new ResourceEventTypeCounter();
		
		synchronized (eventCheck) {
			Resource rmResource = rs.getRoleManagementResource();
			RoleManagement rm = rs.getRoleManagement();
			editingDomain.saveResource(rmResource);
			
			Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
			Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
			editingDomain.getCommandStack().execute(cmd);
			editingDomain.saveAll();
		
			VirSatTransactionalEditingDomain.addResourceEventListener(eventCheck);
			
			editingDomain.reloadAll();
		
			assertFalse("There are no change events", eventCheck.hasChangeEvents);
			assertTrue("There are relaod events", eventCheck.hasReloadEvents);
			
			assertTrue("All resourceSet Resources triggered for relaod", eventCheck.triggeredResources.containsAll(rs.getResources()));
			
			VirSatResourceSet rsNew = editingDomain.getResourceSet();
			RoleManagement rmNew = rsNew.getRoleManagement();
			Resource rmResourceNew = rsNew.getRoleManagementResource();
			
			assertEquals("The resourceSet should not have changed", rs, rsNew);
			assertNotSame("Loaded a new RoleManagement", rm, rmNew);
			assertEquals("The Resource stays the same", rmResource, rmResourceNew);
			
			VirSatTransactionalEditingDomain.removeResourceEventListener(eventCheck);
		}
	}
	
	@Test
	public void testRunExclusiveWithResult() throws InterruptedException {
		Object expectedObject = new Object();
		
		Object result = editingDomain.runExclusive(new RunnableWithResult.Impl<Object>() {
			@Override
			public void run() {
				setResult(expectedObject);
			}
		});
	
		assertEquals("Got correct object", expectedObject, result);
		
		// Hashcode is just called do do something and to complete the lambda, it has no further meaning.
		Object resultNull = editingDomain.runExclusive(() -> expectedObject.hashCode());
		assertNull("Result is null", resultNull);
	}
	
	@Test
	public void testGetUserName() {
		// Usually the current SystemUser should be handed back
		String expectedUser = UserRegistry.getInstance().getUserName();
		assertEquals("Got the SystemUser", expectedUser, editingDomain.getUserName());
	
		// In case an override user context is set, the user from the context should be returned
		editingDomain.setUserContextOverride(new TestUserContext("ContextTestUser", false));
		
		assertEquals("Got user from context", "ContextTestUser", editingDomain.getUserName());
	}
	
	@Test
	public void testIsSuperUser() {
		// Usually the super user rights from the system user should be handed back
		UserRegistry.getInstance().setSuperUser(false);
		assertFalse("No super user rights from the System User", editingDomain.isSuperUser());
	
		UserRegistry.getInstance().setSuperUser(true);
		assertTrue("Now super user rights are provided by the SystemUser", editingDomain.isSuperUser());
		//
		// In case an override user context is set, the user from the context should be returned
		editingDomain.setUserContextOverride(new TestUserContext("ContextTestUser", false));
		
		assertFalse("The ovveride user context does not have super user rights", editingDomain.isSuperUser());
	}
	
	@Test
	public void testExecuteInWorkspaceIUserContext() {
		IUserContext overrideContext1 = new TestUserContext("ContextTestUser1", false);
		IUserContext overrideContext2 = new TestUserContext("ContextTestUser2", false);
		
		editingDomain.setUserContextOverride(overrideContext1);
		editingDomain.executeInWorkspace(() -> {
			assertEquals("Got inner user context", editingDomain.userContextOverride, overrideContext2);
		}, overrideContext2);
		
		assertEquals("Outer Context got correctly restored", overrideContext1, editingDomain.userContextOverride); 
	}
	
	@Test
	public void testExecuteInWorkspaceIUserContextException() {
		IUserContext overrideContext1 = new TestUserContext("ContextTestUser1", false);
		
		editingDomain.setUserContextOverride(overrideContext1);
		
		try {
			editingDomain.executeInWorkspace(() -> {
				throw new RuntimeException("TestException");
			});
			fail("Exception was thrown and should have been caught");
		} catch (Exception e) {
			assertThat("", e, instanceOf(RuntimeException.class));
			assertThat("Got correct error message", e.getMessage(), containsString("TestException"));
		}
		
		assertEquals("Outer Context got correctly restored", overrideContext1, editingDomain.userContextOverride); 
	}
	
	@Test
	public void testUpdateTriggerFullReload() {
	
		editingDomain.recentlyChangedResource.clear();
		assertTrue("There is no resource that has been saved recently", editingDomain.recentlyChangedResource.isEmpty());
		assertFalse("A full reload is not yet triggered", editingDomain.workspaceChangeListener.triggerFullReload);
		
		Resource repoRes = rs.getRepositoryResource();
		IFile repoWsRes = projectCommons.getRepositoryFile();
		editingDomain.recentlyChangedResource.add(repoRes.getURI());
		
		// First try the method on an arbitrary file in the file system nothing should happen
		IFile randomFile = testProject.getFile("RandomFile.file");
		editingDomain.workspaceChangeListener.updateTriggerFullReload(randomFile, true);
		assertFalse("A full reload is not yet triggered", editingDomain.workspaceChangeListener.triggerFullReload);
		assertThat("The list of Recently Saved Resources still contains the resources", editingDomain.recentlyChangedResource, hasItem(repoRes.getURI()));
		
		// Now call the method for calculating the fullReload.
		// In this case we check that a resource which is currently saved does not trigger the reload.
		// Additionally the resource is not deleted from the list of recently changed resources
		editingDomain.workspaceChangeListener.updateTriggerFullReload(repoWsRes, false);
		assertFalse("A full reload is not yet triggered", editingDomain.workspaceChangeListener.triggerFullReload);
		assertThat("The list of Recently Saved Resources still contains the resources", editingDomain.recentlyChangedResource, hasItem(repoRes.getURI()));
	
		// Now a similar test but the resource should be removed from the list of recently saved resources
		editingDomain.workspaceChangeListener.updateTriggerFullReload(repoWsRes, true);
		assertFalse("A full reload is not yet triggered", editingDomain.workspaceChangeListener.triggerFullReload);
		assertThat("The list of Recently Saved Resources does not contain the resources", editingDomain.recentlyChangedResource, not(hasItem(repoRes.getURI())));
	
		// now we call the method again. the resource is not in the list of recently saved resources
		// thus it has to be recognized as an external change and should trigger the full reload of the model.
		editingDomain.workspaceChangeListener.updateTriggerFullReload(repoWsRes, true);
		assertTrue("The full reload got triggered", editingDomain.workspaceChangeListener.triggerFullReload);
		assertThat("The list of Recently Saved Resources does not contain the resources", editingDomain.recentlyChangedResource, not(hasItem(repoRes.getURI())));
	}
	
	@Test
	public void testHandleAddedDvlmResources() {
		editingDomain.recentlyChangedResource.clear();
		assertTrue("There is no resource that has been saved recently", editingDomain.recentlyChangedResource.isEmpty());
		assertFalse("A full reload is not yet triggered", editingDomain.workspaceChangeListener.triggerFullReload);
		
		Resource repoRes = rs.getRepositoryResource();
		IFile repoWsRes = projectCommons.getRepositoryFile();
		editingDomain.recentlyChangedResource.add(repoRes.getURI());
		
		editingDomain.workspaceChangeListener.handleAddedDvlmResources(Collections.singletonList(repoWsRes));
		assertFalse("A full reload is not triggered because the resource was manually marked as recently changed", editingDomain.workspaceChangeListener.triggerFullReload);
		assertThat("The list of Recently Saved Resources still contains the resources", editingDomain.recentlyChangedResource, hasItem(repoRes.getURI()));
	
		editingDomain.recentlyChangedResource.clear();
		editingDomain.workspaceChangeListener.handleAddedDvlmResources(Collections.singletonList(repoWsRes));
		assertTrue("Now the resource was NOT marked as recently changed so we expect an external change and should trigger a reload", editingDomain.workspaceChangeListener.triggerFullReload);
		assertThat("The list should not have been changed by the clal to the handleAdded method", editingDomain.recentlyChangedResource, not(hasItem(repoRes.getURI())));
	}
	
	@Test
	public void testHandleChangedDvlmResources() {
		editingDomain.recentlyChangedResource.clear();
		assertTrue("There is no resource that has been saved recently", editingDomain.recentlyChangedResource.isEmpty());
		assertFalse("A full reload is not yet triggered", editingDomain.workspaceChangeListener.triggerFullReload);
		
		Resource repoRes = rs.getRepositoryResource();
		IFile repoWsRes = projectCommons.getRepositoryFile();
		editingDomain.recentlyChangedResource.add(repoRes.getURI());
		
		editingDomain.workspaceChangeListener.handleChangedDvlmResources(Collections.singletonList(repoWsRes));
		assertFalse("A full reload is not triggered because the resource was manually marked as recently changed", editingDomain.workspaceChangeListener.triggerFullReload);
		assertThat("Resource got removed from recently saved resources", editingDomain.recentlyChangedResource, not(hasItem(repoRes.getURI())));
	
		editingDomain.recentlyChangedResource.clear();
		editingDomain.workspaceChangeListener.handleChangedDvlmResources(Collections.singletonList(repoWsRes));
		assertTrue("Now the resource was NOT marked as recently changed so we expect an external change and should trigger a reload", editingDomain.workspaceChangeListener.triggerFullReload);
		assertThat("Resource got removed from recently saved resources", editingDomain.recentlyChangedResource, not(hasItem(repoRes.getURI())));
	}
	
	@Test
	public void testHandleChangedExternalResource() {
		editingDomain.recentlyChangedResource.clear();
		assertTrue("There is no resource that has been saved recently", editingDomain.recentlyChangedResource.isEmpty());
		assertFalse("A full reload is not yet triggered", editingDomain.workspaceChangeListener.triggerFullReload);
		
		Resource repoRes = rs.getRepositoryResource();
		IFile repoWsRes = projectCommons.getRepositoryFile();
		
		rs.removeResource(repoRes);
		editingDomain.workspaceChangeListener.handleChangedDvlmResources(Collections.singletonList(repoWsRes));
		assertFalse("Change was done on external resource that is not in resource set", editingDomain.workspaceChangeListener.triggerFullReload);
	}
	
	@Test
	public void testHandleRemovedDvlmResources() {
		editingDomain.recentlyChangedResource.clear();
		assertTrue("There is no resource that has been saved recently", editingDomain.recentlyChangedResource.isEmpty());
		assertFalse("A full reload is not yet triggered", editingDomain.workspaceChangeListener.triggerFullReload);
		
		Resource repoRes = rs.getRepositoryResource();
		IFile repoWsRes = projectCommons.getRepositoryFile();
		editingDomain.recentlyChangedResource.add(repoRes.getURI());
		
		editingDomain.workspaceChangeListener.handleRemovedDvlmResources(Collections.singletonList(repoWsRes));
		assertFalse("A full reload is not triggered because the resource was manually marked as recently changed", editingDomain.workspaceChangeListener.triggerFullReload);
		assertThat("Resource got removed from recently saved resources", editingDomain.recentlyChangedResource, not(hasItem(repoRes.getURI())));
		assertThat("ResourceSet does not contain resource anymore", rs.getResources(), not(hasItem(repoRes)));
		
		// Resource has been actually taken from the resourceSet. Thus it is now treated as an arbitrary file and nothing should happen.
		editingDomain.recentlyChangedResource.clear();
		editingDomain.workspaceChangeListener.handleRemovedDvlmResources(Collections.singletonList(repoWsRes));
		assertFalse("Resource is not in the resource set anymore so we don't need to reload", editingDomain.workspaceChangeListener.triggerFullReload);
		assertThat("Resource got removed from recently saved resources", editingDomain.recentlyChangedResource, not(hasItem(repoRes.getURI())));
	}
	
	@Test(expected = WrappedException.class)
	public void testGetResource() {
		
		IFile rmFile = projectCommons.getRoleManagementFile();
		
		assertNotNull("Resource is in VirSat Rsource set and is handed back", rs.getResource(rmFile, false));
		assertNotNull("Resource is in VirSat Rsource set and is handed back", rs.getResource(rmFile, true));
		
		// Remove the resource from the RS now not demandLoading it returns null
		rs.removeResource(rs.getResource(rmFile, false));
		assertNull("Resource should be in VirSat resource set", rs.getResource(rmFile, false));
		
		// Loading it on demand tries to read the file and hand back a resource
		assertNotNull("Resource should be in VirSat resource set", rs.getResource(rmFile, true));
		
		// Loading other than an iFile returns a null
		IFolder randomFolder = projectCommons.getStructuralElemntInstanceFolder(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		assertNull("Resource should be in VirSat resource set", rs.getResource(randomFolder, false));
		
		// In case the file does not exist, an exception is thrown
		IFile randomFile = projectCommons.getStructuralElementInstanceFile(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		rs.getResource(randomFile, true);
	}
	
}
