/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons.external;

import java.io.File;
import java.io.IOException;
import de.dlr.sc.virsat.commons.Activator;

public class ProcessInteraction {
	private static final String DEFAULT_OUTPUT_FILE = "output.txt";
	private static final String DEFAULT_ERROR_FILE = "error.txt";
	
	/**
	 * 
	 * @param applicationpath path to the application
	 * @param command command to run 
	 * @return returns true if the process was successfully finished
	 */
	public boolean startCommandRunner(String applicationpath, String command) {

		ProcessBuilder pb = new ProcessBuilder(applicationpath + command);
		File out = new File(DEFAULT_OUTPUT_FILE);
		File err = new File(DEFAULT_ERROR_FILE);
		pb.redirectError(err);
		pb.redirectOutput(out);
		try {
			Process p = pb.start();
			p.waitFor();
			return true;
		} catch (IOException | InterruptedException e) {
			Activator.getDefault().getLog().error("Process builder for external programm could not be started", e);
		}
		return false;
	}
}
