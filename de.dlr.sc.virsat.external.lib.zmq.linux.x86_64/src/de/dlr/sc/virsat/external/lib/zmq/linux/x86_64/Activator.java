/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.external.lib.zmq.linux.x86_64;

import de.dlr.sc.virsat.external.lib.NativeLibPlugin;

import java.io.IOException;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;

/**
 * Activator for loading the ZMQ Plugin
 * 
 * @author wulk_ja
 *
 */
public class Activator extends NativeLibPlugin implements BundleActivator {

	@Override
	protected boolean loadFromAbsolutePath() {
		// The libraries for zmq should be loaded from the system
		// Thus it is expected that they reside on the path
		// hence they are not loaded by absolute path
		return false;
	}
	
	@Override
	protected ArrayList<String> getLibraryNames(boolean createAbsolutePath) throws IOException {
		ArrayList<String> zmqLibraries = new ArrayList<>(); 
		zmqLibraries.add("jzmq"); // is loading the zmq by itself since it is depending and it should be in the LD_LIBRARY_PATH
		return zmqLibraries;
	}
	
	@Override
	public void loadLibraryByAbsolutePath(String libNameAbsolutePath) {
		System.load(libNameAbsolutePath);
	}

	@Override
	public void loadLibraryByName(String libName) {
		System.loadLibrary(libName);
	}
}
