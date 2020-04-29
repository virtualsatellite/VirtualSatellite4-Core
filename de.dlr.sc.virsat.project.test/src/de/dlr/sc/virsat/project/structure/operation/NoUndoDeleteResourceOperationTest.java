/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure.operation;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.project.test.AProjectTestCase;

public class NoUndoDeleteResourceOperationTest extends AProjectTestCase {

	IFolder testFolder;
	IFolder testSubFolder; 
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		testFolder = testProject.getFolder("testFolder");
		testSubFolder = testFolder.getFolder("testSubFolder");
		
		testFolder.create(true, true, null);
		testSubFolder.create(true, true, null);
	}

	@Test
	public void testNoUndoDeleteResourceOperation() {
		AbstractOperation op = new NoUndoDeleteResourceOperation(testFolder, false);

		assertThat("Operation Label contains correct information", op.getLabel(), containsString(NoUndoDeleteResourceOperation.OPERATION_LABEL));
		assertThat("Operation Label contains correct resource description", op.getLabel(), containsString(testFolder.getRawLocation().toOSString()));
	}

	@Test
	public void testExecuteNoForce() throws ExecutionException {
		AbstractOperation op = new NoUndoDeleteResourceOperation(testFolder, false);
		
		IProgressMonitor testMonitor = new NullProgressMonitor() {
			@Override
			public void beginTask(String name, int totalWork) {
				assertThat("Task name contains correct description", name, containsString(NoUndoDeleteResourceOperation.OPERATION_MONITOR_LABEL));
				assertThat("Task name contains resource description", name, containsString(testFolder.getRawLocation().toOSString()));
			}
		};
		
		assertEquals("Got correct OK status", Status.OK_STATUS, op.execute(testMonitor, null));
		assertFalse("Folder does not exist anymore", testFolder.exists());
		assertFalse("SubFolder does not exist anymore", testSubFolder.exists());
	}
	
	@Test
	public void testExecuteNoForceFailOutOfSyncFile() throws ExecutionException, IOException {
		
		// Create an inconsistent state between the file system and workspace
		assertTrue("Adding a file unknown to the workspace", new File(testFolder.getRawLocation().toOSString(), "OutOfSyncFile").createNewFile());
		assertTrue("Removed folder form file system", new File(testSubFolder.getRawLocation().toOSString()).delete());
		assertTrue("Folder Still exists in workspace", testSubFolder.exists());
		
		AbstractOperation op = new NoUndoDeleteResourceOperation(testFolder, false);
		
		assertEquals("Got correct OK status", Status.ERROR, op.execute(null, null).getSeverity());
		assertTrue("Folder did not get deleted", testFolder.exists());
	}
	
	@Test
	public void testExecuteForceOutOfSyncFile() throws ExecutionException, IOException {
		
		// Create an inconsistent state between the file system and workspace
		assertTrue("Adding a file unknown to the workspace", new File(testFolder.getRawLocation().toOSString(), "OutOfSyncFile").createNewFile());
		assertTrue("Removed folder form file system", new File(testSubFolder.getRawLocation().toOSString()).delete());
		assertTrue("Folder Still exists in workspace", testSubFolder.exists());
		
		AbstractOperation op = new NoUndoDeleteResourceOperation(testFolder, true);
		
		assertEquals("Got correct OK status", Status.OK_STATUS, op.execute(null, null));
		assertFalse("Folder does not exist anymore", testFolder.exists());
		assertFalse("SubFolder does not exist anymore", testSubFolder.exists());
	}

	@Test
	public void testRedo() throws ExecutionException {
		AbstractOperation op = new NoUndoDeleteResourceOperation(testFolder, true);
		assertEquals("Got correct error status", IStatus.ERROR, op.redo(null, null).getSeverity());
	}

	@Test
	public void testUndo() throws ExecutionException {
		AbstractOperation op = new NoUndoDeleteResourceOperation(testFolder, true);
		assertEquals("Got correct error status", IStatus.ERROR, op.undo(null, null).getSeverity());
	}

	@Test
	public void testCanRedo() throws ExecutionException {
		AbstractOperation op = new NoUndoDeleteResourceOperation(testFolder, true);
		assertFalse("Operation does not provide redo", op.canRedo());
	}

	@Test
	public void testCanUndo() throws ExecutionException {
		AbstractOperation op = new NoUndoDeleteResourceOperation(testFolder, true);
		assertFalse("Operation does not provide undo", op.canUndo());
	}
}
