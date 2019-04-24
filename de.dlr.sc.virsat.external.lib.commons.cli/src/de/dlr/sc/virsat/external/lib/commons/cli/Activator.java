/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.external.lib.commons.cli;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleContext;
import de.dlr.sc.virsat.external.lib.LibPlugin;
import de.dlr.sc.virsat.external.lib.commons.commandLine.CommandLineManager;
import de.dlr.sc.virsat.external.lib.commons.commandLine.ExtensionHandler;

/**
 * OSGI Equinox Plugin Activator Class
 * @author fisc_ph
 *
 */
public class Activator extends LibPlugin {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		commandLineManager = CommandLineManager.getInstance();
		extensionHandler = new ExtensionHandler(commandLineManager);
		extensionHandler.execute(Platform.getExtensionRegistry());
		commandLineManager.parseCommandLineArguments();
	}

	private static CommandLineManager commandLineManager;
	private static ExtensionHandler extensionHandler;

	/**
	 * Method to access the command line manager which can tell about set options etc.-
	 * @return the command line maanegr
	 */
	public static CommandLineManager getCommandLineManager() {
		return commandLineManager;
	}
}
