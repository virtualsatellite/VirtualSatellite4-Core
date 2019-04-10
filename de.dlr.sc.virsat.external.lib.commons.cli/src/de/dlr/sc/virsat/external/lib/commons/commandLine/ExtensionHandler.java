/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.external.lib.commons.commandLine;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;

/**
 * Methosd to handle the command line option extension
 * @author fisc_ph
 *
 */
public class ExtensionHandler {

	CommandLineManager commandLineManager;

	/**
	 * Constructor
	 * @param commandLineHandler the command line handler in which this class should inject the set command line arguments
	 */
	public ExtensionHandler(CommandLineManager commandLineHandler) {
		this.commandLineManager = commandLineHandler;
	}

	private static final String EXTENSION_POINT_ID = "de.dlr.sc.virsat.external.lib.commons.cli.CommandLineArguments";

	/**
	 * Call this method to read the extension point from the registry and make it set up the command line 
	 * manager accordingly
	 * @param registry the currently loaded registry
	 */
	public void execute(IExtensionRegistry registry) {
		IConfigurationElement[] config = registry.getConfigurationElementsFor(EXTENSION_POINT_ID);

		for (IConfigurationElement e : config) {
			String command = e.getAttribute("command");
			String attribute = (String) e.getAttribute("attribute");
			String desc = (String) e.getAttribute("description");

			if (attribute == null) {
				commandLineManager.addOption(command, desc);
			} else {
				commandLineManager.addOption(command, attribute, desc);
			}

		}
	}
}
