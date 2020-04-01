/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.cad.command;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;

/**
 * Test class for the copy resource command
 *
 */
public class CopyResourceCommandTest {
	
	private static final String TEST_FILE_NAME = "dummy.test";
	private List<String> testContent;
	private Path testFolderPath;
	private Path testFile;
	
	@Before
	public void setUp() throws CoreException, IOException {
		testFolderPath = VirSatFileUtils.createAutoDeleteTempDirectory("cadTest");
		
		testFile = Paths.get(testFolderPath.toString(), TEST_FILE_NAME);
		testContent = Arrays.asList("solid test", "endsolid test");
		Files.write(testFile, testContent);
	}
	
	
	@Test
	public void testCanExecute() throws IOException {

		Path nonExistingFile = Paths.get(TEST_FILE_NAME, "randomFile.bla");
		
		CopyResourceCommand commandInvalid1 = new CopyResourceCommand(nonExistingFile, testFolderPath);
		assertFalse("Cannot copy folder", commandInvalid1.canExecute());

		CopyResourceCommand commandInvalid2 = new CopyResourceCommand(null, testFolderPath);
		assertFalse("Cannot copy null path", commandInvalid2.canExecute());
		
		final String TEST_TARGET_FILE_NAME = "target.test";
		Path testTarget = Paths.get(testFolderPath.toString(), TEST_TARGET_FILE_NAME);
		CopyResourceCommand commandValid = new CopyResourceCommand(testFile, testTarget);
		assertTrue(commandValid.canExecute());
	}
	
	
	@Test
	public void testExecute() throws IOException {
		
		final String TEST_TARGET_FILE_NAME = "target.test";
		Path testTarget = Paths.get(testFolderPath.toString(), TEST_TARGET_FILE_NAME);
		
		CopyResourceCommand command = new CopyResourceCommand(testFile, testTarget, StandardCopyOption.REPLACE_EXISTING);
		command.execute();
		
		assertTrue("File copied? ", testTarget.toFile().exists());
		assertArrayEquals("File is copied correctly", Files.readAllBytes(testFile),
				Files.readAllBytes(testTarget));
		
	}
	
	@Test
	public void testExecuteWithInvalidFile() throws IOException {
		
		final String TEST_TARGET_FILE_NAME = "target.test";
		Path testTarget = Paths.get(testFolderPath.toString(), TEST_TARGET_FILE_NAME);
		
		CopyResourceCommand command = new CopyResourceCommand(null, testTarget, StandardCopyOption.REPLACE_EXISTING);
		command.execute();
		
		assertFalse("File copied? ", testTarget.toFile().exists());
		
	}
	
	@Test
	public void testUndoWithInvalidFile() throws IOException {
		
		final String TEST_TARGET_FILE_NAME = "target.test";
		Path testTarget = Paths.get(testFolderPath.toString(), TEST_TARGET_FILE_NAME);
		
		CopyResourceCommand command = new CopyResourceCommand(testFile, testTarget, StandardCopyOption.REPLACE_EXISTING);
		command.execute();
		
		Files.delete(testTarget);
		command.undo(); // Just check for unhandled exceptions
		
	}
	
	@Test
	public void testUndoRedo() throws IOException {
		
		final String TEST_TARGET_FILE_NAME = "target.test";
		Path testTarget = Paths.get(testFolderPath.toString(), TEST_TARGET_FILE_NAME);
		
		CopyResourceCommand command = new CopyResourceCommand(testFile, testTarget);
		command.execute();
		
		assertTrue("File copied? ", testTarget.toFile().exists());
		
		command.undo();
		
		assertFalse("File copy undone ", testTarget.toFile().exists());
		
		command.redo();
		
		assertTrue("File copied again? ", testTarget.toFile().exists());
		
	}

}
