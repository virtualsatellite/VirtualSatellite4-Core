/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.eclipse.xtext.ecore.EcoreRuntimeModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Activator of test Fragment as central info hub, even though
 * Fragments do not have an OSGI/Equinox Activator such as a plugin
 * @author fisc_ph
 *
 */
public class TestActivator {

	private static Injector injector;
	
	/**
	 * Hidden construcotr of activator class
	 */
	private TestActivator() {
	}
	
	public static final String FRAGMENT_ID = "de.dlr.sc.virsat.model.concept.test";

	/**
	 * Method to access the fragments contents from the resource folder and to ahnd it back as string
	 * @param resourcePath the path to the resource starting with "resource/"
	 * @return the content of the resource as string
	 * @throws IOException throws
	 */
	public static String getResourceContentAsString(String resourcePath) throws IOException {
		URL url = new URL("platform:/plugin/" + FRAGMENT_ID + resourcePath);
		InputStream inputStream = url.openConnection().getInputStream();

		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String inputLine;
		StringBuilder fileContent = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			fileContent.append(inputLine);
			fileContent.append(System.lineSeparator());
		}

		in.close();

		return fileContent.toString();
	}
	
	public static synchronized Injector getInjector() {
		if (injector == null) {
			initializeEcoreInjector();
		}
		return injector;
	}
	
	private static void initializeEcoreInjector() {
		injector = Guice.createInjector(new EcoreRuntimeModule());
	}

}
