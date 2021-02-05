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

import de.dlr.sc.virsat.commons.Activator;

public class ProcessInteraction {
	
	/**
	 * 
	 * @param applicationpath path to the application
	 * @param command command to run 
	 * @return returns true if the process was successfully finished
	 */
	public List<String> startCommandRunner(String applicationpath, String command) {
		
		List<String> output = new ArrayList<String>();
		ProcessBuilder pb = new ProcessBuilder(applicationpath + command);
		pb.redirectErrorStream(true);
		try {
			Process p = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				output.add(line);
			}
			reader.close();
			p.waitFor();
			return output;
		} catch (IOException | InterruptedException e) {
			Activator.getDefault().getLog().error("Process builder for external programm could not be started", e);
			return output;
		}
	}
}
