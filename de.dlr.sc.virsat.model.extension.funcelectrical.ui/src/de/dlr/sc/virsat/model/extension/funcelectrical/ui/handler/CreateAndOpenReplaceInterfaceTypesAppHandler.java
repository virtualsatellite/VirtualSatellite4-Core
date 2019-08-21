/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.handler;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.commands.IHandler;

import de.dlr.sc.virsat.apps.ui.handler.CopyAndOpenAppHandler;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.Activator;

/**
 * Handler for creating and opening a script that creates an example study
 * @author fisc_ph
 *
 */

public class CreateAndOpenReplaceInterfaceTypesAppHandler extends CopyAndOpenAppHandler implements IHandler {


	
	@Override
	protected InputStream getAppStream() throws IOException {
		return Activator.getResourceContentAsString("/resources/apps/" + APP_FILE_NAME);
	}

	@Override
	protected String getAppName() {
		return APP_FILE_NAME;
	}
}
