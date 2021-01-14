/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons.command;

import java.io.File;
import java.io.IOException;

public class CommandRunner {
	
	
	
	
	private static final String DEFAULT_OUTPUT_FILE = "output.txt";
	private static final String DEFAULT_ERROR_FILE = "error.txt";
	
	/**
	 * 
	 * @param applicationpath path to the .exe file
	 * @param command command to run 
	 * @return returns true if the process could be started
	 */
	public boolean startCommandRunner(String applicationpath, String command) {

		ProcessBuilder pb = new ProcessBuilder(applicationpath + command);
		File out = new File(DEFAULT_OUTPUT_FILE);
		File err = new File(DEFAULT_ERROR_FILE);
		pb.redirectError(err);
		pb.redirectOutput(out);
		try {
			pb.start();
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
