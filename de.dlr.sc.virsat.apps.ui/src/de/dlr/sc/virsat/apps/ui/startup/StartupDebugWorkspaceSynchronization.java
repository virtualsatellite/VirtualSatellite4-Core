/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.ui.startup;

import org.eclipse.ui.IStartup;

import de.dlr.sc.virsat.apps.ui.Activator;

/**
 * This class is called once the APPs UI Plugin gets Loaded by the eclipse platform.
 * We registered this class to an early startup event and by this we make sure, that the activator gets loaded which
 * takes care of refreshing the workspace if an APP got executed.
 * @author fisc_ph
 *
 */
public class StartupDebugWorkspaceSynchronization implements IStartup {
	@Override
	public void earlyStartup() {
		Activator.getDefault();
	}
}
