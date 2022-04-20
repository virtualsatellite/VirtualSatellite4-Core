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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class ProcessInteraction {
	
	/**
	 * 
	 * @param applicationpath path to the application
	 * @param command command to run and application path
	 * @return returns true if the process was successfully finished
	 * @throws IOException cannot find exe for starting the process
	 * @throws InterruptedException 
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
	
	/**
	 * 
	 * @param applicationpath path to the application
	 * @param command command to run and application path
	 * @return returns true if the process was successfully finished
	 * @throws IOException cannot find exe for starting the process
	 * @throws InterruptedException 
	 */
	public List<String> startCommandRunner(String command1, String command2, String command3, String command4, String command5, String command6, String command7, String command8, String command9) throws IOException, InterruptedException {
		// Fix that that strings have not to be entered seperate
		List<String> output = new ArrayList<String>();
		ProcessBuilder pb = new ProcessBuilder(command1, command2, command3, command4, command5, command6, command7, command8, command9);
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
	
	/***
	 * Opens the results of the command runner inside the IDE
	 * 
	 * @param file with the result of the command runner
	 */
	public void openCommandResult(String filename) {
		try {
			String path = new File(".").getCanonicalPath();
			
			File file = new File(path + filename);
			IFileStore fileStore = EFS.getLocalFileSystem().getStore(file.toURI());
			if (fileStore != null) {
				IWorkbench workbench = PlatformUI.getWorkbench();
				if (workbench != null) {
					IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
					if (window != null) {
						IWorkbenchPage page = window.getActivePage();
						try {
							IDE.openEditorOnFileStore(page, fileStore);
						} catch (PartInitException e) {
						}
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
