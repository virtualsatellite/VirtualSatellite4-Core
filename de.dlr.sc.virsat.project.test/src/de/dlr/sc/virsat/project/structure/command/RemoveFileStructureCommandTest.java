/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

public class RemoveFileStructureCommandTest extends AProjectTestCase {

	StructuralElementInstance testSei;
	IFolder testSeiFolder;
	
	public static class TestDeleteOperation extends AbstractOperation {

		public TestDeleteOperation() {
			super("Delete Operation");
		}

		@Override
		public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
			fail("Should not have been called");
			return Status.OK_STATUS;
		}

		@Override
		public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
			fail("Should not have been called");
			return Status.OK_STATUS;
		}

		@Override
		public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
			fail("Should not have been called");
			return Status.OK_STATUS;
		}
		
		@Override
		public boolean canExecute() {
			return false;
		}
		
		@Override
		public boolean canRedo() {
			return false;
		}
		
		@Override
		public boolean canUndo() {
			return false;
		}
	}
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		
		addEditingDomainAndRepository();

		executeAsCommand(() -> {
			// Now create a SEI for testing and embed it into the TransactionalEditingDomain 
			testSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
			projectCommons.createFolderStructure(testSei, null);
			testSeiFolder = projectCommons.getStructuralElemntInstanceFolder(testSei);
			Resource resource = rs.getStructuralElementInstanceResource(testSei);
			resource.getContents().add(testSei);
		});
	}

	@Test
	public void testRemoveFileStructureCommand() {
		AtomicBoolean gotCalled = new AtomicBoolean(false);
		
		// Call the constructor and check if it works as expected
		new RemoveFileStructureCommand(testProject, testSei, (iFolder) -> {
			
			assertEquals("Called with the correct folder", testSeiFolder, iFolder);
			
			gotCalled.set(true);
			return new TestDeleteOperation();
		});
		
		assertTrue("The call back function got called as expected", gotCalled.get());
	}
	
	@Test
	public void testCanExecute() {
		Command removeCommandFail = new RemoveFileStructureCommand(testProject, testSei, (iFolder) -> new TestDeleteOperation());
		assertFalse("Cannot yet execute the command", removeCommandFail.canExecute());

		Command removeCommandAccept = new RemoveFileStructureCommand(testProject, testSei, (iFolder) -> new TestDeleteOperation() {
			@Override
			public boolean canExecute() {
				return true;
			}
		});
		assertTrue("Cannot yet execute the command", removeCommandAccept.canExecute());
	}

	@Test
	public void testCanUndo() {
		Command removeCommandFail = new RemoveFileStructureCommand(testProject, testSei, (iFolder) -> new TestDeleteOperation());
		assertFalse("Cannot yet execute the command", removeCommandFail.canUndo());

		Command removeCommandAccept = new RemoveFileStructureCommand(testProject, testSei, (iFolder) -> new TestDeleteOperation() {
			@Override
			public boolean canUndo() {
				return true;
			}
		});
		assertTrue("Cannot yet execute the command", removeCommandAccept.canUndo());
	}

	@Test
	public void testUndo() {
		AtomicBoolean gotCalled = new AtomicBoolean(false);
		Command removeCommandAccept = new RemoveFileStructureCommand(testProject, testSei, (iFolder) -> new TestDeleteOperation() {
			@Override
			public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				gotCalled.set(true);
				return Status.OK_STATUS;
			}
		});
		removeCommandAccept.undo();
		assertTrue("Callback got correctly called", gotCalled.get());
	}

	@Test
	public void testExecute() {
		AtomicBoolean gotCalled = new AtomicBoolean(false);
		Command removeCommandAccept = new RemoveFileStructureCommand(testProject, testSei, (iFolder) -> new TestDeleteOperation() {
			@Override
			public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				gotCalled.set(true);
				return Status.OK_STATUS;
			}
		});
		removeCommandAccept.execute();
		assertTrue("Callback got correctly called", gotCalled.get());
	}

	@Test
	public void testRedo() {
		AtomicBoolean gotCalled = new AtomicBoolean(false);
		Command removeCommandAccept = new RemoveFileStructureCommand(testProject, testSei, (iFolder) -> new TestDeleteOperation() {
			@Override
			public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				gotCalled.set(true);
				return Status.OK_STATUS;
			}
		});
		removeCommandAccept.redo();
		assertTrue("Callback got correctly called", gotCalled.get());
	}
}
