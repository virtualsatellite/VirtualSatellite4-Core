/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.configuration;

import java.util.Properties;

/**
 * Class for storing configuration properties for VirSat Server.
 * Properties should be loaded from a property file on startup, and can be then accessed.
 */
public class ServerConfiguration {
	
	private static Properties properties = null;
	
	private ServerConfiguration() { }
	
	/**
	 * Loads a properties file into memory
	 * @param filePath path to file containing properties in java properties format
	 */
	public static void loadProperties(String filePath) {
	}
	
	
	public static Properties getProperties() {
		return properties;
	}

	
}
