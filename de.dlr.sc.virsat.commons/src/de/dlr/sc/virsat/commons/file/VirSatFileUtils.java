/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * Extends the apache commons IO with capabilities to VirSat Specific file interaction.
 * E.g. creation of temproary directories
 *
 */
public class VirSatFileUtils extends FileUtils {

	public static final String DELET_FILE_ON_EXIT_THREAD_NAME = "Delete temporary files on exit";
	public static final String TEMP_DIRECTORY_POST_FIX = "_";
	public static final String TEMP_DIRECTORY_PRE_FIX = "VirtualSatellite_";
	
	protected static class DelteFileOnExitThread extends Thread {
		protected DelteFileOnExitThread() {
			setName(DELET_FILE_ON_EXIT_THREAD_NAME);
		}
		
		@Override
		public void run() {
			File pathTempRoot = FileUtils.getTempDirectory();
			File[] subDirectoryFiles = pathTempRoot.listFiles(
					File::isDirectory
					);
			
			if (subDirectoryFiles != null) {
				List<File> subDirectories = Arrays.asList(subDirectoryFiles);
				
				subDirectories.stream()
						.filter((file) -> file.getName().startsWith(TEMP_DIRECTORY_PRE_FIX))
						.forEach((file) -> {
							try {
								FileUtils.deleteDirectory(file);
							} catch (IOException e) {
								System.err.println("VirSatFileUtils: Attention! Could not delete temporary directory (" + file.getAbsolutePath()
									+ ") because, " + e.getMessage() + " Don't worry, these directories"
									+ " are getting most likely deleted next time...");
							}
						}
					);
			}
		}
	}
	
	protected static DelteFileOnExitThread deleteFilesOnExitThread = null;
	
	/**
	 * Method to create a directory in the system temporary folder. The created
	 * folder will be mark to be deleted when the JVM exits.
	 * @param pathName The name of the path to be generated
	 * @return the path of the newly generated temp folder
	 * @throws IOException
	 */
	public static synchronized Path createAutoDeleteTempDirectory(String pathName) throws IOException {
		// Initialize the Delete thread on the JVM shutdown hook
		if (deleteFilesOnExitThread ==  null) {
			deleteFilesOnExitThread = new DelteFileOnExitThread();
			Runtime.getRuntime().addShutdownHook(deleteFilesOnExitThread);
		}
				
		// Add the TEMP_DIRECTORY_POST_FIX ("_") if needed
		pathName = (pathName.startsWith(TEMP_DIRECTORY_PRE_FIX)) ? pathName : TEMP_DIRECTORY_PRE_FIX + pathName;
		pathName = (pathName.endsWith(TEMP_DIRECTORY_POST_FIX)) ? pathName : pathName + TEMP_DIRECTORY_POST_FIX;
		
		// Create the temporary file and remember it for delete
		Path tempPath = Files.createTempDirectory(pathName);

		return tempPath;
	}
	
}
