/**
 * This file is part of the VirSat project.
 *
 * Copyright (c) 2008-2015
 * German Aerospace Center (DLR), Simulation and Software Technology, Germany
 * All rights reserved
 *
 */
package de.dlr.sc.virsat.model.extension.thermal.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Activator of test Fragment as central info hub, even though
 * Fragments do not have an OSGI/Equinox Activator such as a plugin
 * @author fisc_ph
 *
 */
public class TestActivator {

	/**
	 * Hidden constructor of activator class
	 */
	private TestActivator() {
	}
	
	public static final String FRAGMENT_ID = "de.dlr.sc.virsat.model.extension.funcelectrical.test";

	/**
	 * Method to access the fragments contents from the resource folder and to ahnd it back as string
	 * @param resourcePath the path to the resource starting with "resource/"
	 * @return the content of the resource as string
	 * @throws IOException throws
	 */
	public static InputStream getResourceContentAsString(String resourcePath) throws IOException {
		URL url;

		url = new URL("platform:/plugin/" + FRAGMENT_ID + resourcePath);
		InputStream inputStream = url.openConnection().getInputStream();

		return inputStream;
	}
}
