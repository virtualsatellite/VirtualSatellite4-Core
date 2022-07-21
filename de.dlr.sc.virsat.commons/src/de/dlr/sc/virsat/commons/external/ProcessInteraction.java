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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessInteraction {
	
	/**
	 * 
	 * @param command command to run and application path
	 * @return returns true if the process was successfully finished
	 * @throws IOException cannot find exe for starting the process
	 * @throws InterruptedException if the thread is interrupted while the command is running
	 */
	public List<String> startCommandRunner(String command) throws IOException, InterruptedException {
		
		List<String> output = new ArrayList<String>();
		ProcessBuilder pb = new ProcessBuilder(command);
		pb.redirectErrorStream(true);
		Process p = pb.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			output.add(line);
		}
		reader.close();
		p.waitFor();
		return output;
	}
}
