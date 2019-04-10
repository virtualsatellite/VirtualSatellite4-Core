/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.fdir.external.lib.z3;

import de.dlr.sc.virsat.external.lib.NativeLibPlugin;

/**
 * Activator for loading the Z3 Plugin
 * 
 * @author muel_s8
 *
 */
public class Activator extends NativeLibPlugin {

	@Override
	public void loadLibraryByAbsolutePath(String libNameAbsolutePath) {
		System.load(libNameAbsolutePath);
	}

	@Override
	public void loadLibraryByName(String libName) {
		System.loadLibrary(libName);
	}
}
