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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.command.CutToClipboardCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.command.DeleteStructuralElementInstanceCommand;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatCopyToClipboardCommand;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatCutToClipboardCommand;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatPasteFromClipboardCommand;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.resources.VirSatResourceSetUtil;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * This class tests the Transactional Editing Domain for Virtual Satellite
 * 
 * @author fisc_ph
 *
 */
public class VirSatTransactionalEditingDomainTest extends AProjectTestCase {

	private VirSatResourceSet rs;
	private VirSatTransactionalEditingDomain rsEd;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		VirSatResourceSet.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();
		VirSatTransactionalEditingDomain.clearResourceEventListener();

		rs = VirSatResourceSet.getResourceSet(testProject, false);
		rsEd = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
		
		UserRegistry.getInstance().setSuperUser(true);
		
		Command cmd = rs.initializeModelsAndResourceSet(null, rsEd);
		rsEd.getCommandStack().execute(cmd);
		rsEd.saveAll();
		
		ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
	}

	@After
	public void tearDown() throws CoreException {
		super.tearDown();
		
		VirSatResourceSet.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();
		VirSatTransactionalEditingDomain.clearResourceEventListener();
		UserRegistry.getInstance().setSuperUser(false);
	}

	/**
	 * Test class that can be added as a resource listener to the editing domain
	 * @author fisc_ph
	 *
	 */
	private class ResourceEventCounter implements VirSatTransactionalEditingDomain.IResourceEventListener {
		protected Set<Resource> triggeredResources = new HashSet<>();
		protected Resource firstResource;
		protected int triggeredEvent;
		protected int counter = 0;

		protected List<List<StackTraceElement>> stackTraces = new ArrayList<>();
		
		@Override
		public void resourceEvent(Set<Resource> resources, int event) {
			triggeredResources.addAll(resources);
			triggeredEvent = event;
			counter++;
			firstResource = resources.iterator().next();
			
			List<StackTraceElement> stackTrace = Arrays.asList(Thread.currentThread().getStackTrace());
			stackTraces.add(stackTrace);
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
		
		rsEd.saveAll();
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		rsEd.getCommandStack().execute(cmd);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Event has been fired as often as expected", 1, eventCounter.counter);
		
		rsEd.saveAll();
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Event has been fired as often as expected", 2, eventCounter.counter);
		
		Discipline discipline2 = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd2 = AddCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline2);
		rsEd.getCommandStack().execute(cmd2);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		rsEd.saveAll(false, false);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		final int EXPECTED_4_CHANGES = 4;
		assertEquals("Event has been fired as often as expected", EXPECTED_4_CHANGES, eventCounter.counter);
	}
	
	@Test
	public void testSaveResource() {
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		rsEd.saveResource(rmResource);

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
		
		rsEd.saveResource(rmResource);
		assertEquals("Event has been fired as often as expected", 0, eventCounter.counter);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		rsEd.getCommandStack().execute(cmd);
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Event has been fired as often as expected", 1, eventCounter.counter);
		
		rsEd.saveResource(rmResource);
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
	
		rsEd.saveResource(rmResource);
		
		RecordingCommand recCmd = new RecordingCommand(rsEd) {
			@Override
			public void doExecute() {
				rmResource.getContents().clear();
				repoResource.getContents().clear();
				rmResource.getContents().add(ca);
				repoResource.getContents().add(rpi);
			}
		};
		rsEd.getCommandStack().execute(recCmd);

		// Save the RPI but not the CA
		rsEd.saveResource(repoResource);
		
		// Now start reloading, which will cause the RPI in the RepoResource to have a dangling proxy 
		// to the non saved CA in the other resource.
		VirSatResourceSet.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();
		rs = null;
		rsEd = null;
		rs = VirSatResourceSet.getResourceSet(testProject, false);
		rsEd = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
		
		Resource repoResource2 = rs.getRepositoryResource();
		ReferencePropertyInstance danglingRpi = (ReferencePropertyInstance) repoResource2.getContents().get(0);
		
		// This call will actually create the Error
		danglingRpi.getReference();
		
		assertEquals("We have errors", 1, repoResource2.getErrors().size());
		
		// now save it and tell to remove the dangling references
		rsEd.saveResource(repoResource2);
		
		// Now load it a third time and make sure there is no dangling reference
		VirSatResourceSet.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();
		rs = null;
		rsEd = null;
		rs = VirSatResourceSet.getResourceSet(testProject, false);
		rsEd = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
		
		repoResource2 = rs.getRepositoryResource();
		danglingRpi = (ReferencePropertyInstance) repoResource2.getContents().get(0);
		
		assertTrue("No errors anymore", repoResource2.getErrors().isEmpty());
	}
	
	@Test
	public void testSaveResourceNoWriteAccess() {
		// Create empty role management
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		rsEd.saveResource(rmResource);
		
		// Add a discpline
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		rsEd.getCommandStack().execute(cmd);
		
		UserRegistry.getInstance().setSuperUser(false);
		rsEd.saveResource(rmResource);
		
		boolean isChanged = VirSatResourceSetUtil.isChanged(rmResource, Collections.EMPTY_MAP, Collections.EMPTY_MAP);
		assertTrue("Resource is still changed since we have no write permission", isChanged);
	}

	
	@Test
	public void testIsDirtyResource() {
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		
		rsEd.saveResource(rmResource);
		assertFalse("The resource is sotred, therefore it is not dirty", rsEd.isDirty(rmResource));

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
		Command cmd = AddCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		rsEd.getCommandStack().execute(cmd);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertThat("The roleManagement has been changed", rm.getDisciplines(), hasItem(discipline));  
		assertEquals("The Listener should ahve been triggered by now", 1, eventCounter.counter);
		assertTrue("The resource is changed, therefore it is dirty", rsEd.isDirty(rmResource));
		
		rsEd.saveResource(rmResource);
		assertFalse("The resource is sotred, therefore it is not dirty", rsEd.isDirty(rmResource));
	}
	
	@Test
	public void testIsDirty() {
		Resource rmResource = rs.getRoleManagementResource();
		
		RoleManagement rm = rs.getRoleManagement();
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		rsEd.getCommandStack().execute(cmd);
		
		assertThat("The roleManagement has been changed", rm.getDisciplines(), hasItem(discipline));  
		assertTrue("One of the resources is dirty, therefore the editing domain is dirty", rsEd.isDirty());
		rsEd.saveResource(rmResource);
		assertFalse("All resources are saved, therefore the editing domain is not dirty", rsEd.isDirty());
	}

	@Test
	public void testAddResourceEventListener() {
		ResourceEventCounter eventCounter1 = new ResourceEventCounter();
		ResourceEventCounter eventCounter2 = new ResourceEventCounter();

		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		rsEd.saveResource(rmResource);

		VirSatTransactionalEditingDomain.addResourceEventListener(eventCounter1);
		assertEquals("Listener is just added", 0, eventCounter1.counter);
		assertEquals("Listener is just added", 0, eventCounter2.counter);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		rsEd.getCommandStack().execute(cmd);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Listener is just added", 1, eventCounter1.counter);
		assertEquals("Listener is just added", 0, eventCounter2.counter);

		VirSatTransactionalEditingDomain.addResourceEventListener(eventCounter2);
		Discipline discipline2 = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd2 = AddCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline2);
		rsEd.getCommandStack().execute(cmd2);

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
		rsEd.saveResource(rmResource);

		VirSatTransactionalEditingDomain.addResourceEventListener(eventCounter1);
		VirSatTransactionalEditingDomain.addResourceEventListener(eventCounter2);
		assertEquals("Listener is just added", 0, eventCounter1.counter);
		assertEquals("Listener is just added", 0, eventCounter2.counter);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		rsEd.getCommandStack().execute(cmd);
		
		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Listener is just added", 1, eventCounter1.counter);
		assertEquals("Listener is just added", 1, eventCounter2.counter);

		VirSatTransactionalEditingDomain.removeResourceEventListener(eventCounter1);
		Discipline discipline2 = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd2 = AddCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline2);
		rsEd.getCommandStack().execute(cmd2);

		VirSatTransactionalEditingDomain.waitForFiringOfAccumulatedResourceChangeEvents();
		
		assertEquals("Listener is just added", 1, eventCounter1.counter);
		assertEquals("Listener is just added", 2, eventCounter2.counter);
	}

	@Test
	public void testGetResourceSet() {
		ResourceSet testRs = rsEd.getResourceSet();
		assertEquals("ResourceSet is correctly attached to Editing Domain", rs, testRs);
	}
	
	@Test
	public void testCreateCommand() {
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		rsEd.saveResource(rmResource);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		Command cmd = AddCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		rsEd.getCommandStack().execute(cmd);
		
		// First Check for the CutCommand
		Command command = CutToClipboardCommand.create(rsEd, rm.getDisciplines());
		assertTrue("Got correct VirSat Command", command instanceof VirSatCutToClipboardCommand);

		command = CopyToClipboardCommand.create(rsEd, rm.getDisciplines());
		assertTrue("Got correct VirSat Command", command instanceof VirSatCopyToClipboardCommand);
		
		rsEd.getCommandStack().execute(command);
		
		command = PasteFromClipboardCommand.create(rsEd, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES);
		assertTrue("Got correct VirSat Command", command instanceof VirSatPasteFromClipboardCommand);
		
		command = DeleteCommand.create(rsEd, rm.getDisciplines());
		assertTrue("Got correct VirSat Command", command instanceof DeleteStructuralElementInstanceCommand);
	}
}
