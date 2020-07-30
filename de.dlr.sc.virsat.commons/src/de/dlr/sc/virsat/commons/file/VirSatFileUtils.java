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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

/**
 * Extends the apache commons IO with capabilities to VirSat Specific file interaction.
 * E.g. creation of temproary directories
 *
 */
public class VirSatFileUtils extends FileUtils {

	public static final String TEMP_DIRECTORY_POST_FIX = "_";
	public static final String TEMP_DIRECTORY_PRE_FIX = "VirtualSatellite_";
	
	/**
	 * Method to create a directory in the system temporary folder. The created
	 * folder will be mark to be deleted when the JVM exits.
	 * @param pathName The name of the path to be generated
	 * @return the path of the newly generated temp folder
	 * @throws IOException
	 */
	public static synchronized Path createAutoDeleteTempDirectory(String pathName) throws IOException {
			// Add the TEMP_DIRECTORY_POST_FIX ("_") if needed
		pathName = (pathName.startsWith(TEMP_DIRECTORY_PRE_FIX)) ? pathName : TEMP_DIRECTORY_PRE_FIX + pathName;
		pathName = (pathName.endsWith(TEMP_DIRECTORY_POST_FIX)) ? pathName : pathName + TEMP_DIRECTORY_POST_FIX;
		
		// Create the temporary file and remember it for delete
		Path tempPath = Files.createTempDirectory(pathName);

		return tempPath;
	}
	
}
